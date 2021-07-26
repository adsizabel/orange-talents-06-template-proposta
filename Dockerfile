FROM openjdk:11
LABEL version="0.0.1-SNAPSHOT" maintainer="Izabel Silva<izabel@email>"
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-Dperiodicidade.associar.cartao=1000", "-jar", "app.jar"]