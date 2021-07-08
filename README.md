Spustenie servisu:

    gradle clean build jar

    docker-compose up

---------------------------------
Tabulka s geolokaciou je v databáze, názov - 'geoip'

---------------------------------
http://localhost:8080/ringier/login  - prijíma Post request v json formáte a, na základe IP adresy, pridáva štat ku ktorému adresa patrí.
Príklad requestu:

{
  "ts": 1614281031,
  "ip": "186.152.12.120"
}

---------------------------------
http://localhost:8080/ringier/stat  - servis posiela dáta dennej agregácie prihlásení pre krajiny, usporiadané podľa počtu od najväčšieho, s možnosťou vybrať napr. top3, top5 krajín.
Príklad requestu:

{
  "date":"2021-06-17",
  "top":10
}

    date - dátum prihlásení
    top  - počet štátov,

---------------------------------
http://localhost:8080/ringier/addip  - servis generuje náhodne prehlásenia používateľov.
Príklad requestu:

{
  "startIP":"112.156.150.62",
  "endIP":"149.2.17.3",
  "count":50
}

    startIP - začiatok rozsahu
    endIP   - koniec rozsahu
    count   - množstvo adries