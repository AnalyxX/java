CREATE SCHEMA IF NOT EXISTS bd_analyx DEFAULT CHARACTER SET utf8 ;
USE bd_analyx;
-- drop database bd_analyx;
CREATE TABLE IF NOT EXISTS empresa (
  id INT NOT NULL AUTO_INCREMENT,
  cnpj CHAR(17) NULL,
  razaoSocial VARCHAR(128) NULL,
  responsavel VARCHAR(45) NULL,
  telefone CHAR(13) NULL,
  email VARCHAR(256) NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS cpu (
  id INT NOT NULL AUTO_INCREMENT,
  modeloCPU VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS disco (
  id INT NOT NULL AUTO_INCREMENT,
  volume VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS ram (
  id INT NOT NULL AUTO_INCREMENT,
  total VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS especificacaoMaquina (
  id INT NOT NULL AUTO_INCREMENT,
  numeroSerial VARCHAR(45) NULL,
  cpu INT NOT NULL,
  disco INT NOT NULL,
  ram INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_especificacaoMaquina_cpu1
    FOREIGN KEY (cpu)
    REFERENCES cpu (id),
  CONSTRAINT fk_especificacaoMaquina_Disco1
    FOREIGN KEY (disco)
    REFERENCES disco (id),
  CONSTRAINT fk_especificacaoMaquina_ram1
    FOREIGN KEY (ram)
    REFERENCES ram (id));

CREATE TABLE IF NOT EXISTS setor (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS funcionario (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NULL,
  matricula VARCHAR(45) NULL UNIQUE,
  empresa INT NULL,
  gestor INT NULL,
  maquina INT NULL,
  setor INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Funcionario_Empresa1
    FOREIGN KEY (empresa)
    REFERENCES empresa (id),
  CONSTRAINT fk_Funcionario_Funcionario1
    FOREIGN KEY (gestor)
    REFERENCES funcionario (id),
  CONSTRAINT fk_Funcionario_EspecificacaoMaquina1
    FOREIGN KEY (maquina)
    REFERENCES especificacaoMaquina (id),
  CONSTRAINT fk_Funcionario_setor1
    FOREIGN KEY (setor)
    REFERENCES setor (id));

CREATE TABLE IF NOT EXISTS tipoUsuario (
  id INT NOT NULL AUTO_INCREMENT,
  tipoUsuario VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS usuario (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(256) NULL UNIQUE,
  senha VARCHAR(256) NULL,
  tipo INT NOT NULL,
  funcionario INT NOT NULL,
  PRIMARY KEY (id, funcionario),
  CONSTRAINT fk_Usuario_Funcionario1
    FOREIGN KEY (funcionario)
    REFERENCES funcionario (id),
  CONSTRAINT fk_Usuario_tipoUsuario1
    FOREIGN KEY (tipo)
    REFERENCES tipoUsuario (id));

CREATE TABLE IF NOT EXISTS monitoramento (
  id INT NOT NULL AUTO_INCREMENT,
  data DATE NULL,
  maquina INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Monitoramento_EspecificacaoMaquina1
    FOREIGN KEY (maquina)
    REFERENCES especificacaoMaquina (id));

CREATE TABLE IF NOT EXISTS rede (
  id INT NOT NULL AUTO_INCREMENT,
  latencia INT NULL,
  upload INT NULL,
  download INT NULL,
  monitoramento INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_rede_Monitoramento1
    FOREIGN KEY (monitoramento)
    REFERENCES monitoramento (id));

CREATE TABLE IF NOT EXISTS tipoComponente (
  id INT NOT NULL AUTO_INCREMENT,
  tipoComponente VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS componente (
  id INT NOT NULL AUTO_INCREMENT,
  uso FLOAT NULL,
  monitoramento INT NOT NULL,
  tipoComponente INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_componente_Monitoramento1
    FOREIGN KEY (monitoramento)
    REFERENCES monitoramento (id),
  CONSTRAINT fk_componente_table11
    FOREIGN KEY (tipoComponente)
    REFERENCES tipoComponente (id));

CREATE TABLE IF NOT EXISTS tipoCategoria (
  id INT NOT NULL AUTO_INCREMENT,
  tipoCategoria VARCHAR(45) NULL,
  descricao VARCHAR(45) NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS alertas (
  id INT NOT NULL AUTO_INCREMENT,
  nivelGravidade VARCHAR(45) NULL,
  monitoramento INT NOT NULL,
  tipoComponente INT NOT NULL,
  tipoCategoria INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Alertas_Monitoramento1
    FOREIGN KEY (monitoramento)
    REFERENCES monitoramento (id),
  CONSTRAINT fk_Alertas_table11
    FOREIGN KEY (tipoComponente)
    REFERENCES tipoComponente (id),
  CONSTRAINT fk_Alertas_tipoCategoria1
    FOREIGN KEY (tipoCategoria)
    REFERENCES tipoCategoria (id));

CREATE TABLE IF NOT EXISTS log (
  id INT NOT NULL AUTO_INCREMENT,
  descrição VARCHAR(45) NULL,
  componente INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Log_componente1
    FOREIGN KEY (componente)
    REFERENCES componente (id));
    
insert into tipoUsuario values
(null,'web'),
(null,'java');

insert into setor value
(null, 'DevOps');
desc funcionario;
insert into empresa value
(null, '00000000000100', 'SPTech School','Rafel Petry','(11)959595959','rafael.petry@sptech.school');

insert into cpu value 
(null,'i5');

insert into disco value
(null,'1tb');

insert into ram value
(null,'8bg');
desc especificacaoMaquina;
insert into especificacaoMaquina values
(null,'06515KLN51',1,1,1);

insert into funcionario value
(null,'Marcio','01191000',1,null,null,1);

insert into funcionario value
(null,'Joao','01202000',1,1,null,1);

insert into usuario values
(null,'marcio@email.com','1234',1,1),
(null,'teste@email.com','1234',2,2);

insert into monitoramento value
(null,'2023-04-23',1);

insert into tipoComponente values
(null,'cpu'),
(null,'disco'),
(null,'ram');

insert into componente value
(null,50,1,1),
(null,70,1,2),
(null,30,1,3);