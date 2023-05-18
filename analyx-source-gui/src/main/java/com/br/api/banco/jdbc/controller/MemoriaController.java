package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Memoria;
import com.br.api.banco.jdbc.Conexao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.List;

/**
 *
 * @author carlo
 */
public class MemoriaController {

    public void registrarUso(Memoria memoria) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.update("insert into componente values (null,?,3)", memoria.getEmUso());
    }

    public Memoria leituraMemoria() {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();
        
        return con.queryForObject("select id,"
                + "uso as emUso "
                + "from componente "
                + "where fkTipoComponente = 3", new BeanPropertyRowMapper<Memoria>(Memoria.class));
    }

    public List<Memoria> showAll() {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.query("select * from memoria", new BeanPropertyRowMapper(Memoria.class));
    }
}
