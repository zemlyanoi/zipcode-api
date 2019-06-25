# ZIPCODE SERVICE

Coding Challenge

## PROBLEM

Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.

## NOTES

- The ranges above are just examples, your implementation should work for any set of arbitrary ranges
- Ranges may be provided in arbitrary order
- Ranges may or may not overlap
- Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices

## EXAMPLES

- If the input = [94133,94133] [94200,94299] [94600,94699]
- Then the output should be = [94133,94133] [94200,94299] [94600,94699]
 
- If the input = [94133,94133] [94200,94299] [94226,94399]
- Then the output should be = [94133,94133] [94200,94399]

## HOW TO RUN WITH DOCKER

- clone the repository
- navigate to folder `cd docker`
- install docker on local env documentation 
  - how to install docker for windows - https://docs.docker.com/docker-for-windows/install/
  - how to install docker for mac - https://docs.docker.com/docker-for-mac/install/
- run command `docker-compose up`
- open in browser `localhost:3000`

## HOW TO RUN API

- clone the repository
- make command `./gradlew clean build` in zipcodeservice folder
- navigate to folder `cd build/libs` where jar file has been built
- run command `java -jar zipcodeservice.jar` for start application
- application will run using port 8080
- use any HTTP client to make a POST request on REST service `http://localhost:8080/process-zipcodes` and send Request Body text like `[94133,94133] [94200,94299] [94226 94399]` 

## HOW TO RUN UI

- clone the repository
- navigate to folder `cd app/`
- run command `yarn install`
- run command `yarn build`
- run command `yarn start`
- application will run using port 3000
- open in browser `http://localhost:3000`


![Image description](/images/1.png)
![Image description](/images/2.png)
![Image description](/images/3.png)