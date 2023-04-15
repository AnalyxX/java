
package com.br.api.banco.jdbc.controller;

public class TesteLogin {
    public static void main(String[] args) {
        LoginController loginDAO = new LoginController();
        
        System.out.println(loginDAO.entrar("teste", "1234"));
    }
}
