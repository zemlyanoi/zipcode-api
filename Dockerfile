FROM openjdk:8u171-jre-alpine3.7
COPY build/libs/zipcodeservice.jar home/
EXPOSE 8080
CMD ["java","-jar","/home/zipcodeservice.jar"]