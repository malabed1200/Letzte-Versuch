Protokoll – Warehouse REST Projekt
1. Ziel des Projekts

In diesem Projekt mussten wir eine kleine Spring-Boot Anwendung bauen, die die Daten eines Warenlagers simuliert. Die Daten sollen als JSON und als XML abrufbar sein.
Zusätzlich sollte der Standort Linz als Beispiel verwendet werden und einige Produkte beinhalten.

2. Vorbereitungen

Ich habe zuerst das Projekt über Spring Initializr erstellt (Spring Web + Jackson XML).
Dann habe ich das Projekt in IntelliJ geöffnet und den typischen Spring-Boot Aufbau gemacht:

WarehouseApplication → Startklasse

warehouse Package → Controller + Service

model Package → WarehouseData + Product Klassen

3. Umsetzung
3.1 Controller

Der Controller stellt zwei Endpunkte bereit – einen für JSON, einen für XML:

@RequestMapping("/warehouse/{inID}/json")
public WarehouseData warehouseDataJson(@PathVariable String inID) {
    return service.getWarehouseData(inID);
}


und

@RequestMapping("/warehouse/{inID}/xml")
public WarehouseData warehouseDataXml(@PathVariable String inID) {
    return service.getWarehouseData(inID);
}


Das hat gut funktioniert, weil Spring automatisch in JSON bzw. XML konvertiert.

3.2 Service

Im Service werden die Daten erstellt. Ich habe einfach fixe Werte genommen, weil es für die Übung reicht:

products.add(new Product("00-443175", "Bio Orangensaft Sonne",
        "Getraenk", 2500, "Packung 1L"));


Außerdem wird ein Timestamp gesetzt:
LocalDateTime.now().toString()

3.3 XML Format

Damit die XML-Ausgabe schön aussieht, musste ich bei der Liste diese Annotation machen:

@JacksonXmlElementWrapper(localName = "productData")
@JacksonXmlProperty(localName = "product")


Vorher war die XML-Ausgabe komplett falsch formatiert.

4. Ergebnis / Links

Nach dem Starten der Anwendung (./gradlew bootRun) konnte ich folgende Links im Browser öffnen:

✔ Startseite

http://localhost:8080/

✔ JSON

http://localhost:8080/warehouse/001/json

Beispiel JSON:
{
  "warehouseID": "001",
  "warehouseName": "Linz Bahnhof",
  "warehouseCity": "Linz",
  "productData": [...]
}

✔ XML

http://localhost:8080/warehouse/001/xml

Beispiel XML:
<WarehouseData>
    <warehouseID>001</warehouseID>
    <warehouseName>Linz Bahnhof</warehouseName>
    <productData>
        <product>...</product>
    </productData>
</WarehouseData>

5. Probleme & Lösungen
Problem 1: XML war kaputt

Ohne die Jackson-Annotationen wurde die Liste falsch dargestellt.
Lösung: @JacksonXmlElementWrapper und @JacksonXmlProperty hinzufügen.

Problem 2: JSON und XML hatten denselben Link

Am Anfang hatte ich nur /warehouse/{id}.
Dann habe ich zwei getrennte Routen gemacht:
/json und /xml.

Problem 3: Timestamp sah komisch aus

Ich habe mich für .toString() entschieden, weil es ausreichend für die Aufgabe ist.

6. Erkenntnisse

Wie man in Spring eine REST-Schnittstelle baut

Unterschied zwischen JSON- und XML-Ausgabe

Wie Jackson XML funktioniert

Wie man Models sauber aufbaut

Wie Dependency Injection in Spring funktioniert

7. Fazit

Die Aufgabe war anfangs ein bisschen verwirrend, aber nach dem Controller und dem Service war alles klar. Die Ausgabe funktioniert im Browser ohne Probleme und erfüllt die Anforderungen (JSON + XML).
