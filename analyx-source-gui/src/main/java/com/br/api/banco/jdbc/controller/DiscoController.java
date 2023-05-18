
package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
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

        return con.queryForObject("select id,"
                + "uso as emUso "
                + "from componente "
                + "where fkTipoComponente = 2", new BeanPropertyRowMapper<Disco>(Disco.class));
    }
}
