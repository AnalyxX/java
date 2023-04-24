package com.br.api.banco.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author consultor
 */
public class Conexao {

    private JdbcTemplate conexaoDoBanco;

    public Conexao() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        dataSource.setUrl("jdbc:sqlserver://localhost;encrypt=true;database=analyx;integratedSecurity=true;"); 
        
        dataSource.setUsername("analyx-admin");
        
        dataSource.setPassword("#Gfgrupo2");

        this.conexaoDoBanco = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }

}
