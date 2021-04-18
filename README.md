# Ohte harjoitustyö - Space Invaders

Tämä ohjelma emuloi Space Invaders -peliä. Pelissä on toistaiseksi luotu alustava käyttöliittymä. Peliä ei itse vielä voi pelata.


## Dokumentaatio
[Vaatimusmäärittely](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Javan versio

Sovellus on toteutettu ja testattu Javan versiolla 11.

## Komentorivitoiminnot

Komentorivitoimintojen käyttäminen edellyttää niiden suorittamista **projektihakemistossa**.

### Sovelluksen käynnistäminen

Sovelluksen voi käynnistää komennolla

```
mvn compile exec:java -Dexec.mainClass=spaceinvadersapp.Main
```

### Testaus

Toistaiseksi ohjelmassa on 10 testiä, jotka testaavat abstraktin luokan Shape periviä PlayerShape- ja PlayerBullet-luokkia.

```
mvn test
```

Testikattavuusraportin voi luoda komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella projektin juuresta löytyvästä tiedostosta **target/site/jacoco/index.html** tai esimerkiksi Chromium-selaimessa komennolla (edellyttää Chromium-selaimen asennuksen)

```
chromium-browser target/site/jacoco/index.html
```

