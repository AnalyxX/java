package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Login;
import org.springframework.jdbc.core.JdbcTemplate;
import com.br.api.banco.jdbc.Conexao;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author carlo
 */
public class LoginController {

    public void registear(Login login) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.update("insert into login values (?,?,?)", login.getId(), login.getEmail(), login.getSenha());
    }
    
    // ALTERADA A FUNÇÃO ENTRAR, DESTA VEZ UTILIZANDO O LOGIN COMO PARÂMETRO

    public List<Login> entrar(Login login) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();
        String email = login.getEmail();
        String senha = login.getSenha();

        return con.query("select * from login where email = ? and senha = ?",new BeanPropertyRowMapper(Login.class), email, senha);
    }
}
