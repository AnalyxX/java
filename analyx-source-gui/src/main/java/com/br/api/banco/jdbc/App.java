/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.api.banco.jdbc;

import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author gibas
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException{
        JSONObject json = new JSONObject();
        
        json.put("text", "3° teste realizado!");
        
        Slack.sendMessage(json);
    }
}
