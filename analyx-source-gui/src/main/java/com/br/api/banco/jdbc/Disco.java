package com.br.api.banco.jdbc;

/**
 *
 * @author carlo
 */
public class Disco {

    private Integer id;
    private String volume;
    private String emUso;

    public Disco(Integer id, String volume, String emUso) {
        this.id = id;
        this.volume = volume;
        this.emUso = emUso;
    }

    public Disco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getEmUso() {
        return emUso;
    }

    public void setEmUso(String emUso) {
        this.emUso = emUso;
    }

    @Override
    public String toString() {
        return String.format("Disco\n"
                + "Id: %d\n"
                //+ "Volume do disco: %s\n"
                + "Uso disco: %s\n",
                 id, emUso);
    }

}
