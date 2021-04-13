# Ohte harjoitustyö - Space Invaders

Tämä ohjelma emuloi Space Invaders -peliä. Pelissä on toistaiseksi luotu alustava käyttöliittymä. Peliä ei itse vielä voi pelata.


## Dokumentaatio
[Vaatimusmäärittely](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Javan versio

Sovellus on toteutettu ja testattu Javan versiolla 11.

## Komentorivitoiminnot

### Sovelluksen käynnistäminen

Sovelluksen voi käynnistää komennolla

```
mvn compile exec:java -Dexec.mainClass=main.SpaceInvadersApplication
```

### Testaus

Toistaiseksi ohjelmassa on 4 testiä, jotka testaavat vain käyttöliitymän tekstejä. Testit voi suorittaa komennolla

```
mvn test
```

Testikattavuusraportin voi luoda komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella projektin juuresta löytyvästä tiedostosta **target/site/jacoco/index.html** tai komennolla

```
chromium-browser target/site/jacoco/index.html
(edellyttää Chromium-selaimen asennuksen)
```

