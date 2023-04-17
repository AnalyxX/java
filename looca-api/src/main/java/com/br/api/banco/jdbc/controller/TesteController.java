package com.br.api.banco.jdbc.controller;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.util.Conversor;


public class TesteController {

    public static void main(String[] args) {
        LoginController loginDAO = new LoginController();
        MemoriaController memoriaDAO = new MemoriaController();
        Looca looca = new Looca();
        Conversor conversor = new Conversor();
        Memoria memoria = looca.getMemoria();
  
        System.out.println("CONVERSOR");
        System.out.println(conversor.formatarBytes(memoria.getEmUso()));

        System.out.println("----------------------------");
        System.out.println("GET (EM BYTES)");
        System.out.println(memoria.getEmUso());

        try {
            System.out.println(loginDAO.entrar("teste", "1234"));
            System.out.println("");
            memoriaDAO.registrarUso(memoria.getEmUso(), memoria.getDisponivel());
            System.out.println(memoriaDAO.showAll());

        } catch (Exception e) {
            System.out.println("Mensagem de erro MYSQL -> " + e.getMessage());
        }
    }
}
