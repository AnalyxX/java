 #!/bin/bash
java -version #verifica versao atual do java
if [ $? = 0 ]; #se retorno for igual a 0
then #entao,
	echo “java instalado” #print no terminal
else #se nao,
        echo “java não instalado” #print no terminal
        echo “gostaria de instalar o java? [s/n]” #print no terminal
        read get #variável que guarda resposta do usuário
        if [ \“$get\” == \“s\” ]; #se retorno for igual a 0
        then #entao
	        sudo apt install openjdk-17-jre -y #executa instalacao do java
        fi #fecha o 2º if
fi #fecha o 1º if


git clone https://github.com/AnalyxX/java.git #executa o clone onde esta o jar
cd java #acessa o diretorio java
cd looca-api #acessa o diretorio da api
sudo apt-get update #atualizar os pacotes do sistema
sudo apt-get install maven #instalar o Maven
mvn install #baixar as dependências
cd java #acessa o diretorio java
cd looca-api #acessa o diretorio da api
cd target #acesso ao target onde esta o .jar
sudo chmod +x looca-api-1.0-SNAPSHOT-jar-with-dependencies.jar #atribui acesso ao arquivo para ser um executavel
java -jar looca-api-1.0-SNAPSHOT-jar-with-dependencies.jar #executa o arquivo .jar
