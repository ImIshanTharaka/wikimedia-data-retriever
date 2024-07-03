# wikimedia-data-retriever
An application to retrieve real-time stream data from Wikimedia using Apache Kafka and save them in a MySQL database.

## Specifications
This is a multi model maven project. There is a parent microservice (wikimedia-data-retriever) and 2 child microservices.
1. kafka-producer-wikimedia - read real-time stream data from Wikimedia and write them to the Kafka broker
2. kafka-consumer-database - read real-time stram data from kafka broker and write them to the MySQL database
   
![alt text](https://github.com/ImIshanTharaka/wikimedia-data-retriever/blob/main/diagram.jpg?raw=true)

## What I have learned
* Exchange data between microservicesa using Apache Kafka
* working with multi model Maven projects

## Used technologies
* Java
* Springboot
* MySQL
* Apache Kafka

## How to run the application
* Start Zookeeper and Kafka 
* Update the Mysql database url, username, password and Kafka broker localhost port in the application properties files
* Run the 2 microservices parallelly
