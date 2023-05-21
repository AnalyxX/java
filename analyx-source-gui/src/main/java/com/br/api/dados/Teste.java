package com.br.api.dados;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import java.util.List;

/**
 *
 * @author carlo
 */
public class Teste {

    public static void main(String[] args) {
        Looca looca = new Looca();
        System.out.println(looca.getGrupoDeDiscos().getDiscos());
        System.out.println(looca.getGrupoDeDiscos().getQuantidadeDeDiscos());
        System.out.println(looca.getGrupoDeDiscos().getQuantidadeDeVolumes());
        System.out.println(looca.getGrupoDeDiscos().getTamanhoTotal());
        System.out.println(looca.getGrupoDeDiscos().getVolumes());

        long bytes1 = 1000202273280L;
        long bytes = 240054796800L + 1000202273280L;
        double gigabytes = (double) bytes / 1000000000.0;
        gigabytes = Math.round(gigabytes * 100.0) / 100.0;
        System.out.println("O número em gigabytes é: " + gigabytes);
        double terabytes = (double) bytes / 1000000000000.0;
        terabytes = Math.round(terabytes * 100.0) / 100.0;
        System.out.println("O número em terabytes é: " + terabytes);
        
        List<Volume> volumeTotalUsado = looca.getGrupoDeDiscos().getVolumes();
        long disponivel = 0;
        long total = 0;
        
        for (Volume volume : volumeTotalUsado) {
            disponivel += volume.getDisponivel();
            total += volume.getTotal();
        }
        
        System.out.println(disponivel);
        System.out.println(total);
        
        
        double testeTotal  = (double) total / 1000000000.0;
        System.out.println("DISPONIVEL SEM MATH ROUND");
        System.out.println(testeTotal);
        System.out.println("DIPONIVEL COM MATH ROUND");
        testeTotal = Math.round(testeTotal * 100.0) / 100.0;
        System.out.println(testeTotal);
        
        double testeDisponivel  = (double) disponivel / 1000000000.0;
        System.out.println("DISPONIVEL SEM MATH ROUND");
        System.out.println(testeDisponivel);
        System.out.println("DIPONIVEL COM MATH ROUND");
        testeDisponivel = Math.round(testeDisponivel * 100.0) / 100.0;
        System.out.println(testeDisponivel);
        
        Double espacoUtilizado = (double) (testeTotal - testeDisponivel);
        Double porcentagemDeUso = (espacoUtilizado / testeTotal) * 100.0;
        porcentagemDeUso = Math.round(porcentagemDeUso * 100.0) / 100.0;
        System.out.println("PORCETAGEM DE USO ->>>>>>>   " + porcentagemDeUso);
    }
}
