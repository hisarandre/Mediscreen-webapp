<h1 align="center">Welcome to the WebApp Microservice 👋</h1>
<p>
</p>

> An app with patients notes, data and the level of risk of .

## Versions
- Spring Boot: 3.1.2
- Maven: 3.1.2
- JDK: 17

## Run the app

To launch the app, you can run it locally or use docker.

0. First, create a "mediscreen" folder 
1. Clone inside the [patient](https://github.com/hisarandre/microservice-patient), [history](https://github.com/hisarandre/microservice-history) and [assessment](https://github.com/hisarandre/microservice-assessment) repositories

### Run with docker (recommended)

First, be sure you have Docker open and a .JAR build in each microservice

1. Open the WebApp in your IDE
2. Add each microservice by adding them as a module : Go to "File > New Modules from existing project" and select the pom.xml 
3. Launch `docker-compose up -d --build` in your terminal
4. Go to http://localhost:8083

### Run local

0. First, you will need to launch each microservice and make sure they are running. 

Launch the app :
1. Make sure you have the required versions of Java and dependencies installed.
2. Open a terminal or command prompt and navigate to the project directory.
3. Run the following command to build the project and create an executable JAR file:
   ` mvn package`
4. Once the build is successful, you can launch the app using the following command:
   ` java -jar target/webapp-0.0.1-SNAPSHOT.jar `
   This will start the app on the configured server address : http://localhost:8083