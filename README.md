# MovieProxy

## Extras
I've basically translated it to english, updated/changed some dependencies to make it work on my setup.
I also did some little tweaks (it now saves the last downloaded video name) and other minor design changes

It will save the last downloaded title if the title matches the following regex
```regex
(.*) - s(\d+)e(\d+)\..*
```
some examples

- Pokemon - s1e001.mp4
- South Park - s02e03.mkv
- ~~ijfsdqoifhjqds.mp4~~
- ~~Pokemon s1 e001.mp4~~

## Preview
![preview](https://i.melijn.me/16d36d531f5)

## Installation

1. Download the jar file
2. Run the jar file "java -jar MovieProxy.jar"
3. Server will start on 0.0.0.0:8008
4. Access the server with localhost:8008

Alternatively, setup a dns and a nginx proxy pass so it's easier, you can use cloudflare for free ssl and hide your server ip. Contact me on discord for more info

## Download

Die Software kann auf meinem Jenkins heruntergeladen werden oder selbst kompiliert werden.  
Download: https://ci.howaner.de/job/MovieProxy/  
Mehr Informationen auf der [Homepage](https://www.movieproxy.de/installation/).

## Kompilierung

Das Projekt verwendet Maven als Build-Management-Software. Zum Kompilieren muss also nur der Maven Build gestartet werden.  
```mvn clean install```

## Verwendete Libraries

- Netty
- Gson
- Guava
- Lombok
- ~~Log4j~~ logback
- Junit
