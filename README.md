# Ohte harjoitustyö - Space Invaders

Tämä ohjelma emuloi Space Invaders -peliä. Peli on yksinpeli ja siinä on 5 vaikeutuvaa tasoa.


## Dokumentaatio
[Käyttöohje](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

## Releaset

[Viikko 5](https://github.com/asianomainen/ot-harjoitustyo/releases/tag/viikko5_v2)

[Viikko 6](https://github.com/asianomainen/ot-harjoitustyo/releases/tag/viikko6_v2)

[Loppupalautus](https://github.com/asianomainen/ot-harjoitustyo/releases/tag/loppupalautus) (katso ohjeet ohjelman käynnistämiseen [käyttöohjeista](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md))

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

Sovelluksen testit voi ajaa komennolla

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

Mahdolliset virheilmoitukset voi tarkistaa avaamalla selaimella tiedoston _target/site/checkstyle.html_


### JavaDoc

Sovelluksen JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDociin voi perehtyä avaamalla selaimella tiedoston _target/site/apidocs/index.html_
