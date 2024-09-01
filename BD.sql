-------------------------
-- Banco de dados loja --
-------------------------

// relações
// cliente 1-tem_endereco=n enderecos
// pedido 1=tem_cliente-n clientes
// pedido n-tem_action_figure-n action_figures

CREATE SCHEMA loja;

CREATE TABLE loja.cliente {
    id INT AUTO_INCREMENT,
    cpf INT(11),
    nome VARCHAR(200),
    email VARCHAR(200) NOT NULL,

    id_pedido INT,

    CONSTRAINT pk_cliente PRIMARY KEY(id_cliente),
    CONSTRAINT uk_cliente_cpf UNIQUE(cpf),
    CONSTRAINT uk_cliente_email UNIQUE(email),

    CONSTRAINT fk_pedido_cliente FOREIGN KEY(id_pedido) REFERENCES pedido(id),
};

CREATE TABLE loja.endereco {
    id INT AUTO_INCREMENT,
    end_num INT,
    estado CHAR(2),
    cidade VARCHAR(200),
    bairro VARCHAR(200),
    rua VARCHAR(200),
    cep INT(8),

    id_cliente INT,

    CONSTRAINT pk_endereco PRIMARY KEY(id),
    CONSTRAINT uk_endereco_cliente UNIQUE(end_num, cep),

    CONSTRAINT fk_cliente_endereco FOREIGN KEY(id_cliente) REFERENCES cliente(id),
};

CREATE TABLE loja.pedido {
    id INT AUTO_INCREMENT,
    valor_total DECIMAL(7,2),
    data_pedido TIMESTAMP,

    CONSTRAINT pk_pedido PRIMARY KEY(id),
};

CREATE TABLE loja.pedido_tem_action_figure {
    id_pedido INT,
    id_action_figure INT,
    pedido_quantidade INT,

    CONSTRAINT pk_pedido_tem_action_figure PRIMARY KEY(id_pedido, id_action_figure),

    CONSTRAINT fk_id_pedido FOREIGN KEY(id_pedido) REFERENCES pedido(id),
    CONSTRAINT fk_id_action_figure FOREIGN KEY(id_action_figure) REFERENCES action_figure(id),
};

CREATE TABLE loja.action_figure {
    id INT AUTO_INCREMENT,
    personagem VARCHAR(200),
    universo VARCHAR(200),
    fabricante VARCHAR(200),
    tamanho INT,
    preco DECIMAL(7,2),
    descricao TEXT,
    categoria VARCHAR(200),
    estoque INT,

    CONSTRAINT pk_action_figure PRIMARY KEY(id),
};


CREATE TABLE loja.material (
    id INT AUTO_INCREMENT,
    tipo_material VARCHAR(200),

    CONSTRAINT pk_material PRIMARY KEY(id)
);

CREATE TABLE loja.action_figure_material (
    id_action_figure INT,
    id_material INT,

    CONSTRAINT pk_action_figure_material PRIMARY KEY(id_action_figure, id_material),

    CONSTRAINT fk_id_action_figure FOREIGN KEY(id_action_figure) REFERENCES loja.action_figure(id),
    CONSTRAINT fk_id_material FOREIGN KEY(id_material) REFERENCES loja.material(id)
);

CREATE TABLE loja.imagem (
    id INT AUTO_INCREMENT,
    imagem_url VARCHAR(200),

    id_action_figure INT,

    CONSTRAINT pk_imagem PRIMARY KEY(id)

    CONSTRAINT fk_id_action_figure FOREIGN KEY(id_action_figure) REFERENCES loja.action_figure(id)
);

CREATE TABLE loja.spring_session (
  id_primario CHAR(36) NOT NULL,
  id_sessao CHAR(36) NOT NULL,
  hora_criacao BIGINT NOT NULL,
  hora_ultimo_acesso BIGINT NOT NULL,
  intervalo_max_inatividade INT NOT NULL,
  tempo_expiracao BIGINT NOT NULL,
  nome_principal VARCHAR(100),
  CONSTRAINT pk_spring_session PRIMARY KEY (id_primario)
);

CREATE UNIQUE INDEX spring_session_ix1 ON loja.spring_session (id_sessao);
CREATE INDEX spring_session_ix2 ON loja.spring_session (tempo_expiracao);
CREATE INDEX spring_session_ix3 ON loja.spring_session (nome_principal);

CREATE TABLE loja.spring_session_atributos (
  id_sessao_primaria CHAR(36) NOT NULL,
  nome_atributos VARCHAR(200) NOT NULL,
  atributos_bytes BYTEA NOT NULL,
  CONSTRAINT pk_spring_session_atributos PRIMARY KEY (id_sessao_primaria, nome_atributos),
  CONSTRAINT fk_spring_session_atributos FOREIGN KEY (id_sessao_primaria) 
  	REFERENCES loja.spring_session(id_primario) ON DELETE CASCADE
);