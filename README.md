
# Arbetsprov för DIGG

Det här projektet är en fullstack-applikation som består av en frontend byggd med **Vue.js** och en backend byggd med **Spring Boot**. Projektet är designat för att hantera användarinformation genom att kunna skapa, redigera, radera och visa användare. Det är containeriserat med **Docker**, vilket gör det lättare att köra applikationen utan att manuellt installera alla beroenden på din dator.

## Innehållsförteckning

1.  [Projektets struktur](#projektets-struktur)
2.  [Teknologier som används](#teknologier-som-anv%C3%A4nds)
3.  [Förutsättningar](#f%C3%B6ruts%C3%A4ttningar)
4.  [Installation och körning](#installation-och-k%C3%B6rning)
    1.  [Använda Docker](#anv%C3%A4nda-docker)
    2.  [Köra frontend och backend lokalt](#k%C3%B6ra-frontend-och-backend-lokalt)
5.  [Testning](#testning)
6.  [API-dokumentation](#api-dokumentation)
7.  [Vanliga problem och felsökning](#vanliga-problem-och-fels%C3%B6kning)
8.  [Framtida förbättringar](#framtida-f%C3%B6rb%C3%A4ttringar)
9.  [Licens](#licens)

## Projektets struktur

Applikationen är uppdelad i två huvuddelar:

1.  **Frontend** - Användargränssnittet som körs i webbläsaren. Byggt med Vue.js och hanteras av **Nginx** i produktionsmiljö.
2.  **Backend** - API och datalogik som hanteras av Spring Boot med Java. Det hanterar alla API-anrop för att skapa, uppdatera, radera och visa användare.

Projektets struktur ser ut så här:

bash

Copy code

digg-arbetsprov/
│
├── frontend/              # Vue.js frontend-kod
│   ├── src/               # Källkod för frontend
│   └── Dockerfile         # Dockerfil för frontend
│
├── backend/               # Spring Boot backend-kod
│   ├── src/               # Källkod för backend
│   └── Dockerfile         # Dockerfil för backend
│
├── docker-compose.yml      # Docker Compose-fil för att köra hela applikationen
├── README.md               # Dokumentationen (den här filen)
└── ...


## Teknologier som används

-   **Vue.js** - Ett JavaScript-ramverk för att bygga användargränssnitt.
-   **Spring Boot** - En Java-baserad ramverk för backend som gör det enkelt att skapa REST API
    
    .
-   **Docker** - Används för att paketera applikationen i containrar, så att det är enkelt att köra applikationen utan att behöva konfigurera alla verktyg själv.
-   **JUnit** - Används för att skriva och köra enhetstester för backend.

## Förutsättningar

Innan du kan köra det här projektet behöver du ha följande installerat:

1.  **Docker** - Installera Docker
2.  **Docker Compose** - Följer ofta med när du installerar Docker.
3.  (Valfritt) **Node.js och npm** - Installera Node.js (om du vill köra frontend utan Docker)
4.  (Valfritt) **Java 17 och Maven** - [Installera Java](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) och [Maven](https://maven.apache.org/download.cgi) (om du vill köra backend utan Docker)

## Installation och körning

### Använda Docker

#### 1. Klona projektet från GitHub

Först måste du klona projektet till din lokala dator. Detta gör du genom att köra följande kommando i en terminal:

bash

Copy code

`git clone https://github.com/ditt-konto/digg-arbetsprov.git
cd digg-arbetsprov` 

#### 2. Bygg och starta applikationen med Docker Compose

För att bygga och köra hela applikationen (både frontend och backend) samtidigt, kör detta kommando i projektets rotmapp:

bash

Copy code

`docker-compose up --build` 

-   Detta kommando kommer att bygga frontend och backend från grunden.
-   När detta är klart kan du öppna webbläsaren och besöka [http://localhost](http://localhost) för att se frontend.
-   Backend-API
    
    körs på [http://localhost:8080](http://localhost:8080).

#### 3. Kontrollera att applikationen körs

När applikationen har startat kan du öppna din webbläsare och gå till:

-   **Frontend:** [http://localhost](http://localhost)
-   **Backend API:** [http://localhost:8080](http://localhost:8080)

#### Stoppa applikationen

För att stänga ner applikationen, tryck `CTRL + C` i terminalen där Docker Compose körs. Du kan också stänga ner alla Docker-containrar med:

bash

Copy code

`docker-compose down` 

### Köra frontend och backend lokalt

Om du föredrar att köra frontend och backend utan Docker kan du göra det med följande steg:

#### Backend

1.  Gå till backend-katalogen:
    
    bash
    
    Copy code
    
    `cd backend` 
    
2.  Bygg och kör backend med Maven (du måste ha Java och Maven installerat):
    
    bash
    
    Copy code
    
    `mvn clean package
    java -jar target/backend.jar` 
    

Backend kommer nu att köra på [http://localhost:8080](http://localhost:8080).

#### Frontend

1.  Gå till frontend-katalogen:
    
    bash
    
    Copy code
    
    `cd frontend` 
    
2.  Installera beroenden (du måste ha Node.js och npm installerat):
    
    bash
    
    Copy code
    
    `npm install` 
    
3.  Starta utvecklingsservern:
    
    bash
    
    Copy code
    
    `npm run dev` 
    

Frontend kommer nu att köra på http://localhost:3000.

## Testning

### Backend-enhetstester

Det finns enhetstester för backend som är skrivna med **JUnit**. Dessa tester verifierar att backenden fungerar som förväntat. För att köra testerna, gå till backend-katalogen och kör:

bash

Copy code

`mvn test` 

Om alla tester lyckas, kommer du att se meddelandet "BUILD SUCCESS" i terminalen.

### Enhetstester som täcks

1.  Hämta alla användare (GET /digg/user)
2.  Skapa ny användare (POST /digg/newuser)
3.  Uppdatera användare (PUT /digg/edituser)
4.  Radera användare (DELETE /digg/deleteuser)

## API-dokumentation

Backend tillhandahåller ett REST API som gör det möjligt att interagera med användardata. Här är en översikt av de API-slutpunkter som finns:

1.  **GET /digg/user** - Hämtar alla användare.
2.  **POST /digg/newuser** - Skapar en ny användare.
3.  **PUT /digg/edituser** - Uppdaterar en befintlig användare.
4.  **DELETE /digg/deleteuser** - Raderar en användare.

Alla anrop returnerar data i **JSON**-format.

## Vanliga problem och felsökning

### Problem: "Port already in use"

Om du får ett felmeddelande om att en port redan används, kan det bero på att en annan applikation använder port 80 eller 8080. För att lösa detta kan du:

1.  Stänga den andra applikationen som använder porten.
2.  Ändra porten i `docker-compose.yml`-filen eller i dina Docker-filer.

### Problem: "Connection refused" när man försöker nå backend

Detta kan bero på att backend inte har startat korrekt. Kontrollera backend-loggarna i terminalen där du kör Docker eller Maven för eventuella felmeddelanden.

## Framtida förbättringar

-   Implementera fler valideringar både på klient- och serversidan.
-   Lägg till fler enhetstester, inklusive integrationstester.
-   Skapa en CI/CD-pipeline för automatiserade tester och distribution.

## Licens

Det här projektet har ingen specifik licens i nuläget, men du kan använda det för utbildningssyfte eller som referens.
