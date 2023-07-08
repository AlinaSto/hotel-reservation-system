
# Project Title
Hotel Reservation App


# Hi, I'm Alina! 👋

And here you can find the documentation of the Hotel Reservation App. project
## 🚀 About Me
I'm a junior back-end developer


## 🛠 Skills
Java, Spring Boot, OOP, MySQL, Rest APIs, HTML, CSS.


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://katherineoelsner.com/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/)


# Hotel Reservation App.

This application can be used by any hotel which needs a digital reservation system, and allows users to make reservation  for their favorite hotel.
The user has the posibility to register if is a new user or authenticate in the application if he already made an account in the hotel application in the past.
When the user is getting registred in the app a token(containing an username and a password) is generated and everytime when he want to authenticate the token is validate beeing compared with the new username and password introduced by himself.
In addition, compared to other applications of this kind, customers can receive recommendations of tourist attractions in the city they want to visit with the help of artificial intelligence based on the API from OpenAI


## Features

As a client, I can:
- register or authenticate,
- view all hotels,
- view all available rooms from a hotel,
- view available rooms ordered by price,
- view prices for all reservation,
- view all reviews by hotel,
- make a room reservation,
- add a review to a hotel after check-out.


As an admin, I can:
- add/delete hotel;
- add/delete rooms to/from hotel,
- update room price.



## Built with

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

## Demo

You can view the demo here:

(insert link to video demo)


## API Reference

#### Add a new hotel room

```http
  Post /room/add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of room to be added  |

Request body example:

```json
{
  "extraPrices": [
    {
      "roomNumber": " ",
      "pricePerNight": " ",
      "numberOfPerson": " "
    }
```  

#### Update price per room

```http
  PUT /room/update/${roomHotelId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of hotel room to update |
| `body` | `json` | **Required**.  The properties of room to be updated |

Request body example:

```json
{
    "id": 6,
    "roomNumber": "3",
    "pricePerNight": 58,
    "numberOfPerson": 3,
    "roomRezervationjoinTable": []
}



## API Authentication and Authorization

There are only two requests which don't require authorization headers.

#### Authenticate (login)

```http
  POST /authenticate
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to authenticate  |

Request body example:

```json
{
  "password": "string",
  "username": "string"
}
```  

#### Register

```http
  POST /authenticate
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to register  |

Request body example:

```json
{
  {
  "email": "string",
  "password": "string",
  "username": "string"
}
}
```  
#### Add Hotel

```http
  POST /add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of hotel to add related info |

Request body example:

```json
{
{
 "name": "string",
 "noOfRooms": "string"


}
}
```  
#### Add Reservation

```http
  POST /add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of reservation to add related info |

Request body example:

```json
{
{
    "userId": no,
    "roomIds": "string",
    "checkIn": "date",
    "checkOut": "date"
  
}
}
``` 
#### Get All Rooms From Hotel

```http
  Get /get
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The hotel name  |

Request body example:

```json
{
{
 "name": "Kronwell"
}
}
``` 
#### Add Room To Hotel

```http
  POST /add
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The room parameters  |

Request body example:

```json
{
{
 "roomNumber": "3",
  "pricePerNight": "580",
   "numberOfPerson": "3"
}
}
``` 
After running the authenticate request, the client will obtain an access token that will be used in all subsequent request in order to authenticate the user and to authorize the user based on its role.

This is an example of what should be included in the request header:

```http
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcxMTQzMzEyfQ.dxIzsD9Bm8y_kw3MOoZ2JXIKOg--uZaA5XNtBLdGYc4Ps3nlzBFDwBJi0bEeHlCggonZ6nQ2zwCI0D5a7dXjmw
```  
## Prerequisites

For building and running the application you need:
- JDK 1.8 or higher
- Maven 3

For deploying on Heroku you need:
- GIT
- Heroku CLI
## Dependencies

You don't need any additional dependencies.
All dependecies related to database management, server management, security management and so on, will be automatically injected by Maven using the pom.xml file located in the root folder of the project.
## Installation

Clone the project

```bash
  git clone https://github.com/AlinaSto/hotel-reservation-system
```

Go to the project directory


## Run Locally

Use maven to build the app and, to run it, and to start the local embedded Tomcat server

```bash
  mvn spring-boot:run
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```


## Environment Variables

You can deploy this project using Heroku or other providers as well, by specifying the following environment variables:

`PROFILE`

`MYSQL_URL`

`MYSQL_USER`

`MYSQL_PASS`



## Deployment

To deploy this project run

```bash
  git push heroku master
```


## Usage

You cand use the a demo version of the app, using SwaggerUI and following this link:

```javascript
https://obscure-peal.heroku.app/swagger-ui/
```

## Badges


![Maintained](https://img.shields.io/badge/Maintained%3F-yes-green.svg)
![GIT](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![Heroku](https://img.shields.io/badge/heroku-%23430098.svg?style=for-the-badge&logo=heroku&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![JWT](https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)
