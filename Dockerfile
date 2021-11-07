FROM openjdk
COPY target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-0.0.1-SNAPSHOT.war docker-spring-boot.war
EXPOSE 8083
ENTRYPOINT ["java","-jar","/docker-spring-boot.war"]
