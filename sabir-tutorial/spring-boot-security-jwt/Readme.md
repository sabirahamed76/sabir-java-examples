#Spring Boot Security JWT 

-Create table in mysql

POST http://localhost:8080/api/auth/login
Pass the below as body raw json
{
    "usernameOrEmail":"admin@gmail.com",
    "password":"admin"
}
-This will return the accessToken


GET http://localhost:8080/api/auth/login
pass the JWT token in the header to access admin-related REST APIs


Reference:
https://www.javaguides.net/2024/01/spring-boot-security-jwt-tutorial.html