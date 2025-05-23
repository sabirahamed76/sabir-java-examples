# Sabir Saga Pattern - Orchestration

This is a sample project to demo saga pattern.

## Prerequisites:

* Kafka cluster

# High Level Architecture

![](doc/saga-orchestration.png)


Execute the below 4 times in postman
POST
http://localhost:8080/order/create
{
    "userId":1,
    "productId":3
}

Check the 4th order status
GET
http://localhost:8080/order/all
