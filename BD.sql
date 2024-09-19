-- Criar o esquema
CREATE SCHEMA IF NOT EXISTS loja;

-- Criar a tabela cliente
CREATE TABLE loja.cliente (
    id SERIAL,
    cpf VARCHAR(14) NOT NULL,
    nome VARCHAR(200),
    email VARCHAR(200) NOT NULL,
    
    CONSTRAINT pk_cliente PRIMARY KEY(id),
    CONSTRAINT uq_cliente_cpf UNIQUE (cpf),
    CONSTRAINT uq_cliente_email UNIQUE (email)
);

-- Criar a tabela estado
CREATE TABLE loja.estado (
    id SERIAL,
    sigla_estado VARCHAR(2) NOT NULL,
    
    CONSTRAINT pk_estado PRIMARY KEY(id),
    CONSTRAINT uq_estado_sigla UNIQUE(sigla_estado)
);

-- Criar a tabela cidade (entidade fraca)
CREATE TABLE loja.cidade (
    id SERIAL,
    nome VARCHAR(200) NOT NULL,
    id_estado INT NOT NULL,
    
    CONSTRAINT pk_cidade PRIMARY KEY(id),
    CONSTRAINT fk_estado FOREIGN KEY(id_estado) REFERENCES loja.estado(id) ON DELETE CASCADE
);

-- Criar a tabela bairro (entidade fraca)
CREATE TABLE loja.bairro (
    id SERIAL,
    nome VARCHAR(200) NOT NULL,
    id_cidade INT NOT NULL,

    CONSTRAINT pk_bairro PRIMARY KEY(id),
    CONSTRAINT fk_cidade FOREIGN KEY(id_cidade) REFERENCES loja.cidade(id) ON DELETE CASCADE
);

-- Criar a tabela logradouro (entidade fraca)
CREATE TABLE loja.logradouro (
    id SERIAL,
    nome VARCHAR(200) NOT NULL,
    tipo VARCHAR(200) NOT NULL,
    id_cidade INT NOT NULL,

    CONSTRAINT pk_logradouro PRIMARY KEY(id),
    CONSTRAINT fk_cidade FOREIGN KEY(id_cidade) REFERENCES loja.cidade(id) ON DELETE CASCADE
);

-- Criar a tabela cep_end (entidade fraca)
CREATE TABLE loja.cep_end (
    id SERIAL,
    cep VARCHAR(9) NOT NULL,
    id_logradouro INT NOT NULL,
    id_bairro INT NOT NULL,

    CONSTRAINT pk_cep_end PRIMARY KEY(id),
    CONSTRAINT uq_cep UNIQUE (cep),
    CONSTRAINT fk_logradouro FOREIGN KEY(id_logradouro) REFERENCES loja.logradouro(id) ON DELETE CASCADE,
    CONSTRAINT fk_bairro FOREIGN KEY(id_bairro) REFERENCES loja.bairro(id) ON DELETE CASCADE
);

-- Criar a tabela endereco (entidade fraca)
CREATE TABLE loja.endereco (
    id SERIAL,
    numero INT NOT NULL,
    complemento VARCHAR(200),
    id_cep INT NOT NULL,

    CONSTRAINT pk_endereco PRIMARY KEY(id),
    CONSTRAINT fk_cep FOREIGN KEY(id_cep) REFERENCES loja.cep_end(id) ON DELETE CASCADE
);

-- Criar a tabela cliente_endereco (relacionamento cliente e endereco)
CREATE TABLE loja.cliente_endereco (
    id_cliente INT,
    id_endereco INT,

    CONSTRAINT pk_cliente_endereco PRIMARY KEY(id_cliente, id_endereco),
    CONSTRAINT fk_id_cliente FOREIGN KEY(id_cliente) REFERENCES loja.cliente(id) ON DELETE CASCADE,
    CONSTRAINT fk_id_endereco FOREIGN KEY(id_endereco) REFERENCES loja.endereco(id) ON DELETE CASCADE
);

-- Criar a tabela fabricante
CREATE TABLE loja.fabricante (
    id SERIAL,
    nome_fabricante VARCHAR(255) NOT NULL,
    
    CONSTRAINT pk_fabricante PRIMARY KEY(id),
    CONSTRAINT uq_fabricante_nome UNIQUE(nome_fabricante)
);

