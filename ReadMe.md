## SyncWalker

SyncWalker ist ein Synchronisationstool zur einseitigen Verzeichnisabgleichung.
Die Anwendung analysiert zunächst ein definiertes Source-Verzeichnis (Source of Truth) und anschließend ein Target-Verzeichnis.
Auf Basis dieses Vergleichs wird eine Differenz von Dateien ermittelt.
Alle Dateien, die im Source-Verzeichnis vorhanden sind, jedoch im Target-Verzeichnis fehlen, werden automatisiert in das Zielverzeichnis kopiert.

Das Tool eignet sich insbesondere dafür, Dateien konsistent an zwei unterschiedlichen Speicherorten synchron zu halten.
Ideal für Back-up Prozesse oder Musikdateien die auf verschiedenen Geräten zu finden sind.

Wichtig:
Es erfolgt ausschließlich ein Hinzufügen fehlender Dateien.
Eine Löschung bestehender Dateien wird unter keinen Umständen durchgeführt!

---

## Setup

Führe die folgenden Schritte aus, um das Projekt korrekt zu konfigurieren.

### 1. Konfigurationsdatei erstellen
Erstelle im **Root-Verzeichnis des Projekts** (Projekt-Ebene) eine Datei mit dem Namen:
Die Datei darf **nicht** im `src`-Ordner liegen.

```
config.properties
```

### 2. Konfigurationswerte eintragen
Füge folgenden Inhalt in die Datei ein:

```
folder_source=/home/you/sourceFolder
folder_target=/home/you/sourceTarget
file_format_copied=.txt, .pdf
```

### 3. Pfade anpassen
Passe beide Variablen an dein lokales System an:

* **`folder_source`**
  Definiert die *Source of Truth* — also das Quellverzeichnis, aus dem synchronisiert wird.
  Dieses Verzeichnis dient als Referenzzustand.

* **`folder_target`**
  Definiert das Zielverzeichnis.
  In dieses Verzeichnis werden alle Dateien kopiert, die in der Quelle existieren, aber im Ziel fehlen.

* **`file_format_copied`** (Optional)
  Ermöglicht das Filtern nach bestimmten Dateiformaten. Wenn dieser Wert gesetzt ist, werden nur Dateien mit den angegebenen Endungen synchronisiert. Werden keine Formate angegeben, werden alle Dateien kopiert!

---

### Beispiel für Dateifilter

Um nur bestimmte Formate wie `.txt` und `.pdf` zu synchronisieren, gib diese einfach durch Leerzeichen oder Kommas getrennt in der `config.properties` an:

```properties
file_format_copied=.txt .pdf
```
Oder alternativ:
```properties
file_format_copied=.txt, .pdf
```

Werden keine Formate angegeben, werden alle Dateien kopiert!

---

## Voraussetzungen

Bevor du das Projekt startest, stelle sicher, dass folgende Software installiert ist:

* **Java 21** - Prüfen mit:

  ```bash
  java -version
  ```

* **Maven 3.5** - Prüfen mit:

  ```bash
  mvn -version
  ```

---  

## Running

Dieses Projekt kann über das Terminal gebaut und gestartet werden.

### 1. Projekt bauen

```bash
mvn clean package
```

### 2. Ausführbares JAR starten

```bash
java -jar target/SyncWalker-1.0-SNAPSHOT.jar
```

### 3. Konfigurationsdatei anpassen

```bash
nano config.properties
```

* `folder_source` → Quelle (Source of Truth)
* `folder_target` → Zielverzeichnis (fehlende Dateien werden hierher kopiert)
* `file_format_copied` → Dateiformate die kopiert werden sollen