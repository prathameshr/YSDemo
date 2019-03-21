

# Spring Boot Application 

Spring Boot Starter Application with simple Rest Controller.

##Getting Started

POSTMAN is used for testing APIs
```
Install Postman from here -> https://www.getpostman.com/
```

###Please run application in Eclipse or Spring Tool Suite.

After starting the application, enter below url in browser for Swagger Definitions.
```
http://localhost:8080/swagger-ui.html#/contact-api
```

###For executing test cases using command prompt, Run 

```
gradlew clean test
```


###For creating war arrtifactory for deployment, Run 

```
gradlew clean build
```


###Embedded DB is used for storing contacts and their details.

Once the application is stopped, all contact details will get deleted.

In real life scenario, MYSQL or MongoDB can be used.


##Below Operations can be performed using POSTMAN:

###1. Fetch all Contacts - GET http://localhost:8080/v1/contact

```
Response:
[
    {
        "firstName": "John",
        "lastName": "Wick",
        "phoneNumber": 999000999,
        "address": "Dublin",
        "emailAddress": "john.wick@gmail.com",
        "id": 1
    },
    {
        "firstName": "Peter",
        "lastName": "Parker",
        "phoneNumber": 888000999,
        "address": "London",
        "emailAddress": "peter.parker@gmail.com",
        "id": 2
    }
]
```

###2. Fetch Contact by Id - GET http://localhost:8080/v1/contact/1

```
Response:
{
    "firstName": "John",
    "lastName": "Wick",
    "phoneNumber": 999000999,
    "address": "Dublin",
    "emailAddress": "john.wick@gmail.com",
    "id": 1
}
```

###3. Fetch Contact by First Name - GET http://localhost:8080/v1/contact?firstname=John
(Case Sensitive)

```
Response:

[
    {
        "firstName": "John",
        "lastName": "Wick",
        "phoneNumber": 999000999,
        "address": "Dublin",
        "emailAddress": "john.wick@gmail.com",
        "id": 1
    }
]
```

###4. Fetch Contact by Last Name - GET http://localhost:8080/v1/contact?lastname=Parker
(Case Sensitive)

```
Response:

[
    {
        "firstName": "Peter",
        "lastName": "Parker",
        "phoneNumber": 888000999,
        "address": "London",
        "emailAddress": "peter.parker@gmail.com",
        "id": 2
    }
]
```

###5. Create a new Contact - POST http://localhost:8080/v1/contact

Pass below payload as raw body

{
  "firstName": "Bruce",
  "lastName": "Wayne",
  "phoneNumber": "909022223",
  "address": "Dublin",
  "emailAddress": "sample@gmail.com"
}


```
Response: 

{
    "firstName": "Bruce",
    "lastName": "Wayne",
    "phoneNumber": 909022223,
    "address": "Dublin",
    "emailAddress": "sample@gmail.com",
    "id": 3
}
```

###6. Update an existing Contact - PUT http://localhost:8080/v1/contact

Pass below payload as raw body (Don't forget to mention contact id for update)

{
  "id": 3,
  "firstName": "Bruce",
  "lastName": "Wayne",
  "phoneNumber": "555555555",
  "address": "Dublin",
  "emailAddress": "hero@gmail.com"
}

```
Response:

{
    "firstName": "Bruce",
    "lastName": "Wayne",
    "phoneNumber": 555555555,
    "address": "Dublin",
    "emailAddress": "hero@gmail.com",
    "id": 3
}
```

###7. Delete an existing contact - DELETE http://localhost:8080/v1/contact/3

```
Response:

Contact 3 is deleted!
```
