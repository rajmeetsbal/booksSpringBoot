FROM java:8
#WORKDIR userservice/src
WORKDIR usr/src
ENV MYSQL_DATABASE=mybooksusersdb
ENV MYSQL_USER=app_root
ENV MYSQL_PASSWORD=root
ENV MYSQL_URL=mysql://localhost:3306/mybooksusersdb
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
EXPOSE 8083:8083
ENTRYPOINT ["java", "-jar", "userservice-1.0.jar"]