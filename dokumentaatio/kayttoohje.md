# Käyttöohje

Lataa tiedosto [SpaceInvadersApplicationV6.jar](https://github.com/asianomainen/ot-harjoitustyo/releases/tag/viikko6_v2)

## Ohjelman käynnistäminen

Ohjelma käynnistetään siirtymällä ladatun jar-tiedoston sisältävään hakemistoon ja suorittamalla komennon

```
java -jar SpaceInvadersApplicationV6.jar
```
## Main menu

Sovellus käynnistyy main menuun eli päävalikkoon:

![MainMenu](https://user-images.githubusercontent.com/46067482/117039263-40e91b00-ad11-11eb-92c5-ed98d769e9d9.png)

Päävalikosta pääsee
- peliin (Start Game)
- asetuksiin (Settings)
- high score -listalle (High Scores)
- pois pelistä (Exit)

Navigointi tapahtuu hiirellä tai nuolinäppäimillä/tab-näppäimellä ja ENTER-nappia painamalla.

## Peli

Pelissä on toistaiseksi
- 1 pelaaja
- 50 vihollista / taso
  - taso ei vielä vaihdu vihollisten kuoltua
- pistemäärä
  - pistemäärä kasvaa aina 100:lla kun yksi vihollinen tapetaan

Pelin näppäimet:
- vasen nuolinäppäin: pelaaja liikkuu vasemmalle
- oikea nuolinäppäin: pelaaja liikkuu oikealle 
- välilyönti (SPACE): pelaaja ampuu
- ESC-näppäin: sulkee pelin ja palaa päävalikkoon
  - toistaiseksi peli ei ala alusta mikäli peliin palataan päävalikkoon palaamisen jälkeen
  - peli alkaa alusta vain kun sovelluksen käynnistää uudelleen

![Game](https://user-images.githubusercontent.com/46067482/117040208-51e65c00-ad12-11eb-989c-b11f4ff7275b.png)

## Asetukset

Pelissä voi vaihtaa seuraavia asetuksia
- väriasetukset
  - muuttaa pelin värit käänteiseksi (ns. dark mode)
- väniasetukset
  - pelissä ei toistaiseksi ole ääniä, joten ääniasetuksen muuttaminen ei tee mitään

Päävalikkoon voi palata _Back to Main Menu_ näppäimellä

![Settings](https://user-images.githubusercontent.com/46067482/117041534-a807cf00-ad13-11eb-83f0-8cd13d2e09a9.png)

## High Score -lista

HUOM! high scoret eivät toistaiseksi tallennu listalle.

High Score -lista tallentaa ennätykset pistemäärän mukaan. Pelin läpäistyään pelaaja voi halutessaan tallentaa tuloksensa syöttämällä nimimerkin.

High Scoreja voi halutessaan järjestää

- nimen mukaan
- ajan mukaan
- pistemäärän mukaan

Päävalikkoon voi palata _Back to Main Menu_ näppäimellä

![HighScore](https://user-images.githubusercontent.com/46067482/117042225-5ca1f080-ad14-11eb-887f-5522ba8c2af6.png)
