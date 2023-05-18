package com.br.api.banco.jdbc.controller;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;

public class TesteController {

    public static void main(String[] args) {
        UsuarioController loginDAO = new UsuarioController();
        CpuController cpuDAO = new CpuController();
        DiscoController discoDAO = new DiscoController();
        MemoriaController memoriaDAO = new MemoriaController();
        Looca looca = new Looca();
        Memoria memoria = looca.getMemoria();
        
        try {
        System.out.println(loginDAO.entrarMySql("teste@email.com", "1234"));
        System.out.println(cpuDAO.leituraCpu());
        System.out.println(discoDAO.leituraDisco());
        System.out.println(memoriaDAO.leituraMemoria());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
