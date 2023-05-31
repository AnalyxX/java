package com.br.api.dados;

import com.br.api.banco.jdbc.AlertaLimite;
import com.br.api.banco.jdbc.EspecificacaoMaquina;
import com.br.api.banco.jdbc.Monitoramento;
import com.br.api.banco.jdbc.Slack;
import com.br.api.banco.jdbc.controller.SlackController;
import com.br.api.banco.jdbc.controller.AlertaController;
import com.br.api.banco.jdbc.controller.AlertaLimiteController;
import com.br.api.banco.jdbc.controller.CpuController;
import com.br.api.banco.jdbc.controller.DiscoController;
import com.br.api.banco.jdbc.controller.EspecificacaoMaquinaController;
import com.br.api.banco.jdbc.controller.MemoriaController;
import com.br.api.banco.jdbc.controller.MonitoramentoController;
import com.br.api.banco.jdbc.controller.PacoteController;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.*;

/**
 *
 * @author carlo
 */
public class ApiSwing extends javax.swing.JFrame {

    /**
     * Creates new form ApiSwing
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public ApiSwing() throws IOException, InterruptedException {
        initComponents();
        startApp();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usoCPU = new javax.swing.JLabel();
        usoDisco = new javax.swing.JLabel();
        usoRam = new javax.swing.JLabel();
        sair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Uso da CPU:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Uso do Disco:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Uso da RAM:");

        sair.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sair.setForeground(new java.awt.Color(0, 0, 0));
        sair.setText("Minimizar");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usoRam)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usoDisco))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(60, 60, 60)
                        .addComponent(usoCPU)))
                .addContainerGap(272, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sair)
                .addGap(209, 209, 209))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usoCPU))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usoDisco))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(usoRam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(sair)
                .addGap(57, 57, 57))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        
    }//GEN-LAST:event_sairActionPerformed

    private void startApp() throws InterruptedException, IOException{
        MonitoramentoController monitoramentoDAO = new MonitoramentoController();
        CpuController cpuDAO = new CpuController();
        DiscoController discoDAO = new DiscoController();
        MemoriaController memoriaDAO = new MemoriaController();
        EspecificacaoMaquinaController emDAO = new EspecificacaoMaquinaController();
        PacoteController pacoteDAO = new PacoteController();
        AlertaLimiteController alertaLimiteDAO = new AlertaLimiteController();
        AlertaController alertaDAO  = new AlertaController();
        Looca looca = new Looca();
        JSONObject json = new JSONObject();
        SlackController urlSlack = new SlackController();
        String webhook = urlSlack.getSlackWebhook();
        
        Slack.URL = webhook;

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                
                Date dataHoraAtual = new Date();
                String dataAtual = new SimpleDateFormat("yyyy/MM/dd").format(dataHoraAtual);
                String horaAtual = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

                List<Volume> volumeTotalUsado = looca.getGrupoDeDiscos().getVolumes();
                long disponivel = 0;
                long total = 0;
                for (Volume volume : volumeTotalUsado) {
                    disponivel += volume.getDisponivel();
                    total += volume.getTotal();
                }
                Double espacoUtilizado = (double) (total - disponivel);
                Double usoDisco = (espacoUtilizado / total) * 100.0;
                usoDisco = Math.round(usoDisco * 100.0) / 100.0;

                Double memoriaUtilizada = (double) (looca.getMemoria().getTotal() - looca.getMemoria().getEmUso());
                Double usoRam = (memoriaUtilizada / looca.getMemoria().getTotal()) * 100.0;
                usoRam = Math.round(usoRam * 100.0) / 100.0;

                Double usoCpu = looca.getProcessador().getUso();
                
                Long bytesEnviados = 0L;
                Long bytesRecebidos = 0L;
                Long pacotesEnviados = 0L;
                Long pacotesRecebidos = 0L;

                List<RedeInterface> redes = new ArrayList<>(looca.getRede().getGrupoDeInterfaces().getInterfaces());
                for (RedeInterface rede : redes) {
                    if (rede.getBytesEnviados() > 0L || rede.getPacotesEnviados() > 0L) {
                        bytesEnviados += rede.getBytesEnviados();
                        bytesRecebidos += rede.getBytesRecebidos();
                        pacotesEnviados += rede.getPacotesEnviados();
                        pacotesRecebidos += rede.getPacotesRecebidos();
                    }
                }

                Double latencia;
                try {
                    InetAddress address = InetAddress.getByName("www.google.com.br");
                    long start = System.nanoTime();
                    if (address.isReachable(5000)) {
                        long end = System.nanoTime();
                        latencia = (end - start) / 1000000.0;
                    } else {
                        System.out.println("Host inacessível");
                        latencia = null;
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    latencia = null;
                }

//                EspecificacaoMaquina maquinaAtualLocal = emDAO.getEspecificacaoMaquinaPorHostNameLocal(
//                        looca.getRede().getParametros().getHostName());
//                monitoramentoDAO.insertMonitoramentoLocal(dataAtual, horaAtual, maquinaAtualLocal.getId());
//                Monitoramento monitoramentoAtualLocal = monitoramentoDAO.getMonitoramentoLocal(maquinaAtualLocal.getId());
//
//                pacoteDAO.insertPacotesLocal(latencia,
//                        pacotesEnviados,
//                        pacotesRecebidos,
//                        bytesRecebidos,
//                        bytesEnviados,
//                        monitoramentoAtualLocal.getId());
//                cpuDAO.insertUsoCpuLocal(usoCpu, monitoramentoAtualLocal.getId());
//                discoDAO.insertUsoDiscoLocal(usoDisco, monitoramentoAtualLocal.getId());
//                memoriaDAO.insertUsoRamLocal(usoRam, monitoramentoAtualLocal.getId());

                EspecificacaoMaquina maquinaAtualAzure = emDAO.getEspecificacaoMaquinaPorHostNameAzure(
                        looca.getRede().getParametros().getHostName());
                monitoramentoDAO.insertMonitoramentoAzure(dataAtual, horaAtual, maquinaAtualAzure.getId());
                Monitoramento monitoramentoAtualAzure = monitoramentoDAO.getMonitoramentoAzure(maquinaAtualAzure.getId());
                
                pacoteDAO.insertPacotesAzure(latencia,
                        pacotesEnviados,
                        pacotesRecebidos,
                        bytesRecebidos,
                        bytesEnviados,
                        monitoramentoAtualAzure.getId(),
                        maquinaAtualAzure.getId());
                cpuDAO.insertUsoCpuAzure(usoCpu, 
                        monitoramentoAtualAzure.getId(),
                        maquinaAtualAzure.getId());
                discoDAO.insertUsoDiscoAzure(usoDisco, 
                        monitoramentoAtualAzure.getId(),
                        maquinaAtualAzure.getId());
                memoriaDAO.insertUsoRamAzure(usoRam, 
                        monitoramentoAtualAzure.getId(),
                        maquinaAtualAzure.getId());
                
                AlertaLimite alertaLimite = alertaLimiteDAO.getAlertaLimiteAzure();
                Double verde = alertaLimite.getLimiteVerde();
                Double vermelho = alertaLimite.getLimiteVermelho();
                
                if (usoCpu <= verde && usoDisco <= verde && usoRam <= verde) {
                    alertaDAO.insertAlertaAzure("Normal", 1, 1, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else if (usoCpu > verde && usoCpu < vermelho  &&
                        usoDisco > verde && usoDisco < vermelho &&
                        usoRam > verde && usoRam < vermelho) {
                    alertaDAO.insertAlertaAzure("Alerta", 1, 2, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else {
                    alertaDAO.insertAlertaAzure("Crítico", 1, 3, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                    
                   try {               
                        String nome = maquinaAtualAzure.getHostName();
                        json.put("text", 
                               "Uma de suas máquinas está com uso crítico da CPU!"
                                        + " Nome da Máquina: "+ nome);

                       Slack.sendMessage(json);
                } catch (IOException | InterruptedException | JSONException e) {
                    e.printStackTrace();
                }
                }
                
                if (usoDisco <= verde) {
                    alertaDAO.insertAlertaAzure("Normal", 2, 1, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else if (usoDisco > verde && usoDisco < vermelho) {
                    alertaDAO.insertAlertaAzure("Alerta", 2, 2, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else {
                    alertaDAO.insertAlertaAzure("Crítico", 2, 3, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                    
                   try {               
                        String nome = maquinaAtualAzure.getHostName();
                        json.put("text", 
                                "Uma de suas máquinas está com uso crítico do Disco!"
                                        + " Nome da Máquina: "+ nome);

                        Slack.sendMessage(json);

                    } catch (IOException | InterruptedException | JSONException e) {
                       e.printStackTrace();
                    }
                }
                
                if (usoRam <= verde) {
                    alertaDAO.insertAlertaAzure("Normal", 3, 1, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else if (usoRam > verde && usoRam < vermelho) {
                    alertaDAO.insertAlertaAzure("Alerta", 3, 2, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                } else {
                    alertaDAO.insertAlertaAzure("Crítico", 3, 3, alertaLimite.getId(),
                            monitoramentoAtualAzure.getId(),
                            maquinaAtualAzure.getId()
                    );
                    
                    try {               
                        String nome = maquinaAtualAzure.getHostName();
                        json.put("text", 
                                "Uma de suas máquinas está com uso crítico do Disco!"
                                        + " Nome da Máquina: "+ nome);

                        Slack.sendMessage(json);

                    } catch (IOException | InterruptedException | JSONException e) {
                       e.printStackTrace();
                    }
                }
                System.out.println("Monitoramento feito com sucesso, "
                        + "aguarde mais 1 minuto");
            }
        }, 0, 5000);//60000
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ApiSwing().setVisible(true);
                } catch (InterruptedException | IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton sair;
    private javax.swing.JLabel usoCPU;
    private javax.swing.JLabel usoDisco;
    private javax.swing.JLabel usoRam;
    // End of variables declaration//GEN-END:variables
}
