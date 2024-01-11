
# Receipt Processor

Created two api endpoints for posting the reciept which returns an id and get the total points using that id. 
You will need to have maven installed on your system with java 17. 


## System Requirements 
- Spring Boot {spring-boot-version} requires Java 17 and is compatible up to and including Java 21. {spring-framework-docs}/[Spring Framework {spring-framework-version}] or above is also required.
- Maven 3.6.3 or later
- Tomcat 10.1 - servelet version 6.0

## Run Locally

Clone the project

```bash
  git clone https://github.com/dhruvsavla/fetch.git
```

Go to the project directory

```bash
  cd fetch
```

Build the application using Maven:

```bash
  mvn clean install
```

Run the application

```bash
  mvn spring-boot:run
```


## API Reference

#### POST receipt

```http
  POST /receipts/process
```
example:- 

"{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "   Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}"

returns id.

example:- 
{ "id": "7fb1377b-b223-49d9-a31a-5a02701dd310" }
#### Get total points from the receipt

```http
  GET /receipts/{id}/points
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `UUID` | **Required**. Id of item to fetch |


returns total points

example:- 
{ "points": 32 }

