FROM openjdk:11
RUN mkdir -p /home/docker_user/app/
RUN chmod 755 /home/docker_user/app/
COPY target/helloService-0.0.1-SNAPSHOT.jar /home/docker_user/app/helloService.jar
EXPOSE 8089
CMD ["java","-jar","/home/docker_user/app/helloService.jar"]