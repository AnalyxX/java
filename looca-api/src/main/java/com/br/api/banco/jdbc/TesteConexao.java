package com.br.api.banco.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author consultor
 */
public class TesteConexao {

    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = null;
        
        try {
            con = conexao.getConexaoDoBanco();
            if (con != null) {
                System.out.println("Conexão feita!");
            }
        } catch (Exception e) {
            System.out.println("Erro na conexão com o SQL: " + e.getMessage());
        }
        

    }
}
