# StudyDeck - Karteikarten Lern-App

Eine moderne Web-Anwendung zum Erstellen und Lernen von Karteikarten mit Quiz-Funktion.

## 🚀 Live Demo

**Frontend:** https://htw-studydeck-frontend.onrender.com  
**Backend API:** https://htw-studydeck-backend.onrender.com

## 📖 Funktionen

- ✅ Decks erstellen, bearbeiten und löschen
- ✅ Karteikarten mit Frage & Antwort erstellen
- ✅ Lernsets zur Organisation von Decks
- ✅ Lernmodus: Karten durchgehen und als gelernt markieren
- ✅ Quiz-Modus mit 3 Fragetypen:
    - Multiple Choice
    - True/False
    - Freitext-Eingabe
- ✅ Fortschrittsanzeige mit Statistiken

## 🛠️ Tech Stack

**Frontend:**
- Vue.js 3
- TypeScript
- Pinia (State Management)
- Vue Router
- Axios

**Backend:**
- Spring Boot
- PostgreSQL
- JPA/Hibernate
- Lombok

## 📦 Installation & Start (lokal)

### Backend
```bash
cd todo-app
./gradlew bootRun
```

Backend läuft auf: `http://localhost:8080`

### Frontend
```bash
cd studydeck-frontend
npm install
npm run dev
```

Frontend läuft auf: `http://localhost:5173`

## 📱 Benutzung

### 1. Deck erstellen
- Navigiere zu "Meine Decks"
- Klicke auf "+ Neues Deck"
- Gib einen Titel ein (z.B. "Zellen")
- Titel muss min. 3 Zeichen haben

### 2. Karten hinzufügen
- Öffne ein Deck
- Klicke auf "+ Neue Karte"
- Gib Frage und Antwort ein
- Speichern
- Antwort und Frage dürfen nicht leer sein

### 3. Lernen
- Im Deck auf "Lernen" klicken
- Karte anzeigen → umdrehen → als gelernt markieren oder nicht gelernt

### 4. Quiz starten
- Im Deck auf "Quiz" klicken
- Fragen beantworten
- Ergebnis ansehen

## 🎓 Projekt-Info

- **HTW Berlin** - Webtech Projekt WiSe 2025
- **Student:** Tarik Onat
- **Studiengang:** Wirtschaftsinformatik (4. Semester)
```