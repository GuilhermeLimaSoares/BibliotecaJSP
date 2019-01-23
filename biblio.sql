create database biblio;

create table biblio.usuarios (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255),
  login VARCHAR(255),
  senha VARCHAR(255),
  status VARCHAR(255),
  tipo VARCHAR(255),
  primary key (id));

create table biblio.pessoas (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255),
  cpf VARCHAR(255),
  tipo VARCHAR(255),
  email VARCHAR(255),
  primary key (id));

create table biblio.usuarios_pessoas (
  id BIGINT NOT NULL AUTO_INCREMENT,
  idPessoa BIGINT,
  idUsuario BIGINT, 
  observacao VARCHAR(255),
  primary key (id));
  
 --  create table biblio.cliente (
--   id BIGINT NOT NULL AUTO_INCREMENT,
--   mesa BIGINT,
--   status VARCHAR(255),
--   primary key (id));

create table biblio.cliente (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255),
  telefone VARCHAR(255),
  endereco VARCHAR(255),
  status VARCHAR(255),
  tipo VARCHAR(255),
  primary key (id));
  
-- --- ---  create table biblio.comanda(
-- --  --  id BIGINT NOT NULL AUTO_INCREMENT,*/
-- -- --   id BIGINT NOT NULL AUTO_INCREMENT,
-- -- --   lanche VARCHAR(255),
-- -- --   bebida VARCHAR(255),
-- -- --   sobremesa VARCHAR(255),
-- -- --   salada VARCHAR(255),
-- -- --   utensilios VARCHAR(255),
-- -- --   primary key (id));
-- -- 

create table biblio.livro (
  id BIGINT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(255),
  autor VARCHAR(255),
  categoria VARCHAR(255),
  idioma VARCHAR(255),
  editora VARCHAR(255),
  primary key (id));
  
create table biblio.livro_cliente (
  id BIGINT NOT NULL AUTO_INCREMENT,
  idCliente BIGINT,
  idLivro BIGINT, 
  observacao VARCHAR(255),
  primary key (id));

ALTER TABLE biblio.usuarios_pessoas ADD CONSTRAINT FK_PES_USU_ID FOREIGN KEY (IDPESSOA) REFERENCES biblio.pessoas (ID);

ALTER TABLE biblio.usuarios_pessoas ADD CONSTRAINT FK_USU_PES_ID FOREIGN KEY (IDUSUARIO) REFERENCES biblio.usuarios (ID);  

ALTER TABLE biblio.livro_cliente ADD CONSTRAINT FK_LIO_CLI_ID FOREIGN KEY (IDLIVRO) REFERENCES biblio.livro (ID);

ALTER TABLE biblio.livro_cliente ADD CONSTRAINT FK_CLI_LIO_ID FOREIGN KEY (IDCLIENTE) REFERENCES biblio.cliente (ID);  

INSERT INTO `biblio`.`pessoas` (`id`, `nome`, `cpf`, `tipo`, `email`) VALUES ('1', 'Carla Veronica', '12345', 'aluno', 'carlinha@gmail.com');
INSERT INTO `biblio`.`pessoas` (`id`, `nome`, `cpf`, `tipo`, `email`) VALUES ('2', 'Bruno Mesquita', '54321', 'aluno', 'bruno.mesquita@gmail.com');

INSERT INTO `biblio`.`usuarios` (`id`, `nome`, `login`, `senha`, `status`, `tipo`) VALUES ('1', 'CARLA VERONICA', 'carlinha@gmail.com', '12345', 'ATIVO', 'ALUNO');
INSERT INTO `biblio`.`usuarios` (`id`, `nome`, `login`, `senha`, `status`, `tipo`) VALUES ('2', 'BRUNO MESQUITA', 'bruno.mesquita@gmail.com', '54321', 'ATIVO', 'ALUNO');

INSERT INTO `biblio`.`usuarios_pessoas` (`id`, `idPessoa`, `idUsuario`, `observacao`) VALUES ('1', '1', '1', 'TESTE CARLA');
INSERT INTO `biblio`.`usuarios_pessoas` (`id`, `idPessoa`, `idUsuario`, `observacao`) VALUES ('2', '2', '2', 'TESTE BRUNO');


INSERT INTO `biblio`.`livro` (`id`, `titulo`, `autor`, `categoria`, `idioma`, `editora`) VALUES ('1', 'Orgulhoo e Preconceito', 'Jane Austen', 'romance', 'ingles', 'cia das letras');
INSERT INTO `biblio`.`livro` (`id`, `titulo`, `autor`, `categoria`, `idioma`, `editora`) VALUES ('2', 'Crime e Castigo', 'Fiodor Dostoievski ', 'romance', 'russo', 'abril');

INSERT INTO `biblio`.`cliente` (`id`, `nome`, `telefone`, `endereco`, `status`, `tipo`) VALUES ('1', 'CARLA VERONICA', '5498-0040', 'rua canario', 'ATIVO', 'ALUNO');
INSERT INTO `biblio`.`cliente` (`id`, `nome`, `telefone`, `endereco`, `status`, `tipo`) VALUES ('2', 'BRUNO MESQUITA', '5440-0089', 'rua amora', 'ATIVO', 'ALUNO');

INSERT INTO `biblio`.`livro_cliente` (`id`, `idCliente`, `idLivro`, `observacao`) VALUES ('1', '1', '1', 'Esperando');
INSERT INTO `biblio`.`livro_cliente` (`id`, `idCliente`, `idLivro`, `observacao`) VALUES ('2', '1', '1', 'Esperando');
