# Desafio Técnico

Projeto Java desenvolvido como parte de um desafio técnico. Esta aplicação é construída com Maven e empacotada como um arquivo `.jar`.

## ✅ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- Docker
- JUnit
- Mockito

## 🚀 Como Executar Localmente

### Pré-requisitos

- JDK 17+
- Maven 3.8+
- Docker (opcional, apenas para rodar via container)

### Compilando o projeto

```bash
mvn clean package
```
- O comando acima vai gerar o .jar em target/Desafio-Tecnico-0.0.1-SNAPSHOT.jar.

### Executando o projeto localmente

```bash
java -jar target/Desafio-Tecnico-0.0.1-SNAPSHOT.jar
```

- O comando acima vai executar o projeto localmente e estará disponivel em:.

```http
http://localhost:8080
```
### 🐳 Executando com Docker

- 1. Compile o projeto
     Antes de criar a imagem Docker, compile o projeto com Maven:

```bash
mvn clean package
```

- 2. Crie a imagem Docker

```bash
docker build -t desafio-tecnico .
```

- 3. Execute a imagem Docker

```bash
docker run -p 8080:8080 desafio-tecnico
```

### 📁 Estrutura do Projeto

```bash
.
├── src/
│   └── main/java/...     # Código-fonte do projeto
├── target/               # Arquivos compilados
├── Dockerfile            # Dockerfile para criação da imagem
├── pom.xml               # Arquivo de configuração do Maven
└── README.md             # Instruções do projeto
```