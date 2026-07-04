# 📺 Android TV Video Screensaver

A modern Android TV screensaver application that allows users to select one or multiple local videos and play them as a continuous screensaver playlist.

Built using **Kotlin**, **Media3 (ExoPlayer)**, **MVVM Architecture**, and **Android DreamService**.

---

## ✨ Features

- 🎬 Browse all local videos from device storage
- ▶ Preview videos before selecting
- 📂 Select one or multiple videos
- 🔁 Continuous playlist playback
- 🔄 Automatic looping of the playlist
- 🔇 Mute screensaver audio
- ⚙ Settings screen
- 📺 Android TV optimized interface
- 🎨 Modern Material Design UI
- 💾 Persistent playlist using DataStore
- 🖼 Automatic video thumbnails
- 🎯 D-Pad friendly navigation

---



## 🛠 Tech Stack

- Kotlin
- Android Studio
- Media3 (ExoPlayer)
- Android DreamService
- RecyclerView
- Glide
- DataStore Preferences
- Material Components
- MVVM Architecture
- Repository Pattern

---

## 📂 Project Structure

```
app
│
├── adapter
├── datastore
├── dream
├── models
├── player
├── repository
├── settings
├── storage
├── ui
└── viewmodel
```

---

## 🚀 How It Works

1. Open **Video Library**
2. Select one or multiple videos
3. Save the playlist
4. Enable the application as your Android TV Screensaver
5. Enjoy continuous playback

### Playlist Behavior

- **1 Video Selected**
  - Loops forever

- **Multiple Videos Selected**
  - Plays one after another
  - Automatically repeats the playlist

---

## ⚙ Requirements

- Android TV / Google TV
- Android 8.0 (API 26) or later
- Storage permission to access local videos

---

## 🧩 Architecture

```
Video Library
      │
      ▼
RecyclerView
      │
      ▼
DataStore (Playlist)
      │
      ▼
DreamService
      │
      ▼
Media3 ExoPlayer
      │
      ▼
Continuous Playlist Playback
```

---

## 🎯 Future Improvements

- Shuffle Mode
- Favorites
- Folder Selection
- Video Categories
- Playlist Reordering
- Transition Effects
- Cloud Backup
- Play Store Release

---

## 👨‍💻 Developer

**Anoob**


Interested in:

- Android Development
- Cybersecurity
- AI Applications
- IoT Projects

---

## 📄 License

This project is licensed under the MIT License.

---

## ⭐ Support

If you found this project useful, consider giving it a ⭐ on GitHub.

It helps others discover the project and motivates future improvements.

/*
 * Copyright (c) 2026 Anoob Krishna S N.
 * All rights reserved.
 */
