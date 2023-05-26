package com.br.api.banco.jdbc.controller;

import com.br.api.banco.jdbc.Conexao;
import com.br.api.banco.jdbc.ConexaoAzure;
import com.br.api.banco.jdbc.Cpu;
import com.br.api.banco.jdbc.EspecificacaoMaquina;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author carlo
 */
public class EspecificacaoMaquinaController {

    ConexaoAzure conexaoAzure = new ConexaoAzure();

    JdbcTemplate conAzure = conexaoAzure.getConexaoDoBanco();

    Conexao conexao = new Conexao();

    JdbcTemplate con = conexao.getConexaoDoBanco();

    Looca looca = new Looca();
    CpuController cpuDAO = new CpuController();
    DiscoController discoDAO = new DiscoController();
    MemoriaController memoriaDAO = new MemoriaController();

    public void insertMaquinaAzure(String hostName, Integer fkCpu, Integer fkDisco, Integer fkRam) {

        conAzure.update("insert into especificacaoMaquina values (null, ?, ?, ?, ?)",
                hostName, fkCpu, fkDisco, fkRam);
    }

    public void insertMaquinaLocal(String hostName, Integer fkCpu, Integer fkDisco, Integer fkRam) {

        conAzure.update("insert into especificacaoMaquina values (null, ?, ?, ?, ?)",
                hostName, fkCpu, fkDisco, fkRam);
    }

    public void verificaCadastroDaMaquina(String hostName) {

        List<EspecificacaoMaquina> cadastro = conAzure.query("select id, "
                + "hostName, "
                + "fkCpu as cpu, "
                + "fkDisco as disco, "
                + "fkRam as ram "
                + "from especificacaoMaquina where hostName = ?",
                new BeanPropertyRowMapper(EspecificacaoMaquina.class),
                hostName);

        if (cadastro.isEmpty()) {
            Long totalRam = looca.getMemoria().getTotal();
            List<Volume> volumes = looca.getGrupoDeDiscos().getVolumes();
            long total = 0;
            for (Volume volume : volumes) {
                total += volume.getTotal();
            }
            cpuDAO.insertCpuMaquinaAzure(looca.getProcessador().getNome());
            discoDAO.insertDiscoMaquinaAzure(total);
            memoriaDAO.insertMemoriaMaquinaAzure(totalRam);
            System.out.println("A máquina foi cadastrada na Azure");
        } else {
            System.out.println("A máquina já está cadastrada na Azure");
        }

    }

    public void cadastrarMaquina(String hostName) {

    }

    public EspecificacaoMaquina getEspecificacaoMaquinaPorHostNameLocal(String hostName) {

        return con.queryForObject("select id, "
                + "hostName, "
                + "fkCpu as cpu, "
                + "fkDisco as disco, "
                + "fkRam as ram "
                + "from especificacaoMaquina where hostName = ?",
                new BeanPropertyRowMapper<EspecificacaoMaquina>(EspecificacaoMaquina.class),
                hostName);
    }
}
