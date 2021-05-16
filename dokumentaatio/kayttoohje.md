# Käyttöohje

Lataa sovelluksen zip-tiedoston viimeisimmästä releasesta.

## Ennen kuin käynnistät sovelluksen (tärkeä)

Tarvitset Google-tilin käyttääksesi sovelluksen tietokantatoimintoa ja ennätysten tallentamista. Luo itsellesi Google-tili mikäli sinulla ei sellaista ole.

Ennen kuin generoit jar-tiedoston voit halutessasi luoda oman Google Sheets dokumentin mihin ennätyksiä tallennetaan tai voit käyttää ohjelman [vakiotiedostoa](https://docs.google.com/spreadsheets/d/1e9IFMWunUko426Z4eMbGNMGwWqPIPVMd3q1gWKos9IU/edit#gid=0)

Mikäli käytät omaa tiedostoa, niin kopioi tarkalleen vakiotiedoston kaava ja taulukot omaan tiedostoosi (huomaa kaksi sivua, jotka ovat vaihdettavissa vasemmassa alareunassa). Nimeä myöskin omaan tiedostoon sivut täsmälleen samannimisiksi: "All scores" ja "TOP10". HUOM! suomen- ja englanninkielisessä Google Sheetsissä on eroja. Sovellus ja vakiotiedosto on totetutettu englanninkielisellä versiolla ja ennätysten tallentaminen ei välttämättä toimi mikäli käytät suomenkielistä Sheetsiä. Kieltä voi vaihtaa Google-tilisi asetuksista.

Mikäli luot oman tiedoston, niin kopioi tiedoston tunniste tiedoston verkkosivun osoitteesta. Vakiotiedostossa esimerkiksi tämä tunniste on "1e9IFMWunUko426Z4eMbGNMGwWqPIPVMd3q1gWKos9IU". Liitä kopioimasi tunniste config.properties tiedoston ensimmäiselle riville seuraavasti: "spreadsheetID=_tunniste tähän_"

### Mahdollistamme seuraavaksi tiedon tallentamisen Google Sheetsiin

#### HUOM! Mikäli olet ohjaaja/arvioija, niin voit ohittaa tämän vaiheen lähettämällä minulle sähköpostia osoitteeseen oskari.nuottonen@gmail.com. Voin lisätä sinut manuaalisesti sovelluksen testaajaksi ja antaa käyttöön minun OAuth tunnukset. Tarvitsen vain gmail-sähköpostiosoitteesi.

Huolimatta siitä oletko käyttänyt omaa tiedostoa vai vakiotiedostoa, tee seuraavat toimenpiteet:

1. Siirry sovelluksen main-hakemistoon (SpaceInvaders/src/main) ja luo sinne uusi hakemisto “resources”. Siirry luomaasi hakemistoon ja luo sinne uusi tiedosto nimeltä “google-sheets-client-secret.json”. Tämä muuttaa tiedoston automaattisesti json-tiedostoksi.

2. Siirry [Google Developer Consoleen](https://console.cloud.google.com/home/)

3. Luo uusi projekti developer consoleen
![google1](https://user-images.githubusercontent.com/46067482/118406321-3a995e00-b684-11eb-829b-be74adb9897d.png)
![google2](https://user-images.githubusercontent.com/46067482/118406333-50a71e80-b684-11eb-975a-c635eb847050.png)

4. Klikkaa vasemmasta laidasta Credentials -> Create Credentials -> OAuth Client ID
![google3](https://user-images.githubusercontent.com/46067482/118406342-5e5ca400-b684-11eb-9afc-08bc778b8ab5.png)

5. Klikkaa configure consent screen
![google4](https://user-images.githubusercontent.com/46067482/118406379-83511700-b684-11eb-8c76-c0ace5e3aac1.png)

6. Valitse External ja klikkaa Create
![google5](https://user-images.githubusercontent.com/46067482/118406399-9663e700-b684-11eb-80bf-33c4ff6149a4.png)

7. Anna sovellukselle haluamasi nimi, esim. Space Invaders. Valitse support emailiksi oma sähköpostiosoitteesi. Syötä alalaidassa Developer contact infoksi oma sähköpostiosoitteesi. Klikkaa Save and Continue
![google6](https://user-images.githubusercontent.com/46067482/118406408-a54a9980-b684-11eb-824a-d9bad95d2ec9.png)

8. Klikkaa auenneella ruudulla Save and Continue
![google 7](https://user-images.githubusercontent.com/46067482/118406442-ce6b2a00-b684-11eb-8f67-08f02cc8d618.png)

9. Klikkaa OAuth consent screen -> klikkaa Add users -> syötä oma gmail-tilisi sähköpostiosoitteesi -> klikkaa add
![google 8](https://user-images.githubusercontent.com/46067482/118406524-f9557e00-b684-11eb-83e9-ac4bbb27a275.png)

10. Klikkaa Credentials ja valitse pudotusvalikosta Desktop app
![google 9](https://user-images.githubusercontent.com/46067482/118406626-3cafec80-b685-11eb-9bb1-8b80eac04dfa.png)

11. Lataa OAuth 2.0 tunnisteesi
![google 10](https://user-images.githubusercontent.com/46067482/118406638-4cc7cc00-b685-11eb-82a5-738745f45816.png)

12. Kopioi lataamasi tunniste, liitä se kohdassa 1. luomaasi json-tiedostoon ja tallenna tiedosto.

Voit nyt generoida jar-tiedoston siirtymällä projektihakemistoon (missä on pom.xml-tiedosto) ja suorittamalla komennon

```
mvn package
```
Tiedostoa luodessasi selaimesi avautuu ja pyytää sinulta lupaa antaa sovelluksen muokata Google Sheets -tiedostoja. Tämä lupa pitää antaa joka kerta kun sovellus avataan.


## Ohjelman käynnistäminen

Ohjelma käynnistetään siirtymällä jar-tiedoston sisältävään hakemistoon (target) ja suorittamalla komennon

```
java -jar SpaceInvadersApplication-1.0-SNAPSHOT.jar
```

## Main menu

Sovellus käynnistyy main menuun eli päävalikkoon:

![mainmenu](https://user-images.githubusercontent.com/46067482/118407039-6f5ae480-b687-11eb-9e3a-d299558d43ad.png)

Päävalikosta pääsee
- peliin (Start/Resume Game)
- asetuksiin (Settings)
- high score -listalle (High Scores)
- pois pelistä (Exit)

Navigointi tapahtuu hiirellä tai nuolinäppäimillä/tab-näppäimellä ja ENTER-nappia painamalla.

## Peli

Pelissä on
- 1 pelaaja
  - pelaajalla on 3 elämää
- 50 vihollista / taso
- 5 tasoa
- pistemäärä
  - pistemäärä kasvaa 100:lla kun yksi vihollinen tapetaan 
  - pistemäärä vähenee 500:lla kun pelaajaan osutaan

Pelin näppäimet:
- vasen nuolinäppäin: pelaaja liikkuu vasemmalle
- oikea nuolinäppäin: pelaaja liikkuu oikealle 
- välilyönti (SPACE): pelaaja ampuu
- ESC-näppäin: avaa pause menun

![game](https://user-images.githubusercontent.com/46067482/118407078-aaf5ae80-b687-11eb-9343-d816b913988e.png)

## New High Score menu
Pelin jälkeen pelaaja voi halutessaan tallentaa ennätyksen listalle syöttämällä nimensä.

![newhsmenu](https://user-images.githubusercontent.com/46067482/118407121-e2645b00-b687-11eb-8d3c-790535c633bf.png)

## Game Over menu
Palaajan tallennettua ennätyksen tai painettuaan cancel avautuu seuraama näkymä, josta voi aloittaa uuden pelin tai palata päävalikkoon

![gameovermenu](https://user-images.githubusercontent.com/46067482/118407135-f60fc180-b687-11eb-88ae-c230290d34c9.png)

## Asetukset

Pelissä voi vaihtaa seuraavia asetuksia
- väriasetukset
  - muuttaa pelin värit käänteiseksi (ns. dark mode)

Päävalikkoon voi palata _Back to Main Menu_ näppäimellä

![settingsmenu](https://user-images.githubusercontent.com/46067482/118407084-b648da00-b687-11eb-81c3-8726ee82a0e3.png)

## High Score -lista

High Score -lista tallentaa ennätykset pistemäärän mukaan. Pelin läpäistyään pelaaja voi halutessaan tallentaa tuloksensa syöttämällä nimimerkin.

Päävalikkoon voi palata _Back to Main Menu_ näppäimellä

![hsmenu](https://user-images.githubusercontent.com/46067482/118407094-bea11500-b687-11eb-98f6-0cecb4ea139b.png)
