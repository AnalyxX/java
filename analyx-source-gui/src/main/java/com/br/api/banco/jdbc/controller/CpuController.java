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

    public void insertCpuMaquinaLocal(String modeloCPU) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();
        
        con.update("insert into cpu value (null,?)", modeloCPU);
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