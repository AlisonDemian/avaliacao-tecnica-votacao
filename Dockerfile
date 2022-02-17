FROM adoptopenjdk/openjdk11:alpine
RUN mkdir /opt/avaliacao-tecnica-votacao
COPY target/avaliacao-tecnica-votacao-0.0.1-SNAPSHOT.jar /opt/avaliacao-tecnica-votacao
CMD ["java", "-jar", "/opt/avaliacao-tecnica-votacao/avaliacao-tecnica-votacao-0.0.1-SNAPSHOT.jar"]




