Spring MVC Example
-------------------

1. Use postman GET to execute http://localhost:8080/products 

2. Use postman POST to execute http://localhost:8080/products/create RAW below Requestbody

{
    "id": "3",
    "name": "Cashew"
} 


3. Use postman PUT to execute http://localhost:8080/products/update/3 RAW below Requestbody
{
    "name": "Cashew nuts"
} 


4. Use postman PUT to execute http://localhost:8080/products/update/4 RAW below Requestbody
{
    "name": "Cashew nut"
} 

4. Use postman PUT to execute http://localhost:8080/products/delete/3 

