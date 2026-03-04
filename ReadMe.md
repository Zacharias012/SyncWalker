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
```

### 3. Pfade anpassen
Passe beide Variablen an dein lokales System an:

* **`folder_source`**
  Definiert die *Source of Truth* — also das Quellverzeichnis, aus dem synchronisiert wird.
  Dieses Verzeichnis dient als Referenzzustand.

* **`folder_target`**
  Definiert das Zielverzeichnis.
  In dieses Verzeichnis werden alle Dateien kopiert, die in der Quelle existieren, aber im Ziel fehlen.

