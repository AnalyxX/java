
package com.br.api.banco.jdbc;

public class Memoria {

    private Integer id;
    private String emUso;
    private String disponivel;

    public Memoria(Integer id, String emUso, String disponivel) {
        this.id = id;
        this.emUso = emUso;
        this.disponivel = disponivel;
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

    public String getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(String disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return String.format("\n DADOS DE MEMÓRIA\n"
                + "Id: %d\n"
                + "Em Uso: %s\n"
                + "Disponivel: %s\n", id, emUso, disponivel);
    }
}
