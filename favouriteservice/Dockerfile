FROM java:8
WORKDIR /usr/src
ENV MONGO_DATABASENAME=BookFavouritesDB
ENV MONGO_URL=mongodb://localhost:27017/BookFavouritesDB
ADD ./target/favouriteservice-1.0.jar usr/src/favouriteservice-1.0.jar
EXPOSE 9300
ENTRYPOINT ["java" ,"-jar","favouriteservice-1.0.jar"]