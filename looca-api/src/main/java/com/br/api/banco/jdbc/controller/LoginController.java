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

    public void registrar(Login login) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.update("insert into login values (null,?,?)", login.getEmail(), login.getSenha());
    }

    public List<Login> entrar(String email, String senha) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.query("select email,senha from login where email = ? and senha = ?", new BeanPropertyRowMapper(Login.class), email, senha);
    }
}
