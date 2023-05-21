package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import com.br.api.banco.jdbc.Cpu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlo
 */
public class CpuController {

    public Cpu leituraCpu() {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.queryForObject("select * from componente where fkTipoComponente = 1 and fkMonitoramento = 2", new BeanPropertyRowMapper<Cpu>(Cpu.class));
    }

    public void insertUsoCpuLocal(Double c, Integer fkMonitoramento) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        con.update("insert into componente value "
                + "(null, ?,?,1)", c, fkMonitoramento);
    }

    public void insertUsoCpuAzure(Double c, Integer fkMonitoramento) {
        ConexaoAzure conexaoAzure = new ConexaoAzure();

        JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

        conAzure.update("insert into componente value "
                + "(null, ?,?,1)", c, fkMonitoramento);
    }
}