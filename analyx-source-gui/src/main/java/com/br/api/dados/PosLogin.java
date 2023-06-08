package com.br.api.dados;

import com.br.api.banco.jdbc.AlertaLimite;
import com.br.api.banco.jdbc.EspecificacaoMaquina;
import com.br.api.banco.jdbc.Monitoramento;
import com.br.api.banco.jdbc.Slack;
import com.br.api.banco.jdbc.controller.AlertaController;
import com.br.api.banco.jdbc.controller.AlertaLimiteController;
import com.br.api.banco.jdbc.controller.CpuController;
import com.br.api.banco.jdbc.controller.DiscoController;
import com.br.api.banco.jdbc.controller.EspecificacaoMaquinaController;
import com.br.api.banco.jdbc.controller.MemoriaController;
import com.br.api.banco.jdbc.controller.MonitoramentoController;
import com.br.api.banco.jdbc.controller.PacoteController;
import com.br.api.banco.jdbc.controller.SlackController;
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
import org.json.JSONObject;

/**
 *
 * @author carlo
 */
public class PosLogin extends javax.swing.JFrame {

    /**
     * Creates new form LoginSwing
     */
    private Logger log;

    public PosLogin() throws IOException, InterruptedException {
        initComponents();
        startApp();
    }

    private void startApp() throws InterruptedException, IOException {
        MonitoramentoController monitoramentoDAO = new MonitoramentoController();
        CpuController cpuDAO = new CpuController();
        DiscoController discoDAO = new DiscoController();
        MemoriaController memoriaDAO = new MemoriaController();
        EspecificacaoMaquinaController emDAO = new EspecificacaoMaquinaController();
        PacoteController pacoteDAO = new PacoteController();
        AlertaLimiteController alertaLimiteDAO = new AlertaLimiteController();
        AlertaController alertaDAO = new AlertaController();
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

                Double memoriaUtilizada = (double) (looca.getMemoria().getTotal() - 
                        looca.getMemoria().getEmUso());
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

                Double latencia = 0.0;
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

                EspecificacaoMaquina maquinaAtualLocal = emDAO.getEspecificacaoMaquinaPorHostNameLocal(
                        looca.getRede().getParametros().getHostName());
                monitoramentoDAO.insertMonitoramentoLocal(dataAtual, horaAtual, maquinaAtualLocal.getId());
                Monitoramento monitoramentoAtualLocal = monitoramentoDAO.getMonitoramentoLocal(maquinaAtualLocal.getId());

                pacoteDAO.insertPacotesLocal(latencia,
                        pacotesEnviados,
                        pacotesRecebidos,
                        bytesRecebidos,
                        bytesEnviados,
                        monitoramentoAtualLocal.getId());
                cpuDAO.insertUsoCpuLocal(usoCpu, monitoramentoAtualLocal.getId());
                discoDAO.insertUsoDiscoLocal(usoDisco, monitoramentoAtualLocal.getId());
                memoriaDAO.insertUsoRamLocal(usoRam, monitoramentoAtualLocal.getId());

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
                } else if (usoCpu > verde && usoCpu < vermelho
                        && usoDisco > verde && usoDisco < vermelho
                        && usoRam > verde && usoRam < vermelho) {
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
                                + " Nome da Máquina: " + nome);

                        Slack.sendMessage(json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Monitoramento feito com sucesso, "
                        + "aguarde mais 1 minuto");
            }
        }, 0, 5000);//60000
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_resposta = new javax.swing.JLabel();
        lbl_verify = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_resposta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lbl_resposta.setForeground(java.awt.Color.white);
        lbl_resposta.setToolTipText("");

        lbl_verify.setBackground(new java.awt.Color(69, 73, 74));
        lbl_verify.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_verify.setForeground(new java.awt.Color(180, 0, 0));

        jPanel1.setBackground(new java.awt.Color(39, 67, 96));
        jPanel1.setAutoscrolls(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(java.awt.Color.white);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("J� estamos capturando os dados necess�rios");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Obrigado por usar nossa aplica��o");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(lbl_verify, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl_resposta, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(lbl_verify)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_resposta))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PosLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PosLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PosLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PosLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PosLogin().setVisible(true);
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_resposta;
    private javax.swing.JLabel lbl_verify;
    // End of variables declaration//GEN-END:variables
}
