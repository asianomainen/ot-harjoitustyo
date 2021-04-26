# Ohte harjoitustyö - Space Invaders

Tämä ohjelma emuloi Space Invaders -peliä. Peli on yksinpeli ja siinä on 5 vaikeutuvaa tasoa.


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

### Suoritettavan jar-tiedoston luominen

jar-tiedosto luodaan komennolla

```
mvn package
```

Luotu tiedosto löytyy target-hakemistosta. jar-tiedoston pystyy käynnistämään komennolla

```
java -jar target/SpaceInvadersApplication-1.0-SNAPSHOT.jar
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

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/asianomainen/ot-harjoitustyo/blob/master/SpaceInvaders/checkstyle.xml) määritellyt, koodin luettavuuteen liittyvät, tarkistukset voi tehdä komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Virheilmoitukset voi tarkistaa avaamalla selaimella tiedoston _target/site/checkstyle.html_
