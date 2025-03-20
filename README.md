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
   - Java ja Maven õigesti seadistatud.
   - Käivita projekt (mina ise kasutan IntelliJ IDEA community editionit). 
   - Rakendus käivitub ja Tomcat alustab töötamist pordil 8080.
   - Ava brauseris http://localhost:8080/flights


Arendamis noted:
Projektile kulus umbes 15–20 tundi. Alguses kulus aega kavandamisele ja osade võtetega kurssi viimine, seejärel parandasin errorit, mis oli tingitud sellest, et panin projekti põhikaustaks liiga üldise kausta ja IntelliJ ei suutnud projektist aru saada, aga selle arusaamiseks kulus mul aega. Seejärel oli üldjoontes töö üsna sujuv kui väljaarvata mõned pisidetailid.
Töö käigus tehtud märkmed ja keerukused:
-	Istmete erinevad klassid: Erinevad klassid (1. klass, äriklass, turistiklass) ja kuidas kuvada istmete „tooltipid“, et kasutaja saaks kiiresti aru, millisesse klassi iste kuulub. See osa nõudis täpset CSS-i ja JavaScripti kombineerimist ning abi sain dokumentatsioonilt ning Stack Overflow postitustelt.
-	Istekohtade soovitamise loogika:
SeatService genereerib lennuki istmekoha plaani, määrates istmetele juhuslikult “hõivatud” staatuse. See lahendus oli mõnevõrra keerukas, kuna tuli arvestada erinevate istmete klasside ja nende visuaalse eristamisega. Lahenduse testimisel tekkisid küsimused istmete paigutuse ja CSS Grid-i kasutamise kohta, millele aitas lahendus leida ametlik Thymeleaf ja CSS dokumentatsioon.
-	Filtreerimise funktsionaalsus:
Lennuplaani andmete filtreerimine on tehtud FlightService abil. Filtreerimismeetodis eeldatakse, et kui mingit filtrit ei esine (väärtus on null või tühi), siis seda filtrit ignoreeritakse. 
-	Probleemid ja lahendused:
-	Soovitatud istekohtade kuvamine:
Sõnumit "Kahjuks sellist kohta ei ole saadaval" kuvati juba enne, kui kasutaja üldse kasutas soovitatud istekohtade otsimise tööriista, sest recommendedSeats oli null. Selle lahendamiseks lisasin kontrolli, et recommendedSeats osa kuvatakse ainult siis kui seda reaalselt kasutatakse.
- Püsinud probleemid/ mida edasi arendada:
Tahaksin panna istmeplaani taustaks lennuki ja need istmed siis sobitada kokku plaaniga, et anda kasutajale kõvasti parem visuaalne „idee“, kus koht asub jne. Ilmselt saaks lahendada täpsema CSS positsioneerimisega ja kinldad mõõdud määrata, et istmed klapiks pildil olevatega. Hea näide, mis mulle endale meeldib on Finnairi lahendus istmeplaanist.
Istmete vahekäiku ei ole näha, alguses oli, aga kui ma muutsin istmete suuruse väiksemaks siis pseduoelement enam ei tee vahe istmete vahele ja sellega ma jäingi hetkel jänni.
