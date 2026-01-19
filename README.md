# Like Hero To Zero – IPWA02-01 Fallstudie

## Projektbeschreibung
Dieses Projekt ist ein funktionaler Prototyp, der im Rahmen der Fallstudie des Kurses  
**IPWA02-01 – Programmierung von industriellen Informationssystemen** an der  
**IU Internationale Hochschule** erstellt wurde.

Ziel der Anwendung ist es, CO₂-Emissionsdaten zu erfassen und darzustellen:
- Öffentlicher Bereich zur Anzeige von CO₂-Daten (ohne Login)
- Wissenschaftler-Bereich zur Pflege der Daten (mit Login)

Die Anwendung dient ausschließlich zu Studien- und Demonstrationszwecken.

---

## Technologie-Stack
- Java 17
- Jakarta EE (JSF, CDI, JPA)
- PrimeFaces
- Maven
- Payara Server
- Relationale Datenbank (z. B. MySQL)

---

## Projektstruktur (Auszug)

src/main/java
 ├─ model        (JPA Entities)
 ├─ service      (Datenzugriff / Repository)
 └─ controller   (JSF Managed Beans)

src/main/webapp
 ├─ index.xhtml
 ├─ public.xhtml
 ├─ login.xhtml
 └─ scientist.xhtml


---

## Voraussetzungen
- Java 17 installiert
- Maven installiert
- Payara Server (z. B. Payara 6)
- Relationale Datenbank (z. B. MySQL)

---

## Datenbank-Konfiguration
Die Anwendung verwendet eine JNDI DataSource mit folgendem Namen:


jdbc/lh2z


Diese DataSource muss im Payara Server konfiguriert sein (inkl. JDBC-Treiber).

Beispiel:
- Datenbank: lh2z
- Tabelle: co2_record
- Felder: id, date, country, co2

---

## Build & Deployment
1. Projekt bauen:

mvn clean package


2. WAR-Datei deployen:
- über die Payara Admin Console  
  oder
- über den Autodeploy-Ordner

3. Anwendung aufrufen:

http://localhost:8080/like-hero-to-zero


---

## Login (Wissenschaftler-Bereich)
Der Login ist vereinfacht umgesetzt (Prototyp).

Zugangsdaten:
- E-Mail: wissenschaftler@demo.de
- Passwort: zugang

---

## Hinweise zur Bewertung
- Das Projekt erfüllt die technischen Mindestanforderungen der Fallstudie (JSF, JPA, Persistenz).
- Vereinfachungen oder nicht vollständig umgesetzte Anforderungen werden in der schriftlichen Ausarbeitung transparent erläutert.
- Fokus liegt auf Architektur, Nachvollziehbarkeit und funktionalem Prototyp.

---

## Autor
Name: Matthias Pröschel
Matrikelnr.: 32104901  
Kurs: IPWA02-01  
Studiengang: Wirtschaftsinformatik  
IU Internationale Hochschule
