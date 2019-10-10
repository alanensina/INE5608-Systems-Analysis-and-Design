show databases;

create database vadebike;

use vadebike;

create table endereco(
id int not null auto_increment,
logradouro varchar(255),
numero varchar(255),
complemento varchar(255),
bairro varchar(255),
cidade varchar(255),
estado varchar(255),
cep varchar(255),

 CONSTRAINT pk_endereco PRIMARY KEY (id)
);

create table locatario(
id int not null auto_increment,
nome varchar(255),
cpf varchar(255),
idEndereco int not null,
celular varchar(255),
login varchar(255),
senha varchar(255),
dataCadastro date,

 CONSTRAINT pk_locatario PRIMARY KEY (id),
 CONSTRAINT fk_locatario_endereco FOREIGN KEY(idEndereco) REFERENCES endereco (id)
);

create table locador(
id int not null auto_increment,
nome varchar(255),
cpf varchar(255),
idEndereco int not null,
celular varchar(255),
login varchar(255),
senha varchar(255),
dataCadastro date,

 CONSTRAINT pk_locador PRIMARY KEY (id),
 CONSTRAINT fk_locador_endereco FOREIGN KEY(idEndereco) REFERENCES endereco (id)
);

create table bicicleta(
id int not null auto_increment,
idLocador int not null,
modelo varchar(255),
ano varchar(255),
valorDeAluguel varchar(255),
acessorios varchar(255),
disponivel boolean,

 CONSTRAINT pk_bicicleta PRIMARY KEY (id),
 CONSTRAINT fk_bicicleta_locador FOREIGN KEY(idLocador) REFERENCES locador (id)
);

create table aluguel(
id int not null auto_increment,
idLocador int not null,
idLocatario int not null,
idBicicleta int not null,
dtInicio date,
dtFimPrevisto date,
dtFimRealizado date,
valorPrevisto double, -- valor dado na solicitação do aluguel
valorMulta double default 0,
valorFinal double, -- valorPrevisto +  valorMulta
cancelado boolean default false, -- será true se o locador cancelar a solicitação de aluguel

 CONSTRAINT pk_aluguel PRIMARY KEY (id),
 CONSTRAINT fk_aluguel_locador FOREIGN KEY(idLocador) REFERENCES locador (id),
 CONSTRAINT fk_aluguel_locatario FOREIGN KEY(idLocatario) REFERENCES locatario (id),
 CONSTRAINT fk_aluguel_bicicleta FOREIGN KEY(idBicicleta) REFERENCES bicicleta (id)
);

create table avaliacao (
id int not null auto_increment,
idAluguel int not null,
notaDoLocadorParaLocatario double,
notaDoLocatarioParaLocador double,

 CONSTRAINT pk_avaliacao PRIMARY KEY (id),
 CONSTRAINT fk_avaliacao_aluguel FOREIGN KEY(idAluguel) REFERENCES aluguel (id)
);

create table carteiraLocador(

id int not null auto_increment,
idLocador int not null,
saldo double default 0

CONSTRAINT pk_carteiraLocador PRIMARY KEY (id),
CONSTRAINT fk_carteiraLocador_locador FOREIGN KEY(idLocador) REFERENCES locador (id)
);

create table carteiraLocatario(

id int not null auto_increment,
idLocatario int not null,
multaAcumulada double default 0

CONSTRAINT pk_carteiraLocatario PRIMARY KEY (id),
CONSTRAINT fk_carteiraLocatario_locatario FOREIGN KEY(idLocatario) REFERENCES locatario (id)
);