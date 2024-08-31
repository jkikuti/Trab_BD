---------------------
-- Aula 29/08/2024 --
---------------------

// relações
// cliente 1-tem_endereco=n enderecos
// pedido 1=tem_cliente-n clientes
// pedido 1-tem_item_pedido=n itens_pedido
// item_pedido n=tem_action_figure-1 action_figure
// action_figure 1=tem_material-n materiais
// action_figure 1-tem_imagem=n imagens
// a partir dessas informações gerar chave estrangeira

CREATE SCHEMA loja;

CREATE TABLE loja.cliente {
    id_cliente INT AUTO_INCREMENT,
    cpf INT(11),
    nome VARCHAR(200),
    email VARCHAR(200) NOT NULL,

    CONSTRAINT pk_cliente PRIMARY KEY(id_cliente),
    CONSTRAINT uk_cliente_cpf UNIQUE(cpf),
    CONSTRAINT uk_cliente_email UNIQUE(email),

    CONSTRAINT fk_cliente_endereco FOREIGN KEY(id_endereco) REFERENCES endereco(id_endereco),
    CONSTRAINT fk_cliente_pedido FOREIGN KEY(id_pedido) REFERENCES pedido(id_pedido),
};

CREATE TABLE loja.endereco {
    id_endereco INT AUTO_INCREMENT,
    num INT,
    estado CHAR(2),
    cidade VARCHAR(200),
    bairro VARCHAR(200),
    rua VARCHAR(200),
    cep INT(8),

    CONSTRAINT pk_endereco PRIMARY KEY(id_endereco),
    CONSTRAINT uk_endereco_cliente UNIQUE(num, cep),

    CONSTRAINT fk_endereco_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente),
};

CREATE TABLE loja.pedido {
    id_pedido INT AUTO_INCREMENT,
    valor_total DECIMAL(7,2),
    data_pedido TIMESTAMP,

    CONSTRAINT pk_pedido PRIMARY KEY(id_pedido),

    CONSTRAINT fk_pedido_cliente FOREIGN KEY(id_cliente) REFERENCES cliente(id_cliente),
    CONSTRAINT fk_pedido_item_pedido FOREIGN KEY(id_item_pedido) REFERENCES item_pedido(id_item_pedido),
};

CREATE TABLE loja.item_pedido {
    id_item_pedido INT AUTO_INCREMENT,
    quantidade INT,

    CONSTRAINT pk_item_pedido PRIMARY KEY(id_item_pedido),

    CONSTRAINT fk_item_pedido_pedido FOREIGN KEY(id_pedido) REFERENCES pedido(id_pedido),
    CONSTRAINT fk_item_pedido_action_figure FOREIGN KEY(id_action_figure) REFERENCES action_figure(id_action_figure),
};

CREATE TABLE loja.action_figure {
    id_action_figure INT AUTO_INCREMENT,
    personagem VARCHAR(200),
    universo VARCHAR(200),
    fabricante VARCHAR(200),
    tamanho INT,
    preco DECIMAL(7,2),
    descricao TEXT,
    categoria VARCHAR(200),

    CONSTRAINT pk_action_figure PRIMARY KEY(id_action_figure),

    CONSTRAINT fk_action_figure_material FOREIGN KEY(id_material) REFERENCES material(id_material),
    CONSTRAINT fk_action_figure_imagem FOREIGN KEY(id_imagem) REFERENCES imagem(id_imagem),
};

CREATE TABLE loja.material {
    id_material INT AUTO_INCREMENT,
    tipo_material VARCHAR(200),

    CONSTRAINT pk_material PRIMARY KEY(id_material),

    CONSTRAINT fk_material_action_figure FOREIGN KEY(id_action_figure) REFERENCES action_figure(id_action_figure),
};

CREATE TABLE loja.imagem {
    id_imagem INT AUTO_INCREMENT,
    imagem_url VARCHAR(200),

    CONSTRAINT pk_imagem PRIMARY KEY(id_imagem),

    CONSTRAINT fk_imagem_action_figure FOREIGN KEY(id_action_figure) REFERENCES action_figure(id_action_figure),
};