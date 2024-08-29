---------------------
-- Aula 29/08/2024 --
---------------------

CREATE SCHEMA loja;

CREATE TABLE loja.cliente {
    cpf INT(11),
    nome VARCHAR(200),
    email VARCHAR(200) NOT NULL,

    CONSTRAINT pk_cliente PRIMARY KEY(cpf),
    CONSTRAINT uk_cliente_email UNIQUE(email),
};

CREATE TABLE loja.endereco {
    num INT,
    estado CHAR(2),
    cidade VARCHAR(200),
    bairro VARCHAR(200),
    rua VARCHAR(200),
    cep INT(8),

    CONSTRAINT pk_endereco PRIMARY KEY(num, cep),
};

CREATE TABLE loja.pedido {
    id_pedido INT,
    valor_total DECIMAL(7,2),
    data_pedido 
};

CREATE TABLE loja.item_pedido {

};

CREATE TABLE loja.action_figure {

};

CREATE TABLE loja.material {

};

CREATE TABLE loja.imagem {

};