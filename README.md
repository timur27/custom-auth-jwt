[![Build Status](https://travis-ci.com/timur27/donner.svg?token=gAMYfxCcgmJLAixPNatY&branch=master)](https://travis-ci.com/timur27/donner)

Custom auth on Spring Boot without Spring Security but custom authentication flow. 

For new user register type: 
`curl --header "Content-Type: application/json" 
--request POST 
--data '{"username":"USERNAME","password":"PASSWORD"}' 
http://localhost:8081/register`


For user login:  
`curl --header "Content-Type: application/json"  
--request POST  
--data '{"username":"USERNAME","password":"PASSWORD"}'  
http://localhost:8081/login` 

Possible HTTP server responses are:
/register -> 201 CREATED - User successfully created
          -> 409 CONFLICT - User with provided data already exist
          
/login    -> 200 OK - message: JWT_TOKEN 
          -> 400 BAD REQUEST - Provided user data is invalid
