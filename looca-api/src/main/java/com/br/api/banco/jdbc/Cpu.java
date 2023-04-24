package com.br.api.banco.jdbc;

/**
 *
 * @author carlo
 */
public class Cpu {

    private Integer id;
    private String emUso;

    public Cpu(Integer id, String emUso) {
        this.id = id;
        this.emUso = emUso;
    }

    public Cpu() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getEmUso() {
        return emUso;
    }

    public void setEmUso(String emUso) {
        this.emUso = emUso;
    }

    @Override
    public String toString() {
        return String.format("CPU\n"
                + "Id: %d\n"
                + "uso: %s\n"
                ,id, emUso);
    }

}
