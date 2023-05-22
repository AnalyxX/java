package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import com.br.api.banco.jdbc.Disco;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlo
 */
public class DiscoController {

    public Disco leituraDisco() {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.queryForObject("select * from componente where fkTipoComponente = 2 and fkMonitoramento = 2", new BeanPropertyRowMapper<Disco>(Disco.class));
    }

    public void insertUsoDiscoLocal(Double d, Integer fkMonitoramento) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();
        
        con.update("insert into componente value "
                + "(null, ?,?,2)", d, fkMonitoramento);
    }
    
    public void insertUsoDiscoAzure(Double d, Integer fkMonitoramento) {
        ConexaoAzure conexaoAzure = new ConexaoAzure();

        JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();
        
        conAzure.update("insert into componente value "
                + "(null, ?,?,2)", d, fkMonitoramento);
    }
}
