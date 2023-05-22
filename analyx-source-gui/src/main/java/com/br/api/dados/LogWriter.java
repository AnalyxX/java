package com.br.api.dados;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
    private static final String LOG_FILE_PATH = "C:\\Users\\gibas\\OneDrive\\Documentos\\NetBeansProjects\\java\\analyx-source-gui\\src\\main\\resources\\log.txt";

    public static void main(String[] args) {
        // Colar nas camadas de execução do programa, como a Tela de Swing
        
        try {
            // Abre o arquivo de log para escrita (o arquivo é criado se não existir)
            BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true));

            // Escreve a mensagem de log no arquivo
            String mensagem = "Início da execução do programa.";
            escreverLog(writer, mensagem);

            // Seu código Java aqui...

            // Exemplo de registro de um evento no log
            mensagem = "Evento ocorreu.";
            escreverLog(writer, mensagem);

            // Seu código Java aqui...

            // Exemplo de registro de um erro no log
            mensagem = "Ocorreu um erro!";
            escreverLog(writer, mensagem);

            // Seu código Java aqui...

            // Fecha o arquivo de log
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void escreverLog(BufferedWriter writer, String mensagem) throws IOException {
        // Obtém a data/hora atual e adiciona à mensagem de log
        String logEntry = "[" + java.time.LocalDateTime.now() + "] " + mensagem;

        // Escreve a mensagem de log no arquivo
        writer.write(logEntry);
        writer.newLine();

        // Flushe o buffer para garantir que a mensagem seja gravada no arquivo
        writer.flush();

        // Imprime a mensagem no console (opcional)
        System.out.println(logEntry);
    }
}
