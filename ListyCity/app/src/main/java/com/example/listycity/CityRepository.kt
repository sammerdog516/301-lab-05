package com.example.listycity

import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CityRepository {
    private val db = Firebase.firestore
    private val citiesRef = db.collection("cities")
    private val _cities = mutableStateListOf(
        City("Edmonton", "AB"),
        City("Vancouver", "BC"),
        City("Toronto", "ON")
    )

    val cities: List<City>
        get() = _cities

    init {
        citiesRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            _cities.clear()

            snapshot?.documents?.forEach { document ->
                val city = document.toObject(City::class.java)
                if (city != null) {
                    _cities.add(city)
                }
            }
        }
    }

    fun addCity(city: City) {
        citiesRef.document(city.name)
            .set(city)
            .addOnSuccessListener {
                android.util.Log.d("FIRESTORE_DEBUG", "City added: ${city.name}")
            }
            .addOnFailureListener { error ->
                android.util.Log.e("FIRESTORE_DEBUG", "Add failed", error)
            }
    }

    fun updateCity(oldCity: City, updatedCity: City) {
        if (oldCity.name == updatedCity.name) {
            citiesRef.document(oldCity.name).update(
                mapOf(
                    "name" to updatedCity.name,
                    "province" to updatedCity.province
                )
            )
        } else {
            val oldRef = citiesRef.document(oldCity.name)
            val newRef = citiesRef.document(updatedCity.name)

            db.runTransaction { transaction ->
                val oldDocument = transaction.get(oldRef)
                val newDocument = transaction.get(newRef)

                if (!oldDocument.exists()) {
                    throw IllegalStateException("Original city not found")
                }

                if (newDocument.exists()) {
                    throw IllegalStateException("City name already exists")
                }

                transaction.set(newRef, updatedCity)
                transaction.delete(oldRef)
            }
        }
    }

    fun deleteCity(city: City) {
        citiesRef.document(city.name).delete()
    }
}