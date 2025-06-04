Welcome to Sabir Spring Boot Security OAuth
-------------------------------------------
1. Navigate to: console.developers.google.com 

2. Left tab: Credentials > Create credentials > OAuth Client ID

3. Select ‘Application Type’ as ‘Web application’

4. In ‘Authorised redirect URIs , add the URI http://localhost:8080/login/oauth2/code/google // redirects user to login page

5. Copy the client ID and Client secret key to enter in the application.properties

6. Run the spring boot application

7. Browse http://localhost:8080/oauth2/authorization/google



