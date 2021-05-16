![Untitled Diagram](https://user-images.githubusercontent.com/46067482/118410857-47c14780-b69a-11eb-9769-11cc7d34fb31.jpg)
# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne:

![pakkausrakenne(1)](https://user-images.githubusercontent.com/46067482/118401407-411cdb00-b66e-11eb-95fe-3488a6a48aac.jpg)

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

Sovelluksella on vain yhden tyyppisiä käyttäjiä: pelaaja. Pelaajaa varten ei ole luotu omaa luokkaa, koska sovellus on suunniteltu monen ihmisen käytettäväksi yhdellä laitteella. Sovelluslogiikka muodostuu abstraktista luokasta _Shape_ ja sen perivistä luokista, _BulletCollisionHandler_ luokasta, _ShapeRemover_ luokasta, _HighScore_ luokasta ja _HighScoreService_ luokasta.

Abstrakti _Shape_ luokka ja sen perivät luokat muodostavat pelin hahmot, ammukset ja seinät:
- _PlayerShip_
  - pelaajan hahmo
- _PlayerBullet_ 
  - pelaajan ammukset
- _EnemyShip_ 
  - vihollisen hahmo 
- _EnemyBullet_ 
  - vihollisen ammukset
- _GameWall_ 
  - seinät eli esteet

_BulletCollisionHandler_ luokka hoitaa luotien kohteeseen osumisen toiminnot. Esim. tarkistaa onko luoti osunut viholliseen ja merkitsee vihollisen kuolleeksi ja luodin kuolleeksi.

_ShapeRemover_ luokka hoitaa kuolleiden tai ruudun ulkopuolelle liikkuneiden hahmojen poistamisen.

_HighScore_ luokkaa käytetään uusien ennätys-olioiden luomiseen

_HighScoreService_ luookka hoitaa ennätysten käsittelyn. Esim. tallentamisen tietokantaan ja hakemisen tietokannasta.

<pre>
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
</pre>

<pre>
_HighScore_ luokan metodit:
- String getName()
- String getTime()
- String getPoints()
</pre>

<pre>
_HighScoreService_ luokan metodit:
- boolean addNewHighScore(HighScore highScore)
  - palauttaa true jos ennätyksen lisääminen tietokantaan onnistui, muuten false
- BatchGetValuesResponse getAllHighScores()
  - hakee tietokannasta kaikki ennätykset
- void writeScoresToTable(TableView<HighScore> hsTable)
  - kirjoittaa ennätykset parametrina annettuun ennätyslistaan
</pre>

<pre>
_BulletCollisionHandler_ luokan metodit:
- void handlePlayerShots()
- void handleEnemyShots()
  - molemmat luokat saavat parametrina useita listoja ja pelinäkymän 
  - tarkistaa mikäli luodit ovat osuneet johonkin ja merkitsee luodit ja kohteen kuolleeksi
</pre>

<pre>
_ShapeRemover_ luokan metodit:
- removeAllShapes(ArrayList<PlayerBullet> playerBullets, ArrayList<EnemyBullet> enemyBullets, ArrayList<EnemyShip> enemyShips, ArrayList<GameWall> walls, GameUi gameUi)
- removeDeadPlayerShips(ArrayList<PlayerShip> playerShips, GameUi gameUi)
- removeDeadEnemyShips(ArrayList<EnemyShip> enemyShips, GameUi gameUi)
- removeDeadPlayerBullets(ArrayList<PlayerBullet> playerBullets, GameUi gameUi)
- removeDeadEnemyBullets(ArrayList<EnemyBullet> enemyBullets, GameUi gameUi)
- removeDeadWalls(ArrayList<GameWall> walls, GameUi gameUi)
  - kukin metodi saa parametrina listan olioista ja poistaa kaikki kyseisen tyyppisen kuolleet ja pelinäkymän ulkopuolella olevat oliot
</pre>

Sovelluksen toiminnallisuutta kuvaava luokka/pakkauskaavio:

![luokka_pakkauskaavio_final](https://user-images.githubusercontent.com/46067482/118403117-93152f00-b675-11eb-8b7c-982969b3aa55.jpg)

## Ennätysten tallentaminen - Google Sheets API

Pakkaus _spaceinvadersapp.dao_ sisältää tiedon pysyväistallennukseen liittyvät luokat. Pysyväistalennus tehdään internetiin Google Sheet API:n avulla _config.properties_ tiedostossa määriteltyyn tiedostoon.

Sovelluksen toteutuksessa on noudatettu DAO-mallia, ja tiedon tallennuksen hoitaa _HighScoreService_ luokka, joten tiedon tallennus on erotettu käyttöliittymästä.

Tässä tulee ottaa huomioon Google Sheets API:n vaatima OAuth 2.0 varmennus, jotta tietokanta toimisi oikein. Katso lisää esim. [tämän sivun](https://www.baeldung.com/google-sheets-java-client) kohta 3.


Tiedot tallennetaan _HighScoreUi_ luokan sisältämään TableView-taulukkoon HighScore-olioina hyödyntämällä _HighScore_ luokan metodeja.

## Päätoiminnallisuudet

Sekvenssikaavio pelin pelaamisesta.

![Untitled Diagram](https://user-images.githubusercontent.com/46067482/118410862-4d1e9200-b69a-11eb-8992-c39d1191893e.jpg)

Kun pelaaja on käynnistänyt pelin, niin käyttöliittymä rekisteröi pelaajan napinpainalluksia. AnimationTimer-luokka kutsuu sekvenssikaaviossa esitettyjä komentoja n. 100 kertaa sekunnissa. Eli sekvenssikaaviossa esitetty _ShapeRemover_ ja _BulletCollisionHandler_ luokkien toiminnot kutsutaan todellisuudessa joka kerta kun Animation Timer "päivittää" kerran. Pelaajan ampuminen ja liikkuminen toteutetaan vain pelaajan painaessa nappeja.
