# Project Setup

### Prerequisites
Install maven
Install java
Install Spring CLI
For MacOS:
brew tap spring-io/tap
brew install spring-boot
spring --version

### Create Template Spring Project from CommadLine
spring init \
  --build=maven \
  --dependencies=web \
  --package-name=com.creditai.demo \
  credit-ai-demo


### Add the logic files to the package com.creditai.demo
Controller file to controller folder, default com.creditai.demo
Model file to model folder, default com.creditai.demo
Service file to service folder, default com.creditai.demo

### Update Pom file to include all dependencies 
<dependencies>
    <!-- Spring Web for REST API -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- OkHttp for HTTP calls to OpenAI -->
    <dependency>
        <groupId>com.squareup.okhttp3</groupId>
        <artifactId>okhttp</artifactId>
        <version>4.11.0</version>
    </dependency>

    <!-- JSON processing -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>

    <!-- org.json library -->
    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20230227</version>
    </dependency>

    <!-- Lombok for cleaner code -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>



### Build and Run
mvn clean install
mvn spring-boot:run

We should see SpringBoot Application started msg with the port details

### Set the Open API Key in the session
export OPENAI_API_KEY="sk-<your-key>"
echo $OPENAI_API_KEY


### Test using the Postman Curl Command
curl -X POST http://localhost:8080/api/advice \
-H "Content-Type: application/json" \
-d '{"creditScore":650,"debt":12000,"income":5000,"goal":"Improve credit score to 750"}'

# You should see output similar to the below

{
  "planId": "plan_001",
  "summary": "Reduce credit utilization below 30% and pay off high-interest debt first",
  "steps": [
    "Shift $1200 to low-balance credit card",
    "Generate dispute for erroneous collection",
    "Set up automatic payments to avoid late fees"
  ]
}
 





