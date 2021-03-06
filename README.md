# FootballTeams1
Restful Service for Football team data

## Architecture
The application is implemented in the following layers:

* Web layer - REST api controller defining endpoints.
* business logic (BL) layer - performing actions against data recieved
* Data layer - stores the data.
* Helper classes layer - random string and integer generators, used purely for unit testing

Using this pattern would allow the data layer to be replaced with a database and the business logic layer to be edited correspondingly. The Web layer would not need to be changed. This is simplified and in practice another layer might be added between the BL and data layer. However, the business logic is simple in this example so it is not required.

## Web layer
REST api controller created using springboot.
All endpoints are defined here.
Requests and their data are logged to the springboot log with minimal configuration - only setting the file system path to save the log

### Endpoints:

/health
- GET
- body: N/A
- purpose: health check mainly for AWS EBS
- returns: "{"status":"up"}" in JSON format

/hello
- GET
- body: N/A
- purpose: test endpoint
- returns: "Hello from SkyeTech Solutions!" in plain text

/footballTeam
- GET
- body: plain text name of football team
- purpose: get data for a football team
- returns: JSON of team details, e.g (note that date of creation is in Date format: {"name":"Arsenal","city":"London","owner":"Martin Golding","stadiumCapacity":70000,"competition":"Premier League","numberOfPlayers":20,"dateOfCreation":220752000000}

- POST
- body: JSON object for the new team
- purpose: post data for a new football team. NB: if a team with the same name already exists then it will not add a duplicate.
- returns: JSON of team details.
{"name":"Arsenal","city":"London","owner":"Martin Golding","stadiumCapacity":70000,"competition":"Premier League","numberOfPlayers":20,"dateOfCreation":220752000000}

/footballTeams
- GET
- body: N/A
- purpose: get data for all football teams
- returns: JSON object containing all teams and their team details

/footballTeamsSortedByCapacity
- GET
- body: N/A
- purpose: get data for all football teams sorted in descending order of capacity then in ascending alphabetical order of team name (required to pass unit testing)
- returns: JSON object containing all teams and their team details

## Business Logic layer
A set of functions to filter and sort the data for the web requests. It also decouples the data layer from the web controller.

## Data layer
Football teams are stored as POJO with custom comparator for the team name to ensure they are unique
A TreeSet is used to store the teams as it performs well as the number of teams grows. The add() method is overridden with a custom comparator for the team name, again to ensure uniqueness
A singleton class is used to encapsulate the TreeSet and ensure a single store of team data across the entire application

# Testing
Unit testing with MockMVC directly from the IntelliJ menu or in the java files themselves. Maven-test goal will also run all of these tests.
local testing with postman requires maven spring-boot:run from within IntelliJ. Use localhost:5000 as the url and add the appropriate path.
EBS testing with postman requires correct url generated by EBS.

# Logging
For linux (i.e. deploying to AWS EBS) leave values as default
For Windows, to allow edit the following settings in application.properties, for example: TomCat access log server.tomcat.basedir=C:/dev
Spring-boot log logging.file.name=C:/dev/Spring.log

# Building and running
Locally In IntelliJ:
1. Run the maven package goal.
2. run the spring-boot:run plugin.
the api can now be accessed, e.g. tested with postman.

# AWS Elastic Beanstalk integration
* html file landing page index.html in static resources folder. This is to test the site is up and running
* server port 5000 needs to be specified in application.properties to work with EBS load balacer

##  Deploying to EBS
Follow AWS instructions for setting up a new environment:

Java 8 running on 64bit Amazon Linux/2
Use the footballteams-0.0.1-SNAPSHOT.jar created in target folder
