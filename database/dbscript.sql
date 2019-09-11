--
-- File generated with SQLiteStudio v3.2.1 on ter set 10 21:03:53 2019
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: aluguel
CREATE TABLE aluguel (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, idLocador INT REFERENCES locador (id), idLocatario INT REFERENCES locatario (id), idBicicleta INT REFERENCES bicicleta (id), idPonto INT REFERENCES ponto (id), inicioPrevisto DATE, fimPrevisto DATE, valorTotal DOUBLE);

-- Table: avaliacao
CREATE TABLE avaliacao (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, idAluguel INT REFERENCES aluguel (id), notaLocadorParaLocatario DOUBLE, notaLocatarioParaLocador DOUBLE);

-- Table: bicicleta
CREATE TABLE bicicleta (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, idLocador INT REFERENCES locador (id), modelo VARCHAR (50), ano VARCHAR (10), valorDeAluguel DOUBLE, acessorios VARCHAR (255), disponivel BOOLEAN);

-- Table: endereco
CREATE TABLE endereco (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, logradouro VARCHAR (255), numero VARCHAR (30), complemento VARCHAR (255), bairro VARCHAR (100), cidade VARCHAR (50), estado VARCHAR (10), cep VARCHAR (20));

-- Table: locador
CREATE TABLE locador (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome VARCHAR (255), cpf VARCHAR (30), idEndereco INT CONSTRAINT fk_end_locador REFERENCES endereco (id), celular VARCHAR (30), login VARCHAR (30), senha VARCHAR (30), dataCadastro DATE);

-- Table: locatario
CREATE TABLE locatario (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nome VARCHAR (255), cpf VARCHAR (30), idEndereco INT REFERENCES endereco (id), celular VARCHAR (30), login VARCHAR (30), senha VARCHAR (30), dataCadastro DATE);

-- Table: ponto
CREATE TABLE ponto (id INTEGER PRIMARY KEY AUTOINCREMENT, idLocador INT REFERENCES locador (id), nomeDoPonto VARCHAR (255), idEndereco INT REFERENCES endereco (id));

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
