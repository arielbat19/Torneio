# Usando a imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Definindo o diretório de trabalho dentro do container
WORKDIR /app

# Copiando o arquivo JAR do projeto para o container
COPY target/Desafio-Tecnico-0.0.1-SNAPSHOT.jar desafio-tecnico.jar

# Comando para rodar o aplicativo
CMD ["java", "-jar", "desafio-tecnico.jar"]

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
