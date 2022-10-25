# Group 13!
# RUNNING RELEASE ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
## How to run Release 1:
1. Open up docker desktop
2. Open up terminal:
   1. Type in "docker pull goleccc/calculator-web-app:release1"
   2. Once pulled, type in "docker run -d -p 8080:8080 6ea5144a28e2"
3. Go to http://localhost:8080/calculator in your browser

# SETTING UP CONTAINERISED WEB APP ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
## THIS ONLY NEEDS TO BE DONE DURING RELEASING
## How to set up image and container:
1. In terminal in your IDE type in: 
   1. ./mvnw clean install spring-boot:run
   2. terminate the webapp after successfully built
   3. docker build -t web-app-calculator-image .
   4. docker run --name=web-app-calculator-simple-container --rm -d -p 8080:8080 web-app-calculator-image
2. Go to http://localhost:8080/calculator in your browser

# DEVELOPMENT SECTION ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
## Initial Setup
### You have to have an IDE that explicitly supports Java JDK 17 (Preferably Intellij 2022)

1. Clone the repo
2. Follow the tutorial on this page (Important: The Java version we are using is Java 17):
https://docs.oracle.com/cd/E19182-01/821-0917/inst_jdk_javahome_t/index.html#:~:text=To%20set%20JAVA_HOME%2C%20do%20the,Program%20Files%5CJava%5Cjdk1.
3. Restart your PC

### To run the service and check that things are working:
1. Open up the repo in your IDE
2. Open up terminal in your IDE
3. type in ./mvnw spring-boot:run
4. In your browser go to "http://localhost:8080/calculator"

##  After Setup

### For creating and editing tests please navigate to:
	src/test/java/com/group13seassignment2/calculator/CalculatorApplicationTests.java

### For working on the calculator file please go to:
	src/main/java/com/group13seassignment2/calculator/Calculator.java

Please do not change anything in the CalculatorController.java and CalculatorApplication.java files

## GitHub Things

Integrated testing should be set up, so please branch and pull request to main and only continue with the pull request if all tests pass.
