package com.br.api.banco.jdbc;

import com.br.api.banco.jdbc.controller.LoginController;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author consultor
 */
public class TesteConexao {

    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = null;
        // Boolean conectado = false;
        // LoginController loginDAO = new LoginController();

        try {
            con = conexao.getConexaoDoBanco();
            System.out.println("Conexão feita!");

        } catch (Exception e) {
            System.out.println("Erro na conexão com o SQL: " + e.getMessage());
        }

        // CRIAÇÃO DE UM LOGIN PRA TESTAR NA TELA SWING
        /* if(conectado){
            Login login1 = new Login(null, "analyx@gmail.com", "teste1234");
            loginDAO.registear(login1);
            
            System.out.println("Inserção realizada com sucesso!");
            
        }else{
            System.out.println("Não foi possível inserir!!");
        }
        
         */
    }
}
