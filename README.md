# Overview

Players can play this kalaha game via web based user interface developed in Angular module and backend application developed in Spring Boot. To start playing the game, we have to start both front end and backend application.



## Technologies Used

| Component                 | Technology           |
| -------------             |:-------------:       |
| Frontend                  | Angular 14           |
| Backend                   | Spring Boot 3.14     |
| Frontend Build tools      | angular-cli, npm     |
| Backend  Build tools      | angular-cli, npm     |

## Kalaha Server

The backend application is devloped in Spring Boot layered architecture styles.

    1. Presentation Layer
    2. Business Layer
    3. Persistence Layer
    
### Design Pattern

Game flow has been implemented through chain of responsibility pattern princliple and Ordered interface offered by spring framework.
    
    There are two flows in the application. They  are:
        1. Start Game Flow
        2. Play Game Flow

#### Start Game Sequence Diagram:

![GameController_startGame](https://github.com/GKSSN/kalaha_game/assets/118438039/d5c1b8be-f469-4308-9b39-8565521f5479)

#### Play Game Sequence Diagram:

![GameController_sowStones](https://github.com/GKSSN/kalaha_game/assets/118438039/5cf1a57f-e6df-475a-a6c9-bbfbaf131c15)

#### Documentation

For the API documentaion, i have used swagger. It will be available in http://localhost:8080/swagger-ui/index.html

#### Tests

All the bussiness scenarios are tested through junit test case and manual testing. Sonar code analysis attached for reference.

![Sonar_Analysis](https://github.com/GKSSN/kalaha_game/assets/118438039/1bde9354-348f-4397-97ef-160f1083fd44)

## Kalaha Client

UI component is built with angulalr 8 which runs on node.js. I have created two components / pages.

    1. Start Game Component - To get the player names
    2. Play Game Component - To play the game

#### Start Game Component

![StartGame](https://github.com/GKSSN/kalaha_game/assets/118438039/b94430f3-01bd-4e9c-8fbd-db56a1fc8388)

#### Play Game Component

![PlayGame](https://github.com/GKSSN/kalaha_game/assets/118438039/d3d7f71f-fa39-48ee-aaa2-6d381cac7f14)


#### Game Result

![GameResult](https://github.com/GKSSN/kalaha_game/assets/118438039/25005081-650c-4682-99bd-db2486a90987)

## Build & Run

Backend Application:

Navigate to the kalaha-game-ui module folder and run following command and access the application through any Rest client tool http://localhost:8080/ or http://localhost:8080/swagger-ui/index.html

        mvn spring-boot:run
Frontend Application:

Navigate to the kalaha-service module folder and run following command and access the application through http://localhost:4200/

        ng serve


