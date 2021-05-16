# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne:

![pakkausrakenne](https://user-images.githubusercontent.com/46067482/116990283-8e4a9580-acdb-11eb-918d-4b807eb2ef0f.jpg)

Pakkaus _spaceinvadersapp.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän, _spaceinvadersapp.domain_ ohjelman sovelluslogiikan ja _spaceinvadersapp.dao_ tietojen pysyväistallennuksen.

## Käyttöliittymä

Käyttöliittymä sisältää seitsemän erillistä näkymää:
- main menu, eli päävalikko
- pelinäkymä
- asetukset
- high scoret, eli ennätykset
- pelin aikainen pause menu, eli taukovalikko
- pelin jälkeinen ennätyksen tallentamiseen käytetty näkymä
- pelin jälkeinen uuden pelin aloittamiseen tai päävalikkoon palaamiseen käytetty näkymä

Käyttöliittymän toteutus on jaettu jokaista näkymää vastaavaan luokkaan ja näkymät alustetaan sovelluksen käynnistyessä.

## Sovelluslogiikka

Sovelluslogiikka muodostuu

- Abstraktista luokasta _Shape_ ja sen perivistä luokista
  - PlayerShip 
    - pelaajan hahmo
  - PlayerBullet 
    - pelaajan ammukset
  - EnemyShip 
    - vihollisen hahmo
  - EnemyBullet 
    - vihollisen ammukset
  - GameWall 
    - seinät eli esteet
- BulletCollisionHandler 
  - hoitaa luotien kohteeseen osumisen toiminnot
- ShapeRemover
  - hoitaa kuolleiden tai ruudun ulkopuolelle liikkuneiden hahmojen poistamisen
- HichScore
  - luo uuden ennätys-olion
- HighScoreService
  - hoitaa ennätysten käsittelyn

![Luokkakaavio](https://user-images.githubusercontent.com/46067482/116996687-7cb9bb80-ace4-11eb-83f0-3eea687b44d4.jpg)

Kyseisten luokkien toiminnot keskittyvät nimenomaan pelin toteuttamiseen ja ennätysten käsittelyyn.

Abstrakti luokka _Shape_ tarjoaa jokaiselle sen perivälle luokalle niiden tarvitsevat metodit:
- Polygon getShape()
  - palauttaa olion muodon
- void moveLeft()
- void moveRight()
- void moveRight()
- void moveUp()
- void moveDown()
- boolean outOfBounds()
  - palauttaa true jos olio on ruudun ulkopuolella, muuten false
- void setAlive(Boolean value)
- boolean isAlive()
- boolean collision(Shape target)
  - vertaa ovatko kyseinen olio ja parametrina annettu olio törmänneet toisiinsa
  - palauttaa true jos kyllä, muuten false

Abstraktin _Shape_ luokan ja sen perivien luokkien luokkakaavio:

![luokkakaavio](https://user-images.githubusercontent.com/46067482/117036766-76d8d000-ad0e-11eb-8648-6706a10ecdac.jpg)

_HighScore_ luokan metodit:
- String getName()
- String getTime()
- String getPoints()

_HighScoreService_ luokan metodit:
- boolean addNewHighScore(HighScore highScore)
  - palauttaa true jos ennätyksen lisääminen tietokantaan onnistui, muuten false
- BatchGetValuesResponse getAllHighScores()
  - hakee tietokannasta kaikki ennätykset
- void writeScoresToTable(TableView<HighScore> hsTable)
  - kirjoittaa ennätykset parametrina annettuun ennätyslistaan

_BulletCollisionHandler_ luokan metodit:
- void handlePlayerShots()
- void handleEnemyShots()
  - molemmat luokat saavat parametrina useita listoja ja pelinäkymän 
  - tarkistaa mikäli luodit ovat osuneet johonkin ja merkitsee luodit ja kohteen kuolleeksi

_ShapeRemover_ luokan metodit:
- removeAllShapes(ArrayList<PlayerBullet> playerBullets, ArrayList<EnemyBullet> enemyBullets, ArrayList<EnemyShip> enemyShips, ArrayList<GameWall> walls, GameUi gameUi)
- removeDeadPlayerShips(ArrayList<PlayerShip> playerShips, GameUi gameUi)
- removeDeadEnemyShips(ArrayList<EnemyShip> enemyShips, GameUi gameUi)
- removeDeadPlayerBullets(ArrayList<PlayerBullet> playerBullets, GameUi gameUi)
- removeDeadEnemyBullets(ArrayList<EnemyBullet> enemyBullets, GameUi gameUi)
- removeDeadWalls(ArrayList<GameWall> walls, GameUi gameUi)
  - kukin metodi saa parametrina listan olioista ja poistaa kaikki kyseisen tyyppisen kuolleet ja pelinäkymän ulkopuolella olevat oliot

## Ennätysten tallentaminen - Google Sheets API

Pakkaus _spaceinvadersapp.dao_ sisältää tiedon pysyväistallennukseen liittyvät luokat. Pysyväistalennus tehdään internetiin Google Sheet API:n avulla _config.properties_ tiedostossa määriteltyyn tiedostoon.

Sovelluksen toteutuksessa on noudatettu DAO-mallia, ja tiedon tallennuksen hoitaa _HighScoreService_ luokka, joten tiedon tallennus on erotettu käyttöliittymästä.

Tässä tulee ottaa huomioon Google Sheets API:n vaatima OAuth 2.0 varmennus, jotta tietokanta toimisi oikein. Katso lisää esim. [tämän sivun](https://www.baeldung.com/google-sheets-java-client) kohta 3.

Tiedot tallennetaan _HighScoreUi_ luokan sisältämään TableView-taulukkoon HighScore-olioina hyödyntämällä _HighScore_ luokan metodeja.

## Päätoiminnallisuudet

Alustava sekvenssikaavio pelin pelaamisesta. Sekvenssikaavio esittää uuden pelin aloittamista, liikettä ja ampumista:

![SpaceInvadersSeuqnce](https://user-images.githubusercontent.com/46067482/116302653-201d4480-a7aa-11eb-813a-a5b405320db3.png)
