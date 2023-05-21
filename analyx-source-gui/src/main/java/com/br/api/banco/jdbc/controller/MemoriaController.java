package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Memoria;
import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.util.List;

/**
 *
 * @author carlo
 */
public class MemoriaController {

    public Memoria leituraMemoria() {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.queryForObject("select * from componente where fkTipoComponente = 3 and fkMonitoramento = 2", new BeanPropertyRowMapper<Memoria>(Memoria.class));
    }

    public List<Memoria> showAll() {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.query("select * from memoria", new BeanPropertyRowMapper(Memoria.class));
    }

    public void insertUsoRamLocal(Double r, Integer fkMonitoramento) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.update("insert into componente value "
                + "(null, ?,?,3)", r, fkMonitoramento);
    }

    public void insertUsoRamAzure(Double r, Integer fkMonitoramento) {
        ConexaoAzure conexaoAzure = new ConexaoAzure();

        JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

        conAzure.update("insert into componente value "
                + "(null, ?,?,3)", r, fkMonitoramento);
    }

}
