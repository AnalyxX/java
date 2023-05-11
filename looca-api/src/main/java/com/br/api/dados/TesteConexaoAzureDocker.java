package com.br.api.dados;

/**
 *
 * @author Carlos
 */
public class TesteConexaoAzureDocker {

    public static void main(String[] args) {
        LoginControllerTeste loginDao = new LoginControllerTeste();
        try {
            System.out.println("""
                               -------------------------------
                               LOGIN NA AZURE
                               -------------------------------
                               """+loginDao.entrarAzure("marcio@email"
                                       + ".com", "1234"));
        } catch (Exception e) {
            System.out.println("Erro na conexão/Login com a azure");
        }
        
        try {
            System.out.println("""
                               -------------------------------
                               LOGIN NO MYSQL
                               -------------------------------
                               """+loginDao.entrarMySql("teste@email.com", "1234"));
        } catch (Exception e) {
            System.out.println("Erro na conexão/Login com a azure");
        }

    }
}
