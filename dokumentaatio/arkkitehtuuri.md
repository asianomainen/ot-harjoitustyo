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

![Luokkakaavio](https://user-images.githubusercontent.com/46067482/116996687-7cb9bb80-ace4-11eb-83f0-3eea687b44d4.jpg)

Kyseisten luokkien toiminnot keskittyvät yksinomaan itse pelin toteuttamiseen.

Abstrakti luokka _Shape_ tarjoaa jokaiselle sen perivälle luokalle niiden tarvitsevat metodit
- Polygon getShape()
  - palauttaa olion muodon
- void moveLeft()
  - liikuttaa oliota vasemmalle
- void moveRight()
  - liikuttaa oliota oikealle
- void moveRight()
  - liikuttaa oliota oikealle
- void moveUp()
  - liikuttaa oliota ylös
- void moveDown()
  - liikuttaa oliota alas
- boolean outOfBounds()
  - palauttaa true jos olio on ruudun ulkopuolella, muuten false
- void setAlive(Boolean value)
  - määrittää onko olio elossa (true) vai kuollut (false)
- boolean isAlive()
  - palauttaa true jos olio on elossa, muuten false
- boolean collision(Shape target)
  - vertaa ovatko kyseinen olio ja parametrina annettu olio törmänneet toisiinsa
  - palauttaa true jos kyllä, muuten false

Abstraktin _Shape_ luokan ja sen perivien luokkien suhdetta kuvaava luokka/pakkauskaavio:

![luokka_pakkauskaavio](https://user-images.githubusercontent.com/46067482/117036766-76d8d000-ad0e-11eb-8648-6706a10ecdac.jpg)

## Päätoiminnallisuudet

Alustava sekvenssikaavio pelin pelaamisesta. Sekvenssikaavio esittää uuden pelin aloittamista, liikettä ja ampumista:

![SpaceInvadersSeuqnce](https://user-images.githubusercontent.com/46067482/116302653-201d4480-a7aa-11eb-813a-a5b405320db3.png)
