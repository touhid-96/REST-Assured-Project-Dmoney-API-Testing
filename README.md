# REST Assured Project Dmoney API Testing

## Technology and Tool Used
- REST Assured
- Commons Configuration
- Jackson Databind
- TestNG
- Java
- Gradle
- Intellij idea

## Collection
- https://documenter.getpostman.com/view/32159205/2sA35MzeiK

## Scenario
- Do Login by admin
- Create 2 new customers and an agent
- Give 2000 tk from System account to the newly created agent
- Deposit 1500 tk to a customer from the agent account
- Withdraw 500 tk by the customer to the agent
- Send money 500 tk to another customer
- Payment 100 tk to a merchant (01712121212) by the recipient customer
- Check balance of the recipient customer

## Prerequisite
- JDK 11 or higher
- java IDE
- Configure environment variable for Java, Gradle and Allure Report

## How to run this project
- Clone the project
- Execute the following command on the project directory : `gradle clean test`

## Generate Allure Report
- Execute the following commands (after finishing the project run)

  1. `allure generate allure-results --clean`
  2. `allure serve allure-results --clean`

## Allure Reports
![1 overview](https://github.com/touhid-96/REST-Assured-Project-Dmoney-API-Testing/assets/29010371/f92f1ae5-42fb-4dd0-b652-894f6a6c2649)
![2 suites](https://github.com/touhid-96/REST-Assured-Project-Dmoney-API-Testing/assets/29010371/c52295df-fb9f-4990-a94d-d537308d61c7)
![3 graph](https://github.com/touhid-96/REST-Assured-Project-Dmoney-API-Testing/assets/29010371/93aad822-e71c-4b5c-989e-3f11e4f26559)
![4 behaviors](https://github.com/touhid-96/REST-Assured-Project-Dmoney-API-Testing/assets/29010371/1188fbe0-9bcb-4e72-8c15-e95fcea87412)
![5 packages](https://github.com/touhid-96/REST-Assured-Project-Dmoney-API-Testing/assets/29010371/6acd6309-9e45-4e81-9502-5169441dfa6d)
