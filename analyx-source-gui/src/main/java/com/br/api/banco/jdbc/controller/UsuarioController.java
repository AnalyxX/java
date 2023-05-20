package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author carlo
 */
public class UsuarioController {

    public void registrar(Usuario login) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.update("insert into usuario values (null,?,?)", login.getEmail(), login.getSenha());
    }

    public Usuario entrarMySql(String email, String senha) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.queryForObject("select u.id,\n"
                + "		u.email,"
                + "		u.senha,"
                + "		tu.tipoUsuario,"
                + "		f.nome as funcionario "
                + "                     from usuario as u"
                + "			join tipoUsuario as tu"
                + "			on tu.id = u.fkTipoUsuario"
                + "				join funcionario as f"
                + "				on f.id = u.fkFuncionario "
                + "               where email = ? and senha = ?", new BeanPropertyRowMapper<Usuario>(Usuario.class), email, senha);
    }

    public Usuario entrarAzure(String email, String senha) {
        ConexaoAzure conexaoAzure = new ConexaoAzure();

        JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

        return conAzure.queryForObject("select u.id,\n"
                + "		u.email,"
                + "		u.senha,"
                + "		tu.tipoUsuario,"
                + "		f.nome as funcionario "
                + "                     from usuario as u"
                + "			join tipoUsuario as tu"
                + "			on tu.id = u.fkTipoUsuario"
                + "				join funcionario as f"
                + "				on f.id = u.fkFuncionario "
                + "               where email = ? and senha = ?", new BeanPropertyRowMapper<Usuario>(Usuario.class), email, senha);
    }

}
