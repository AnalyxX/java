package com.br.api.dados;

import com.br.api.banco.jdbc.controller.LoginController;

/**
 *
 * @author Carlos
 */
public class TesteConexaoAzureDocker {

    public static void main(String[] args) {
        LoginController loginDAO = new LoginController();

        System.out.println(loginDAO.entrar("teste@email.com", "1234"));
        try {
            //loginDAO.entrar("teste@email.com", "1234");
        } catch (Exception e) {
            System.out.println("Erro na conexão/Login");
        }

    }
}
