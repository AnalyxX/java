package com.br.api.banco.jdbc;

public class Memoria {

    private Integer id;
    private String emUso;

    public Memoria(Integer id, String emUso) {
        this.id = id;
        this.emUso = emUso;
    }

    Memoria() {
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
        return String.format("Memória\n"
                + "Id: %d\n"
                + "Em Uso: %s\n"
                ,id, emUso);
    }
}
