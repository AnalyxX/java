
package com.br.api.banco.jdbc;

/**
 *
 * @author carlo
 */
public class EspecificacaoMaquina {
    private Integer id;
    private String numeroSerial;
    private String modeloCpu;
    private String volumeDisco;
    private String totalRam;

    public EspecificacaoMaquina(Integer id, String numeroSerial, String modeloCpu, String volumeDisco, String totalRam) {
        this.id = id;
        this.numeroSerial = numeroSerial;
        this.modeloCpu = modeloCpu;
        this.volumeDisco = volumeDisco;
        this.totalRam = totalRam;
    }

    public EspecificacaoMaquina() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroSerial() {
        return numeroSerial;
    }

    public void setNumeroSerial(String numeroSerial) {
        this.numeroSerial = numeroSerial;
    }

    public String getModeloCpu() {
        return modeloCpu;
    }

    public void setModeloCpu(String modeloCpu) {
        this.modeloCpu = modeloCpu;
    }

    public String getVolumeDisco() {
        return volumeDisco;
    }

    public void setVolumeDisco(String volumeDisco) {
        this.volumeDisco = volumeDisco;
    }

    public String getTotalRam() {
        return totalRam;
    }

    public void setTotalRam(String totalRam) {
        this.totalRam = totalRam;
    }

    @Override
    public String toString() {
        return String.format("Especificação da máquina\n"
                + "Id: %d\n"
                + "Número Serial: %s\n"
                + "CPU: %s\n"
                + "Disco: %s\n"
                + "Ram: %s\n"
                ,id,numeroSerial,modeloCpu,volumeDisco,totalRam);
    }
    
    
}
