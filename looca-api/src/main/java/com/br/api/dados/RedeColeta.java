package com.br.api.dados;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import java.io.IOException;
import java.net.InetAddress;

public class RedeColeta {
    
    public static void main(String[] args) throws IOException {
        Looca looca = new Looca();

        Rede rede = looca.getRede();
        System.out.println("Informações da rede utilizada");
        System.out.println(rede.getParametros());

        System.out.println("Grupo de interface:");
        System.out.println(looca.getRede().getGrupoDeInterfaces().getInterfaces());

        InetAddress address = InetAddress.getByName("www.google.com.br");
        long start = System.nanoTime();
        if (address.isReachable(5000)) {
            long end = System.nanoTime();
            System.out.println("Latência: " + (end - start) / 1000000.0 + " ms");
        } else {
            System.out.println("Host inacessível");
        }

    }
}