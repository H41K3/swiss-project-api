# 1. Pega um computador virtual "limpo" apenas com o Java 25 instalado
FROM eclipse-temurin:25-jdk

# 2. Assinatura de quem construiu a imagem (ótimo para portfólio)
LABEL maintainer="Haike"

# 3. Cria uma pasta de trabalho lá dentro
WORKDIR /app

# 4. Pega o seu "bolo assado" (o .jar) e coloca dentro da caixa com o nome "app.jar"
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# 5. Avisa que o sistema se comunica pela porta 8080
EXPOSE 8080

# 6. O comando que o container vai rodar automaticamente quando for ligado
ENTRYPOINT ["java", "-jar", "app.jar"]