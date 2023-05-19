package com.br.api.banco.jdbc;

import java.util.Date;

/**
 *
 * @author carlo
 */
public class Monitoramento {
    private Integer id;
    private Date data;

    public Monitoramento(Integer id, Date data) {
        this.id = id;
        this.data = data;
    }

    public Monitoramento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("Monitoramento\n"
                + "Id: %d\n"
                + "Data: %d\n"
                , id,data);
    }
    
    
    
}
