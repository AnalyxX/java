CREATE SCHEMA IF NOT EXISTS bd_analyx DEFAULT CHARACTER SET utf8 ;
USE bd_analyx;

-- drop database bd_analyx;

CREATE TABLE IF NOT EXISTS empresa (
  id INT NOT NULL AUTO_INCREMENT,
  cnpj CHAR(17) NOT NULL,
  razaoSocial VARCHAR(128) NOT NULL,
  responsavel VARCHAR(45) NOT NULL,
  telefone CHAR(13) NOT NULL,
  email VARCHAR(256) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS cpu (
  id INT NOT NULL AUTO_INCREMENT,
  modeloCPU VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS disco (
  id INT NOT NULL AUTO_INCREMENT,
  volume BIGINT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS ram (
  id INT NOT NULL AUTO_INCREMENT,
  total BIGINT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS especificacaoMaquina (
  id INT NOT NULL AUTO_INCREMENT,
  hostName VARCHAR(100) NOT NULL,
  fkCpu INT NOT NULL,
  fkDisco INT NOT NULL,
  fkRam INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_especificacaoMaquina_cpu1
    FOREIGN KEY (fkcpu)
    REFERENCES cpu (id),
  CONSTRAINT fk_especificacaoMaquina_Disco1
    FOREIGN KEY (fkDisco)
    REFERENCES disco (id),
  CONSTRAINT fk_especificacaoMaquina_ram1
    FOREIGN KEY (fkRam)
    REFERENCES ram (id));

CREATE TABLE IF NOT EXISTS setor (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS funcionario (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  matricula VARCHAR(45) NULL UNIQUE,
  fkEmpresa INT NULL,
  fkGestor INT NULL,
  fkMaquina INT NULL,
  fkSetor INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Funcionario_Empresa1
    FOREIGN KEY (fkEmpresa)
    REFERENCES empresa (id),
  CONSTRAINT fk_Funcionario_Funcionario1
    FOREIGN KEY (fkGestor)
    REFERENCES funcionario (id),
  CONSTRAINT fk_Funcionario_EspecificacaoMaquina1
    FOREIGN KEY (fkMaquina)
    REFERENCES especificacaoMaquina (id),
  CONSTRAINT fk_Funcionario_setor1
    FOREIGN KEY (fkSetor)
    REFERENCES setor (id));

CREATE TABLE IF NOT EXISTS tipoUsuario (
  id INT NOT NULL AUTO_INCREMENT,
  tipoUsuario VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS usuario (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(256) NOT NULL UNIQUE,
  senha VARCHAR(256) NOT NULL,
  fkTipoUsuario INT NOT NULL,
  fkFuncionario INT NOT NULL,
  PRIMARY KEY (id, fkFuncionario),
  CONSTRAINT fk_Usuario_Funcionario1
    FOREIGN KEY (fkFuncionario)
    REFERENCES funcionario (id),
  CONSTRAINT fk_Usuario_tipoUsuario1
    FOREIGN KEY (fkTipoUsuario)
    REFERENCES tipoUsuario (id));

CREATE TABLE IF NOT EXISTS monitoramento (
  id INT NOT NULL AUTO_INCREMENT,
  data DATE NOT NULL,
  hora TIME NOT NULL,
  fkMaquina INT NOT NULL,
  PRIMARY KEY (id,fkMaquina),
  CONSTRAINT fk_Monitoramento_EspecificacaoMaquina1
    FOREIGN KEY (fkMaquina)
    REFERENCES especificacaoMaquina (id));

CREATE TABLE IF NOT EXISTS pacote (
  id INT NOT NULL AUTO_INCREMENT,
  latencia DECIMAL(5,2) NOT NULL,
  pacotesEnviados INT NOT NULL,
  pacotesRecebidos INT NOT NULL,
  bytesEnviados INT NOT NULL,
  bytesRecebidos INT NOT NULL,
  fkMonitoramento INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_rede_Monitoramento1
    FOREIGN KEY (fkMonitoramento)
    REFERENCES monitoramento (id));

CREATE TABLE IF NOT EXISTS tipoComponente (
  id INT NOT NULL AUTO_INCREMENT,
  tipoComponente VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS componente (
  id INT NOT NULL AUTO_INCREMENT,
  uso DECIMAL(5,2) NOT NULL,
  fkMonitoramento INT NOT NULL,
  fkTipoComponente INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_componente_Monitoramento1
    FOREIGN KEY (fkMonitoramento)
    REFERENCES monitoramento (id),
  CONSTRAINT fk_componente_table11
    FOREIGN KEY (fkTipoComponente)
    REFERENCES tipoComponente (id));

CREATE TABLE IF NOT EXISTS tipoCategoria (
  id INT NOT NULL AUTO_INCREMENT,
  tipoCategoria VARCHAR(45) NOT NULL,
  descricao VARCHAR(45) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS alerta (
  id INT NOT NULL AUTO_INCREMENT,
  nivelGravidade VARCHAR(45) NOT NULL,
  fkMonitoramento INT NOT NULL,
  fkTipoComponente INT NOT NULL,
  fkTipoCategoria INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Alertas_Monitoramento1
    FOREIGN KEY (fkMonitoramento)
    REFERENCES monitoramento (id),
  CONSTRAINT fk_Alertas_table11
    FOREIGN KEY (fkTipoComponente)
    REFERENCES tipoComponente (id),
  CONSTRAINT fk_Alertas_tipoCategoria1
    FOREIGN KEY (fkTipoCategoria)
    REFERENCES tipoCategoria (id));
    
insert into tipoUsuario values
(null,'web'),
(null,'java');

insert into setor value
(null, 'DevOps');

insert into empresa value
(null, '00000000000100', 'SPTech School','Rafel Petry','(11)959595959','rafael.petry@sptech.school');

insert into cpu value 
(null,'i5');

insert into disco value
(null,'1000000000');

insert into ram value
(null,'80000');

insert into especificacaoMaquina value
(null,'joao-host',1,1,1);

insert into especificacaoMaquina value
(null,'teste-host',1,1,1);

insert into funcionario value
(null,'Marcio','01191000',1,null,null,1);

insert into funcionario value
(null,'Joao','01202000',1,1,1,1);

insert into usuario values
(null,'marcio@email.com','1234',1,1),
(null,'teste@email.com','1234',2,2);

insert into monitoramento value
(null,'2023-04-23','14:30:00',1);

insert into tipoComponente values
(null,'cpu'),
(null,'disco'),
(null,'ram');

insert into componente value
(null,50,1,1),
(null,70,1,2),
(null,30,1,3);
	
-- select * from monitoramento;
         
select f.id as idUsuario,
	   f.nome,
       f.fkMaquina,
       em.id as idMaquina,
       em.hostname 
       from funcionario f
		 join especificacaoMaquina em
         on f.fkMaquina = em.id;
         
-- select * from usuario;
         
select f.id,
	   f.nome,
       f.fkMaquina,
       em.id,
       em.hostname, 
       m.id,
       m.fkMaquina 
       from funcionario as f
		 join especificacaoMaquina as em
         on f.fkMaquina = em.id
         join monitoramento as m
         on em.id = m.fkMaquina;
         
insert into monitoramento value
(null,'2023-05-20','20:50',1);

insert into monitoramento value
(1,'2023-05-20','20:50',2);

insert into monitoramento value
(2,'2023-05-20','20:50',2);

insert into monitoramento value
(3,'2023-05-20','20:50',2);

-- select CONCAT(id,fkMaquina) as 'CHAVE_PRIMARIA' from monitoramento;

select u.id,
	   u.email,
	   u.senha,
		tu.tipoUsuario,
		f.nome as funcionario, 
		f.fkMaquina as maquina
		from usuario as u
			join tipoUsuario as tu
			on tu.id = u.fkTipoUsuario
				join funcionario as f
				on f.id = u.fkFuncionario 
						join especificacaoMaquina as em
						on f.fkMaquina = em.id;
                        
select * from componente c
		 join monitoramento m
         on c.fkMonitoramento = m.id where m.id = 2;

-- desc pacote;
-- select * from pacote;
-- select * from monitoramento;
-- select * from componente;
-- select * from monitoramento where fkMaquina = 2 order by id desc;
-- select * from funcionario;
-- select * from tipoComponente;
-- select * from usuario;
-- select * from especificacaomaquina;
-- desc especificacaomaquina;
-- desc componente;