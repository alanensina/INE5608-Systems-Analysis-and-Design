create database vadebike;

use vadebike;

CREATE TABLE endereco (
id int PRIMARY KEY NOT NULL auto_increment, 
logradouro VARCHAR (255), 
numero VARCHAR (255), 
complemento VARCHAR (255), 
bairro VARCHAR (255), 
cidade VARCHAR (255), 
estado VARCHAR (255), 
cep VARCHAR (255));

CREATE TABLE locatario (
id INTEGER PRIMARY KEY auto_increment NOT NULL, 
nome VARCHAR (255), 
cpf VARCHAR (255), 
idEndereco INT, 
celular VARCHAR (255), 
login VARCHAR (255), 
senha VARCHAR (255), 
dataCadastro DATE,
FOREIGN KEY (idEndereco) REFERENCES endereco(id));

CREATE TABLE locador (
id INTEGER PRIMARY KEY auto_increment NOT NULL, 
nome VARCHAR (255), 
cpf VARCHAR (255), 
idEndereco INT, 
celular VARCHAR (255), 
login VARCHAR (255), 
senha VARCHAR (255), 
dataCadastro DATE,
FOREIGN KEY (idEndereco) REFERENCES endereco(id));

CREATE TABLE bicicleta (
id INTEGER PRIMARY KEY auto_increment NOT NULL, 
idLocador INT, 
modelo VARCHAR (255), 
ano VARCHAR (255), 
valorDeAluguel DOUBLE, 
acessorios VARCHAR (255), 
disponivel VARCHAR(255),
FOREIGN KEY (idLocador) REFERENCES locador(id));

CREATE TABLE ponto (
id INTEGER PRIMARY KEY auto_increment, 
idLocador INT, 
nomeDoPonto VARCHAR (255), 
idEndereco INT,
FOREIGN KEY (idLocador) REFERENCES locador(id),
FOREIGN KEY (idEndereco) REFERENCES endereco(id)
);

CREATE TABLE aluguel (
id INTEGER PRIMARY KEY auto_increment NOT NULL, 
idLocador INT , 
idLocatario INT, 
idBicicleta INT, 
idPonto INT , 
inicioPrevisto DATE, 
fimPrevisto DATE, 
valorTotal DOUBLE,
FOREIGN KEY (idLocador) REFERENCES locador(id),
FOREIGN KEY (idLocatario) REFERENCES locatario(id),
FOREIGN KEY (idPonto) REFERENCES ponto(id),
FOREIGN KEY (idBicicleta) REFERENCES bicicleta(id));

CREATE TABLE avaliacao (
id INTEGER PRIMARY KEY auto_increment NOT NULL, 
idAluguel INT , 
notaLocadorParaLocatario DOUBLE, 
notaLocatarioParaLocador DOUBLE,
FOREIGN KEY (idAluguel) REFERENCES aluguel(id));

show tables;

