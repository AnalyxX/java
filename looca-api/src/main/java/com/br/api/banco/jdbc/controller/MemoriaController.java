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

    public void registrarUso(Long emUso, Long disponivel) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();
       
        con.update("insert into memoria values (null,?,?)", emUso, disponivel);
    }
    
    public List<Memoria> showAll() {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.query("select * from memoria",new BeanPropertyRowMapper(Memoria.class));
    }
}
