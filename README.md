Lennu planeerimise ja lennukis istekohtade soovitamise veebirakendus
See projekt on veebirakendus lennureisijale, mis võimaldab:
- Kuvada lennuplaani kõikide lendudega.
- Filtreerida lende sihtkoha, kuupäeva, lennuaja ja hinna järgi.
- Vaadata lennu detaile koos lennuki istmekoha plaaniga.
- Soovitada lennukis istekohti vastavalt kasutaja eelistustele (akna all, rohkem jalaruumi, lähemal väljapääsule).
- Hoides kursorit istme kohal näeb visuaalselt, millisesse klassi (1. klass, äriklass, turistiklass) iga istekoht kuulub.

Kasutatud Tehnoloogiad:

- Java, mis on antud rakenduse põhikeel.
- Spring Boot, mis vastutab serveripoolse rakenduse eest.
- Thymeleaf, mis vastutab serveripoolse HTML-mallide genereerimise eest.
- Maven, mis vastutab ehituse- ja sõltuvuste haldamise eest.


Kuidas Rakendus Töötab?

1. Lennuplaan (flights.html)

- Eesmärk: Kuvada kõik olemasolevad lennud ja võimaldada neid filtreerida.
- Filtreerimisvõimalused:  Sihtkoht, aeg, hind, kuupäev
- Iga lennu rida sisaldab lennu ID-d, sihtkohta, kuupäeva, aega, hinda ning linki "Vaata detaile", mis suunab üksiku lennu detailvaatesse.

2. Lennu Detailid ja Istekohtade Plaan (flight_detail.html)

- Eesmärk: Kuvada valitud lennu detailid ja lennuki istmekoha plaan.
- Lennu detailid: Kuvatakse lennu ID, sihtkoht, kuupäev, lennuaeg ja hind.
  
- Istekohtade plaan:  Istmekoha plaan kuvatakse grid-layouti abil. 
  - Kuvab istme numbri.
  - Kuvab tooltipina istmeklassi (1. klass, äriklass või turistiklass).
  - Kasutab erinevaid värve vastavalt olekule (vaba, hõivatud, valitud).
- Istmete valimine:  Kasutaja saab klõpsata vabal istmel, mis muudab selle "selected" olekusse. "Kinnita valik" nupp kuvab valitud istmete ID-d
- Soovitatud istekohti vorm:  Kasutaja saab valida, mitu istet ta soovib ning lisaks eelistusi (akna all, rohkem jalaruumi, lähemal väljapääsule). POST-päring saadab need andmed kontrollerisse, mis seejärel tagastab soovitatud istekohad.

Kuidas Rakendust Käivitada?

1. Ehita ja käivita projekt:
   - Veendu, et sul on Java ja Maven õigesti seadistatud.
   - Käivita projekt (mina ise kasutan IntelliJ IDEA community editionit). 
   - Rakendus käivitub ja Tomcat alustab töötamist pordil 8080.
   - Ava brauseris http://localhost:8080/flights
