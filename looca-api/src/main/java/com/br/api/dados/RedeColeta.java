package com.br.api.dados;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;

public class RedeColeta {
    public static void main(String[] args) {
        Looca looca = new Looca();

        Rede rede = looca.getRede();
        System.out.println("Informações da rede utilizada");
        System.out.println(rede.getParametros());

        System.out.println("Grupo de interface:");
        System.out.println(looca.getRede().getGrupoDeInterfaces().getInterfaces());

    }
}
