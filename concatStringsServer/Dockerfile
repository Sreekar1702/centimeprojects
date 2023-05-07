FROM openjdk:11
RUN mkdir -p /home/docker_user/app/
RUN chmod 755 /home/docker_user/app/
COPY target/concatStringsServer-0.0.1-SNAPSHOT.jar /home/docker_user/app/concatStringsServer.jar
EXPOSE 8083
CMD ["java","-jar","/home/docker_user/app/concatStringsServer.jar"]