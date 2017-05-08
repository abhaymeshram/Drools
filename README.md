# Drools

Apply Rules Rest Service

URL : http://localhost:8080/drool-rest/applyRules
Method : POST
Content-Type: application/json

1) Body for experience less than 3 ## please check the value of experience, designation and salary ##

{
  "id": 10,
  "firstName": "Abhay",
  "lastName": "Meshram",
  "age": 30,
  "designation": null,
  "salary": 0,
  "experience": 2.9
}

Rules Response : 

{
  "id": 10,
  "firstName": "Abhay",
  "lastName": "Meshram",
  "age": 30,
  "designation": "Software Engineer",
  "salary": 30000,
  "experience": 2.9
}

2) Body for experience greater than 3 ## please check the value of experience, designation and salary ##

{
  "id": 10,
  "firstName": "Abhay",
  "lastName": "Meshram",
  "age": 30,
  "designation": null,
  "salary": 0,
  "experience": 3.1
}

Rules Response : 

{
  "id": 10,
  "firstName": "Abhay",
  "lastName": "Meshram",
  "age": 30,
  "designation": "Senior Software Engineer",
  "salary": 50000,
  "experience": 3.1
}
