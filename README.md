# 📝 Markdown Notes App
 
> A beautiful, offline-first notes app with live Markdown preview,
> built with Kotlin and Jetpack Compose.
 
![Android](https://img.shields.io/badge/Android-3DDC84?style=flat&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=flat&logo=jetpack-compose)
![License](https://img.shields.io/badge/License-MIT-green.svg)
 
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
 
| Home | Editor | Search |
|------|--------|--------|
| ![Home](docs/screenshots/home.png) | ![Editor](docs/screenshots/editor.png) | ![Search](docs/screenshots/search.png) |
 
| Empty State | Dark Mode |
|-------------|-----------|
| ![Empty](docs/screenshots/empty_state.png) | ![Dark](docs/screenshots/dark_mode.png) |
 
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
git clone https://github.com/yourusername/markdown-notes-app.git
 
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
See LICENSE file for full terms.
```
