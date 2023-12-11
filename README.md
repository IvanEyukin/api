Start: docker-compose -f docker-compose.yml up -d 

Запросы: 
curl --location 'http://localhost:8080/user/checkPassword' \
--header 'Content-Type: application/json' \
--data '{
    "login": "User3",
    "password": "MyPassword2"
}'

curl --location 'http://localhost:8080/user' \
--header 'Content-Type: application/json' \
--data '{
    "name": "TestUser3",
    "login": "User3",
    "password": "MyPassword",
    "creationDate": "2021-08-03T13:28:00"
}'

curl --location --request PUT 'http://localhost:8080/user' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Admin",
    "login": "User3",
    "password": "MyPassword2",
    "role": "Admin"
}'

curl --location 'http://localhost:8080/task' \
--header 'Content-Type: application/json' \
--data '{
    "creator": "TestLogin",
    "title": "TestTitle",
    "content": "TestContent",
    "creationDate": "2023-12-04T19:05:20"
}'

curl --location 'http://localhost:8080/task'

curl --location --request PUT 'http://localhost:8080/task/8' \
--header 'Content-Type: application/json' \
--data '{
    "id": 8,
    "creator": "TestLogin",
    "title": "TestTitle2",
    "content": "MyTestContent",
    "creationDate": "1998-04-04T12:19:44"
}'

curl --location --request DELETE 'http://localhost:8080/task/11'