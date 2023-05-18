package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
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

        return con.queryForObject("select id,"
                + "uso as emUso "
                + "from componente "
                + "where tipoComponente = 1", new BeanPropertyRowMapper<Cpu>(Cpu.class));
    }
}
