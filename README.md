# movies
documentation can be found at: https://github.com/KillMurphy/movies/wiki/Movies-Repository-Wiki 

# Introduction
Movies is a sample Rest application written using IntelliJ (Community Edition), Java 8 and Spring Boot.
Consisting of 3 entities movies, directors and movie ratings (ratings).

It is assumed that the developers know how to package an application using maven.

![databse diagram](https://github.com/KillMurphy/movies/blob/master/movies-database.png)

## Approach
I have not incorporated Hateos in this application and allowed Spring to manage the transactions. Further changes could refactor this to use ResourceAssemblers and specified transactions (within the service layer).

### Testing
GET methods are easier to write tests for but as we cannot be sure of the order that tests run, I put my add/update/delete tests under a single wrapper test so as to enforce the order. This ensures better coverage.

## URL Format
We are using the format http://localhost:8080/api/v1/movies/{movie}

## URLS (GET)
### Movies 

http://localhost:8080/api/v1/movies (returns all movies)

http://localhost:8080/api/v1/movies/{movie} (Long - returns specific movie)

http://localhost:8080/api/v1/movies/director/{director} (Long - returns all movies by the specified director)

http://localhost:8080/api/v1/movies/rating/{rating} (Long - returns all movies over the specified rating)

### Directors 

http://localhost:8080/api/v1/directors (returns all directors)

http://localhost:8080/api/v1/directors/{director} (Long - returns specific director)

### Ratings 

http://localhost:8080/api/v1/ratings (returns all ratings)

http://localhost:8080/api/v1/ratings/{rating} (Long - returns specific rating)

## Updates, Inserts, Deletes
PUT verb is used to update a record, for example:

PUT http://localhost:8080/api/v1/directors

Body: {
"director": 1,
"firstName": "John",
"lastName": "Favreau"
}

POST verb is used to add a record (note that the identifier will be auto generated even though you provide one), for example:

POST http://localhost:8080/api/v1/directors

Body: {
"director": 999,
"firstName": "John",
"lastName": "Favreau"
}

DELETE verb is used to delete a record, for example:

DELETE http://localhost:8080/api/v1/directors/999

## Build Considerations
Please note that you should set your Project SDK to 1.8 and your language level to 8. In IntelliJ this can be found at File > Project Structure > Project

## Run Options (database / H2)

Commenting out the database settings in application.properties will allow the developer to use H2 database (noting the import.sql file has some SQL Server specifics in it, so you'll have to comment those out), otherwise just complete the details for your instance of SQL Server. 

import.sql (H2)

Comment out lines with "set identity_insert <tablename> on/off"

java -jar movies-0.0.1-SNAPSHOT.jar

***

