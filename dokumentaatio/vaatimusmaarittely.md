# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus emuloi Space Invadersin tapaista peliä.

## Käyttäjät

Sovelluksessa on vain yhden tyyppisiä käyttäjiä: pelaaja.

## Käyttöliittymä

Sovelluksen aukeaa pääsivulle, josta pelaaja voi aloittaa uuden pelin, muuttaa pelin asetuksia ja tarkastella ennätyksiä.

## Perusversion tarjoama toiminnallisuus

- Pelissä tulee olemaan 5 toinen toistaan vaikeampaa tasoa.
- Ennätyksiä mitataan pistemäärällä.
  - Pistemäärä on identtinen mikäli pelin läpäisee kokonaan, tällöin vertailuun käytetään läpäisyaikaa.
- Ennätyksen (TOP 10) saavutettuaan, pelaaja voi syöttää nimimerkin, joka tallentuu ennätyslistalle pisteiden ja läpäisyajan kera.
- Pelaaja voi muuttaa halutessaan seuraavia asetuksia:
  - Näppäinasetukset
  - Väriasetukset
  - Ääniasetukset

## Sovelluksen eteneminen

   - [x] Napit:
     - [x] Start Game
     - [x] Settings
     - [x] High Score
     - [x] Exit
   - [ ] Peli:
      - [x] Aloittaa pelin
      - [ ] Pelin aikana ESC-napin painaminen:
        - [x] Peli pysähtyy
        - [ ] Asetuksia voi vaihtaa
        - [ ] Pelin voi lopettaa
      - [x] Omalla aluksella pystyy liikkumaan
      - [x] Omalla aluksella pystyy ampumaan
      - [x] Pelissä on vihollisia
      - [x] Viholliset liikkuvat
      - [x] Vihollisia pystyy ampumaan
      - [x] Viholliset pystyvät ampumaan pelaajaa
      - [ ] Pelissä on esteitä (seiniä)
      - [ ] Esteitä voi tuhota
      - [ ] Taso vaihtuu
   - [ ] Asetukset:
      - [ ] Näppäinasetukset
      - [x] Väriasetukset
      - [x] Ääniasetukset (toistaiseksi sovellukseen ei ole lisätty ääniä)
   - [ ] Ennätykset:
      - [x] Ennätyslista
      - [ ] Uudet ennätykset tallentuvat listalle
   - [x] Exit:
      - [x] Sulkee sovelluksen

## Jatkokehitysideoita

Peliä tullaan jatkokehittämään seuraavasti:

- Tasojen lisääminen
- Eri pelimuotojen lisääminen (endless, 2-player, yms.)
- Äänimaailman kehittäminen
- Tarinan luonti pelille
- Grafiikan parantaminen
