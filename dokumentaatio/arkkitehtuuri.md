# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa toistaiseksi kaksitasoista kerrosarkkitehtuuria ja on pakkausrakenteeltaan seuraavanlainen:

![pakkausrakenne](https://user-images.githubusercontent.com/46067482/116990283-8e4a9580-acdb-11eb-918d-4b807eb2ef0f.jpg)

Pakkaus _spaceinvadersapp.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän ja _spaceinvadersapp.domain_ puolestaan ohjelman sovelluslogiikan.

## Käyttöliittymä

Käyttöliittymä sisältää neljä erillistä näkymää
- main menu, eli päävalikko
- pelinäkymä
- asetukset
- high scoret, eli ennätykset

Joikainen edellä mainituista näkymistä on toteutettu omana [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-oliona. Näkymistä yksi kerrallaan on näkyvänä eli sijoitettuna sovelluksen [stageen](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html). Käyttöliittymä on toteutettu kokonaisuudessaan luokassa [spaceinvadersapp.ui.SpaceInvadersUi](https://github.com/asianomainen/ot-harjoitustyo/blob/master/SpaceInvaders/src/main/java/spaceinvadersapp/ui/SpaceInvadersUi.java).

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta. Se ainoastaan kutsuu sovelluslogiikkaa toteuttavien, abstraktin _Shape_ luokan, metodeja.

## Sovelluslogiikka

Sovelluslogiikka muodostuu abstraktista luokasta _Shape_ ja sen perivistä luokista
- PlayerShip (pelaajan hahmo)
- PlayerBullet (pelaajan ammukset)
- EnemyShip (vihollisen hahmo)
- EnemyBullet (vihollisen ammukset)
- BossEnemyShip (päävihollisen hahmo)

![Luokkakaavio](https://user-images.githubusercontent.com/46067482/116994989-29df0480-ace2-11eb-8a99-48fdcf79d353.jpg)



## Sekvenssikaavio

Alustava sekvenssikaavio pelin pelaamisesta. Sekvenssikaavio esittää uuden pelin aloittamista, liikettä ja ampumista:

![SpaceInvadersSeuqnce](https://user-images.githubusercontent.com/46067482/116302653-201d4480-a7aa-11eb-813a-a5b405320db3.png)
