# 📝 Markdown Notes App
 
> A beautiful, offline-first notes app with live Markdown preview,
> built with Kotlin and Jetpack Compose.

[![Android](https://img.shields.io/badge/Android-3DDC84?style=flat&logo=android&logoColor=white)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=flat&logo=jetpack-compose&logoColor=white)](https://developer.android.com/compose)
[![Room](https://img.shields.io/badge/Room_DB-FF6F00?style=flat&logo=android&logoColor=white)](https://developer.android.com/jetpack/androidx/releases/room)
[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg)](https://developer.android.com/reference)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://www.linkedin.com/in/lahsine-younes/)

 
---
 
## Overview
 
Markdown Notes App is a clean, modern Android app for capturing and
organizing your thoughts in Markdown format. It features a distraction-
free editor, live preview, tag-based organization, and full offline
support via Room database.
 
Built as a learning project and portfolio piece, it demonstrates clean
MVVM architecture using modern Android libraries.
 
---
 
## Features
 
- ✅ Create, edit, and delete notes
- ✅ Live Markdown preview (bold, italic, headings, lists)
- ✅ Tag / label notes (Work, Personal, Ideas, General)
- ✅ Real-time search across title and content
- ✅ Offline-first with Room database persistence
- ✅ Clean, modern UI with soft card design
- ✅ Empty state & confirmation dialogs
 
---
 
## Screenshots
 
| Home notes | Edit note | Preview note |
|------------|-----------|--------------|
| <img width="250" alt="home notes" src="https://github.com/user-attachments/assets/4a40d0f3-e174-44d2-8bda-4ea932ad7bad" /> | <img width="250" alt="edit note" src="https://github.com/user-attachments/assets/ef101169-2dd3-4534-820b-c640884d26b7" /> | <img width="250" alt="preview note" src="https://github.com/user-attachments/assets/94973161-37c2-4e12-ab6b-35981174d97b" /> |

| Home empty | Empty note | Search |
|------------|------------|--------|
| <img width="250" alt="home empty" src="https://github.com/user-attachments/assets/8c7b5e92-cb78-48a1-b0a8-a3ac6174232f" /> | <img width="250" alt="note empty" src="https://github.com/user-attachments/assets/8b2fca2c-0dd9-4749-bd5b-54a80843146d" /> | <img width="250" alt="search" src="https://github.com/user-attachments/assets/a4919cd9-b9e2-4036-8513-8dfd92cf716c" /> |
 
---
 
## Tech Stack
 
| Layer | Technology |
|-------|------------|
| Language | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Architecture | MVVM |
| Database | Room |
| State | StateFlow + collectAsState |
| Navigation | Navigation Compose |
| Markdown | Markwon |
 
---
 
## Architecture

<img height="300" alt="mardown-notes-app Arch" src="https://github.com/user-attachments/assets/095f7d83-e0b3-4dae-b059-13a3e1ad82e6" />


This project uses a simple, beginner-friendly MVVM pattern:
 
```
UI (Compose screens)
     ↕  observes StateFlow
NoteViewModel (business logic)
     ↕  suspend functions
NoteDao (Room queries)
     ↕
NoteDatabase (SQLite on device)
```
 
No complex dependency injection. No multi-module setup.
Clean enough to read in one sitting.
 
---
 
## Getting Started
 
### Prerequisites
- Android Studio Hedgehog or newer
- JDK 17+
- Android device or emulator (API 26+)
 
### Installation
 
```bash
# 1. Clone the repo
git clone https://github.com/LahsineYounes/markdown-notes-app.git
 
# 2. Open in Android Studio
# File → Open → select the folder
 
# 3. Sync Gradle (Android Studio does this automatically)
 
# 4. Run on your device or emulator
# Press the green Play button ▶
```
 
---
 
## Project Structure
 
```
ui/          → Compose screens (HomeScreen, EditorScreen)
data/        → Room DAO and Database setup
model/       → Note data class
ViewModel    → State management and business logic
```
 
---
 
## Future Improvements
 
- [ ] AI-powered note summarization (Gemini API)
- [ ] Export notes as PDF or .md file
- [ ] Cloud sync via Firebase
- [ ] Biometric lock for sensitive notes
- [ ] Widget for home screen quick capture
- [ ] Folder/notebook organization
- [ ] Semantic search across notes
 
---
 
## License
 
```
MIT License — free to use, modify, and distribute.
```
