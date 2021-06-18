# Adobe & AEM Engineering Test
Extention 1 and 3

### About
A webservice that takes in a number and outputs a Roman numeral.
### Techstack
   - Java
   - Springboot
   - Docker
   - JUnit

### Folder structure
- [AemApplication](src/main/java/AemApplication): Entry point of the application where the 'main' fuction lies.
- [AemController](src/main/java/controller/AemController): API requests are mapped and routed to appropriate implementations and served back to the client with a response.
- [AemService](src/main/java/business/AemService): All the computations and business logic to serve the request.
- [AemAdvice](src/main/java/handler/AemAdvice): All exceptions in the codebase are handled here.
- [model](src/main/java/model): POJOs for success and error response.
- [utils](src/main/java/utils): Utility methods and variables for the codebase.


### How to run

In the root directory, run the following command

    ./gradlew clean build bootRun

OR

Build the docker image by running the Dockerfile in root directory by running following commands: 

    1. docker build .
    2. docker run -d -p 8080:8080 <image name>


### How to test
In Postman or any web browser, visit
    
    http://localhost:8080/romannumeral?query=<integer>
