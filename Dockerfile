# Estágio 1: Build (Compilação)
FROM eclipse-temurin:25-jdk AS build
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Dá permissão para o wrapper e compila o projeto
RUN chmod +x ./mvnw && ./mvnw clean package -DskipTests

# Estágio 2: Run (Execução)
FROM eclipse-temurin:25-jdk
WORKDIR /app

# Copia o jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]