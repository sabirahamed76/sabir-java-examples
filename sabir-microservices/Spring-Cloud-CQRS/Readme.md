# CQRS Pattern Example

POST http://localhost:8080/po/sale
{
"userIndex":0,
"productIndex":0
}

POST http://localhost:8080/po/sale
{
"userIndex":1,
"productIndex":1
}


GET http://localhost:8080/po/summary
GET http://localhost:8080/po/total-sale


PUT http://localhost:8080/po/cancel-order/2
