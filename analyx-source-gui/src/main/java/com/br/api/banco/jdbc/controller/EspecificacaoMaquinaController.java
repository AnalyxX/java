package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import com.br.api.banco.jdbc.EspecificacaoMaquina;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlo
 */
public class EspecificacaoMaquinaController {

    public void insertMaquinaAzure(String hostName, Integer fkCpu, Integer fkDisco, Integer fkRam) {
        ConexaoAzure conexaoAzure = new ConexaoAzure();

        JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

        conAzure.update("insert into especificacaoMaquina values (null, ?, ?, ?, ?)",
                hostName, fkCpu, fkDisco, fkRam);
    }

    public void insertMaquinaLocal(String hostName, Integer fkCpu, Integer fkDisco, Integer fkRam) {
        Conexao conexao = new Conexao();

        JdbcTemplate conAzure = conexao.getConexaoDoBanco();

        conAzure.update("insert into especificacaoMaquina values (null, ?, ?, ?, ?)",
                hostName, fkCpu, fkDisco, fkRam);
    }

    public EspecificacaoMaquina getEspecificacaoMaquinaPorHostNameAzure(String hostName) {
        ConexaoAzure conexaoAzure = new ConexaoAzure();

        JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

        return conAzure.queryForObject("select id, "
                + "hostName, "
                + "fkCpu as cpu, "
                + "fkDisco as disco, "
                + "fkRam as ram "
                + "from especificacaoMaquina where hostName = ?",
                new BeanPropertyRowMapper<EspecificacaoMaquina>(EspecificacaoMaquina.class),
                 hostName);
    }

    public EspecificacaoMaquina getEspecificacaoMaquinaPorHostNameLocal(String hostName) {
        Conexao conexao = new Conexao();

        JdbcTemplate con = conexao.getConexaoDoBanco();

        return con.queryForObject("select id, "
                + "hostName, "
                + "fkCpu as cpu, "
                + "fkDisco as disco, "
                + "fkRam as ram "
                + "from especificacaoMaquina where hostName = ?",
                new BeanPropertyRowMapper<EspecificacaoMaquina>(EspecificacaoMaquina.class),
                 hostName);
    }
}
