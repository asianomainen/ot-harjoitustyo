# Arkkitehtuurikuvaus

## Rakenne

Ohjelman rakenne noudattaa toistaiseksi kaksitasoista kerrosarkkitehtuuria ja on pakkausrakenteeltaan seuraavanlainen:

![pakkausrakenne](https://user-images.githubusercontent.com/46067482/116990283-8e4a9580-acdb-11eb-918d-4b807eb2ef0f.jpg)

Pakkaus _spaceinvadersapp.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän ja _spaceinvadersapp.domain_ puolestaan ohjelman sovelluslogiikan.

## Luokkakaavio

Alustava luokkakaavio luokista ja niiden periytymisestä:

![SpaceInvaders](https://user-images.githubusercontent.com/46067482/115159469-e6f31f00-a09b-11eb-8522-7efa0471c790.jpg)


## Sekvenssikaavio

Alustava sekvenssikaavio pelin pelaamisesta. Sekvenssikaavio esittää uuden pelin aloittamista, liikettä ja ampumista:

![SpaceInvadersSeuqnce](https://user-images.githubusercontent.com/46067482/116302653-201d4480-a7aa-11eb-813a-a5b405320db3.png)
