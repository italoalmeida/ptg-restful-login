# ptg-restful-login

API RESTful for user authentication using Java, Spring and JWT.

## Root URL

https://ptg-restful-login.herokuapp.com/

## Endpoints

### User Registration

Registers a new user and returns its access token.

#### URL

/signup

#### Method:

  `POST`

#### Data Params:

    {
      "firstName": "Hello",
      "lastName": "World",
      "email": "hello@world.com",
      "password": "hunter2",
      "phones": [
        {
          "number": 988887888,
          "areaCode": 81,
          "countryCode": "+55"
        }
      ]
    }

#### Success Response:

###### Code: 200 OK

###### Content:

    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
    }

#### Error Response:

###### Code: 400 BAD REQUEST

###### Content:

    {
      "message": "E-mail already exists"
      "code": "400"
    }

OR

###### Code: 400 BAD REQUEST

###### Content:

    {
      "message": "Invalid fields"
      "code": "400"
    }
    
OR

###### Code: 400 BAD REQUEST

###### Content:

    {
      "message": "Missing fields"
      "code": "400"
    }

### User Authentication

Validates user credentials and returns its access token.

#### URL

/signin

#### Method:

  `POST`

#### Data Params:

    {
      "email": "hello@world.com",
      "password": "hunter2"
    }

#### Success Response:

###### Code: 200 OK

###### Content:

    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
    }

#### Error Response:

###### Code: 400 BAD REQUEST

###### Content:

    {
      "message": "Invalid e-mail or password"
      "code": "400"
    }

OR

###### Code: 400 BAD REQUEST

###### Content:

    {
      "message": "Missing fields"
      "code": "400"
    }

### User Details

Returns user details.

#### URL

/me

#### Method:

  `GET`

#### Success Response:

###### Code: 200 OK

###### Content:

    {
      "firstName": "Hello",
      "lastName": "World",
      "email": "hello@world.com",
      "phones": [
        {
          "number": 988887888,
          "areaCode": 81,
          "countryCode": "+55"
        }
      ],
      "createdAt": "2019-05-17T04:12:27.081+0000",
      "lastLogin": "2019-05-17T04:12:27.107+0000"
    }

#### Error Response:

###### Code: 401 UNAUTHORIZED

###### Content:

    {
      "message": "Unauthorized"
      "code": "401"
    }

OR

###### Code: 401 UNAUTHORIZED

###### Content:

    {
      "message": "Unauthorized - invalid session"
      "code": "401"
    }
