# Spring-kafka-mongodb Sample

Sample Project to demonstrate the usage of Spring Framework, Kafka and MongoDB features with Java EE 7 and Java 8

# Author

Frederico Cerqueira Alves

# Frameworks

Spring Boot,
Kafka,
JUnit

# Database

MongoDB

# Docker

- MongoDB Cluster

$ docker run --name mongo -v /opt/mongo/data:/data/db -v /opt/mongo:/opt/keyfile --hostname="node1" -p 27017:27017 -d mongo:3.7.3 --smallfiles

Note: creating a ssl key...

$ openssl rand -base64 741 > mongodb-keyfile 

$ chmod 600 mongodb-keyfile $ chown 999 mongodb-keyfile


- Kafka Zookeeper

$ docker run -d --name zookeeper-server -p 2181:2181 --network ${app-tier} bitnami/zookeeper:latest

- Kafka Server

$ docker run -d --name kafka-server --network ${app-tier} -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181  bitnami/kafka:latest

- Kafka Client

$ docker run -it --rm --network ${app-tier} -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 bitnami/kafka:latest

NOTE: A compose environment is required to perform a better configuration.

