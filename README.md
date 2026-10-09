# CMPUT 301 : Lab 5 Participation Exercise

## Student Details

- **Full Name:** Sam Rawluk
- **CCID:** sgrawluk

## References and Resources

N/A

## Verbal Collaboration

N/A

## Local Firebase setup

The app uses Firebase Firestore. Its live Firebase configuration and the exported
`ListyCity.zip` are intentionally not tracked because the archive also contains
configuration embedded in generated resources and APKs. The app source and build
logic are unchanged.

1. Open the existing Firebase project in the Firebase console, then go to
   **Project settings > General > Your apps**.
2. Select the Android app registered as `com.example.listycity` and download its
   `google-services.json`.
3. Put the downloaded file at `ListyCity/app/google-services.json`, then open
   `ListyCity` in Android Studio, sync Gradle, and build/run normally.

`ListyCity/app/google-services.json.example` documents the expected shape using
placeholders only. It is not a working configuration; use the downloaded file.
Fresh clones require this local setup before the Google Services Gradle task can
succeed. Before pulling this cleanup into an existing checkout, keep a backup of
any local `google-services.json` outside the repository, then restore it afterward
(or download it again). Git may remove the formerly tracked file when pulling.

Keep the live config and generated APK/AAB/project ZIP exports out of public
Git commits. If a course submission needs Firebase configuration, follow the
course's approved private submission process. This repository no longer includes
an exported project ZIP.

Removing the files from the current branch does not erase older commits or
revoke the previously published key. Firebase console keys and settings have not
been changed by this cleanup.
