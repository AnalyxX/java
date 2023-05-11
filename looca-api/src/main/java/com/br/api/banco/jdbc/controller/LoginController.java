package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Login;
import org.springframework.jdbc.core.JdbcTemplate;
import com.br.api.banco.jdbc.Conexao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author carlo
 */
public class LoginController {

    public void registrar(Login login) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.update("insert into usuario values (null,?,?)", login.getEmail(), login.getSenha());
    }

    public Login entrar(String email, String senha) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.queryForObject("select u.id,\n" +
"		u.email," +
"		u.senha," +
"		tu.tipoUsuario," +
"		f.nome as funcionario " +
"                     from usuario as u" +
"			join tipoUsuario as tu" +
"			on tu.id = u.tipo" +
"				join funcionario as f" +
"				on f.id = u.funcionario " +
"               where email = ? and senha = ?", new BeanPropertyRowMapper<Login>(Login.class), email, senha);
    }
}
