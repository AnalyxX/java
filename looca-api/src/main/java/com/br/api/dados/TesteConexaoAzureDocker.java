package com.br.api.dados;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import com.br.api.banco.jdbc.Login;
import com.br.api.banco.jdbc.controller.LoginController;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
                               """+loginDao.entrarAzure("teste@email.com", "1234"));
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
