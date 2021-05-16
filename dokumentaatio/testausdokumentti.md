# Testausdokumentti

Ohjelmaa on testattu sekä automaattisten JUnit testien toimesta että manuaalisesti sovellusta käyttämällä.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Kaikki sovelluslogiikkaan liittyvän luokat testataan automaattisesti JUnit-testeillä. 

JUnit testeillä suoritetaan sekä yksinkertaisia yksikkötestejä että monimutkaisempia sovelluslogiikkaa ja käyttöliittymää testaavia integraatiotestejä.

Integraatiotesteissä testataan myös DAO-luokkien mahdollistamaan tiedon pysyväistallenusta.

### DAO-luokat

DAO-luokkia testataan käyttämällä testejä varten erikseen luotua [Google Sheets -tiedostoa](https://docs.google.com/spreadsheets/d/1UiR0FVs0VkZORRFrIKDxXBdfbB2KdH9u_1LDZIw0vNg/edit#gid=0)

### Testauskattavuus

Testeissä ei oteta huomioon käyttöliittymän rakentavaa ui-pakkausta. Testien rivikattavuus on 97% ja haarautumakattavuus 73%. Haarautumakattavuuden puuteet johtuvat _ShapeRemover_ luokan metodeista.

![testit](https://user-images.githubusercontent.com/46067482/118409733-953ab600-b694-11eb-838b-3f81e016b7ee.png)

Testaamatta jäivät tilanteet, joissa käyttäjät tai tehtävät tallettavia tiedostoja ei ole, tai niihin ei ole luku- ja kirjoitusoikeutta.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Google Sheets API on tuottanut erityistä päänvaivaa sovellusta kehitettäessä. Se vaatii erityisiä toimenpiteitä, joiden tekemättä jättäminen saattaa johtaa sovelluksessa virhetilanteisiin. Sovellus on testattu Linux-käyttöjärjestelmällä [käyttöohjeiden](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) mukaisesti ja sen on todettu toimivan oikein.

Sovellusta on testattu sekä _config.properties_ tiedostossa määritellyllä vakiotiedostolla, sekä uudella käyttäjän määrittämällä tiedostolla.

### Toiminnallisuudet

Kaikki [määrittelydokumentissa](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) ja [käyttöohjeessa](https://github.com/asianomainen/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) listatut tiedot on tarkistettu oikeellisiksi ja todettu toimiviksi.

## Sovellukseen jääneet laatuongelmat

Sovelluksessa ei ole virheilmoituksia ja sen sijaan kehittäjä (minä) on uljaasti luottanut taitoihinsa ja todennut, että virheilmoituksia ei tule mikäli käyttöohjeita on noudatettu oikein.

Jostain syystä jar-tiedoston käynnistäminen ei onnistu mikäli sovelluksen konfiguraatiot on määritelty config.properties tiedostossa. Sovellus käynnistyy normaalisti ohjelmointiympäristössä, mutta jar-tiedostoa käynnistäessä tulee virheilmoitus, että kyseistä tiedostoa ei ole olemassa. Tästä syystä Google Sheets -dokumentin tunniste on kovakoodattu ohjelman koodiin.
