# HTW StudyDeck - Backend

**HTW Berlin – Webtechnologien (WiSe 2025/26)**  
Projekt von: *Tarik Onat*  
Matrikelnummer: *594787*

---

## Projektbeschreibung

HTW StudyDeck ist eine Web-App zum Erstellen und Lernen von digitalen Karteikarten.  
Benutzer können Decks erstellen, Karteikarten hinzufügen und im Lernmodus abfragen.

---

## Technologien

- **Backend:** Spring Boot 3, Java 21
- **Datenbank:** PostgreSQL (Produktion), H2 (Entwicklung)
- **Deployment:** Render.com
- **CI/CD:** GitHub Actions

---

## API Endpoints

### Decks
| Methode | Endpoint | Beschreibung |
|---------|----------|--------------|
| GET | `/api/decks` | Alle Decks abrufen |
| GET | `/api/decks/{id}` | Einzelnes Deck abrufen |
| POST | `/api/decks` | Neues Deck erstellen |
| PUT | `/api/decks/{id}` | Deck aktualisieren |
| DELETE | `/api/decks/{id}` | Deck löschen |

### Cards
| Methode | Endpoint | Beschreibung |
|---------|----------|--------------|
| GET | `/api/cards` | Alle Karten abrufen |
| GET | `/api/cards/deck/{deckId}` | Karten eines Decks |
| POST | `/api/cards` | Neue Karte erstellen |
| PUT | `/api/cards/{id}` | Karte aktualisieren |
| DELETE | `/api/cards/{id}` | Karte löschen |

---

## Lokale Entwicklung
```bash
cd todo-app
./mvnw spring-boot:run
```

Die API läuft dann unter: http://localhost:8080

---

## Tests ausführen
```bash
cd todo-app
./mvnw test
```

---

## Deployment

- **Backend:** https://htw-studydeck-backend.onrender.com
- **Frontend:** https://htw-studydeck-frontend.onrender.com