-- Criar a tabela action_figure
CREATE TABLE loja.action_figure (
    id SERIAL,
    personagem VARCHAR(200),
    universo VARCHAR(200),
    tamanho INT,
    preco DECIMAL(7,2),
    descricao TEXT,
    categoria VARCHAR(200),
    estoque INT,
    id_fabricante INT NOT NULL,

    CONSTRAINT pk_action_figure PRIMARY KEY(id),
    CONSTRAINT fk_fabricante FOREIGN KEY(id_fabricante) REFERENCES loja.fabricante(id) ON DELETE CASCADE
);

-- Criar a tabela pedido
CREATE TABLE loja.pedido (
    id SERIAL,
    valor_total DECIMAL(7,2),
    data_pedido TIMESTAMP,
    id_cliente INT,

    CONSTRAINT pk_pedido PRIMARY KEY(id),
    CONSTRAINT fk_cliente_pedido FOREIGN KEY(id_cliente) REFERENCES loja.cliente(id) ON DELETE CASCADE
);

-- Criar a tabela pedido_tem_action_figure (relacionamento pedido e action_figure)
CREATE TABLE loja.pedido_tem_action_figure (
    id_pedido INT,
    id_action_figure INT,
    pedido_quantidade INT,
    valor_unitario DECIMAL(7,2),
    subtotal DECIMAL(7,2) GENERATED ALWAYS AS (pedido_quantidade * valor_unitario) STORED,

    CONSTRAINT pk_pedido_tem_action_figure PRIMARY KEY(id_pedido, id_action_figure),
    CONSTRAINT fk_id_pedido FOREIGN KEY(id_pedido) REFERENCES loja.pedido(id) ON DELETE CASCADE,
    CONSTRAINT fk_id_action_figure FOREIGN KEY(id_action_figure) REFERENCES loja.action_figure(id) ON DELETE CASCADE
);

-- Criar a tabela material
CREATE TABLE loja.material (
    id SERIAL,
    tipo_material VARCHAR(200),
    
    CONSTRAINT pk_material PRIMARY KEY(id),
    CONSTRAINT uq_material_tipo UNIQUE(tipo_material)
);

-- Criar a tabela action_figure_material (relacionamento action_figure e material)
CREATE TABLE loja.action_figure_material (
    id_action_figure INT,
    id_material INT,

    CONSTRAINT pk_action_figure_material PRIMARY KEY(id_action_figure, id_material),
    CONSTRAINT fk_id_action_figure FOREIGN KEY(id_action_figure) REFERENCES loja.action_figure(id) ON DELETE CASCADE,
    CONSTRAINT fk_id_material FOREIGN KEY(id_material) REFERENCES loja.material(id) ON DELETE CASCADE
);

-- Criar a tabela imagem (associada a action_figure)
CREATE TABLE loja.imagem (
    id SERIAL,
    imagem_url VARCHAR(200),
    id_action_figure INT,

    CONSTRAINT pk_imagem PRIMARY KEY(id),
    CONSTRAINT fk_id_action_figure FOREIGN KEY(id_action_figure) REFERENCES loja.action_figure(id) ON DELETE CASCADE
);

--------------
-- Carrinho --
--------------

-- Criar a tabela spring_session
CREATE TABLE loja.spring_session (
    id_primario CHAR(36) NOT NULL,
    id_sessao CHAR(36) NOT NULL,
    hora_criacao BIGINT NOT NULL,
    hora_ultimo_acesso BIGINT NOT NULL,
    intervalo_max_inatividade INT NOT NULL,
    tempo_expiracao BIGINT NOT NULL,
    nome_principal VARCHAR(100),

    CONSTRAINT pk_spring_session PRIMARY KEY(id_primario)
);

-- Criar índices para spring_session
CREATE UNIQUE INDEX spring_session_ix1 ON loja.spring_session (id_sessao);
CREATE INDEX spring_session_ix2 ON loja.spring_session (tempo_expiracao);
CREATE INDEX spring_session_ix3 ON loja.spring_session (nome_principal);

-- Criar a tabela spring_session_atributos
CREATE TABLE loja.spring_session_atributos (
    id_sessao_primaria CHAR(36) NOT NULL,
    nome_atributos VARCHAR(200) NOT NULL,
    atributos_bytes BYTEA NOT NULL,

    CONSTRAINT pk_spring_session_atributos PRIMARY KEY(id_sessao_primaria, nome_atributos),
    CONSTRAINT fk_spring_session_atributos FOREIGN KEY(id_sessao_primaria) 
        REFERENCES loja.spring_session(id_primario) ON DELETE CASCADE
);
