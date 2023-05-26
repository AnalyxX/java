package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import com.br.api.banco.jdbc.Disco;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlo
 */
public class DiscoController {

    ConexaoAzure conexaoAzure = new ConexaoAzure();

    JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

    Conexao conexao = new Conexao();

    JdbcTemplate con = conexao.getConexaoDoBanco();
    
    public void insertDiscoMaquinaAzure(Long volume) {

        List<Disco> disco = conAzure.query("select id, "
                + "volume "
                + "from disco where volume = ?",
                new BeanPropertyRowMapper<Disco>(Disco.class), volume);

        if (disco.isEmpty()) {
            conAzure.update("insert into disco values (?)", volume);
            System.out.println("disco cadastrado");
        } else {
            System.out.println(disco + " disco já cadastrada");
        }
    }

    public void insertUsoDiscoLocal(Double d, Integer fkMonitoramento) {
        
        con.update("insert into componente value "
                + "(null, ?,?,2)", d, fkMonitoramento);
    }
    
    public void insertUsoDiscoAzure(Double d, Integer fkMonitoramento) {
        
        conAzure.update("insert into componente value "
                + "(?,?,2)", d, fkMonitoramento);
    }
}
