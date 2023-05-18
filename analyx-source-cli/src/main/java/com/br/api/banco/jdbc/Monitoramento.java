package com.br.api.banco.jdbc;

import java.util.Date;

/**
 *
 * @author carlo
 */
public class Monitoramento {
    private Integer id;
    private Date data;
    private String numeroSerial;

    public Monitoramento(Integer id, Date data, String numeroSerial) {
        this.id = id;
        this.data = data;
        this.numeroSerial = numeroSerial;
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

    public String getnumeroSerial() {
        return numeroSerial;
    }

    public void setnumeroSerial(String numeroSerial) {
        this.numeroSerial = numeroSerial;
    }

    @Override
    public String toString() {
        return String.format("Monitoramento\n"
                + "Id: %d\n"
                + "Data: %d\n"
                + "Número Serial: %s\n"
                , id,data,numeroSerial);
    }
    
    
    
}
