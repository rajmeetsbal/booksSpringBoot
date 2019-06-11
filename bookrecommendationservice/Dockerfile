FROM java:8
WORKDIR /usr/src
ENV MONGO_DATABASENAME=BookRecommendationsDB
ENV MONGO_URL=mongodb://localhost:27017/BookRecommendationsDB
ADD ./target/bookrecommendationservice-1.0.jar usr/src/bookrecommendationservice-1.0.jar
EXPOSE 9400
ENTRYPOINT ["java" ,"-jar","bookrecommendationservice-1.0.jar"]