FROM openjdk:11
RUN mkdir -p /home/docker_user/app/
RUN chmod 755 /home/docker_user/app/
COPY target/database-0.0.1-SNAPSHOT.jar /home/docker_user/app/database.jar
EXPOSE 8088
CMD ["java","-jar","/home/docker_user/app/database.jar"]