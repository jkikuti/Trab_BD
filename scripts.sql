-- Inserir materiais únicos
INSERT INTO loja.material (tipo_material) VALUES ('Plástico');
INSERT INTO loja.material (tipo_material) VALUES ('Metal');

-- Inserir fabricantes
INSERT INTO loja.fabricante (nome_fabricante) VALUES ('Bandai'), ('Banpresto'), ('Hasbro'), ('Funko'), ('McFarlane Toys'), ('Square Enix'), ('Good Smile Company'), ('NECA');

-- Inserir action figures com id_fabricante correto
INSERT INTO loja.action_figure (personagem, universo, tamanho, preco, descricao, categoria, estoque, id_fabricante) VALUES
('Goku', 'Dragon Ball', 15, 59.99, 'Action figure do personagem Goku da série Dragon Ball', 'Anime', 10, 1),
('Naruto Uzumaki', 'Naruto', 12, 49.99, 'Action figure do personagem Naruto Uzumaki da série Naruto', 'Anime', 5, 2),
('Darth Vader', 'Star Wars', 18, 69.99, 'Action figure do personagem Darth Vader da série Star Wars', 'Filmes', 7, 3),
('Homem-Aranha', 'Marvel', 10, 39.99, 'Action figure do personagem Homem-Aranha da série Marvel', 'Filmes', 3, 3),
('Mickey Mouse', 'Disney', 8, 29.99, 'Action figure do personagem Mickey Mouse da série Disney', 'Desenhos', 2, 4),
('Batman', 'DC', 14, 59.99, 'Action figure do personagem Batman da série DC', 'Filmes', 4, 5),
('Sora', 'Kingdom Hearts', 15, 59.99, 'Action figure do personagem Sora da série Kingdom Hearts', 'Jogos', 10, 6),
('Shiro', 'No Game No Life', 12, 49.99, 'Action figure do personagem Shiro da série No Game No Life', 'Anime', 5, 7),
('Link', 'The Legend of Zelda', 12, 49.99, 'Action figure do personagem Link da série The Legend of Zelda', 'Jogos', 5, 7),
('Kratos', 'God of War', 18, 69.99, 'Action figure do personagem Kratos da série God of War', 'Jogos', 7, 8);

-- Associar action figures aos materiais
INSERT INTO loja.action_figure_material (id_action_figure, id_material) VALUES
(1, 1),  -- Goku - Plástico
(1, 2),  -- Goku - Metal
(2, 1),  -- Naruto Uzumaki - Plástico
(3, 1),  -- Darth Vader - Plástico
(4, 1),  -- Homem-Aranha - Plástico
(5, 1),  -- Mickey Mouse - Plástico
(6, 1),  -- Batman - Plástico
(7, 1),  -- Sora - Plástico
(7, 2),  -- Sora - Metal
(8, 1),  -- Shiro - Plástico
(9, 1),  -- Link - Plástico
(10, 1); -- Kratos - Plástico

-- Inserir imagens das action figures
INSERT INTO loja.imagem (imagem_url, id_action_figure) VALUES
('https://example.com/goku1.jpg', 1),
('https://example.com/goku2.jpg', 1),
('https://example.com/naruto1.jpg', 2),
('https://example.com/naruto2.jpg', 2),
('https://example.com/darthvader1.jpg', 3),
('https://example.com/darthvader2.jpg', 3),
('https://example.com/homemaranha1.jpg', 4),
('https://example.com/homemaranha2.jpg', 4),
('https://example.com/mickey1.jpg', 5),
('https://example.com/mickey2.jpg', 5),
('https://example.com/batman1.jpg', 6),
('https://example.com/batman2.jpg', 6),
('https://example.com/sora1.jpg', 7),
('https://example.com/sora2.jpg', 7),
('https://example.com/shiro1.jpg', 8),
('https://example.com/shiro2.jpg', 8),
('https://example.com/link1.jpg', 9),
('https://example.com/link2.jpg', 9),
('https://example.com/kratos1.jpg', 10),
('https://example.com/kratos2.jpg', 10);

-- Inserir clientes
INSERT INTO loja.cliente (cpf, nome, email) VALUES
('123.123.123-12', 'João Kikuti', 'joao.kikuti@example.com'),
('456.456.456-45', 'Gregorio Favero', 'gregorio.favero@example.com'),
('789.789.789-78', 'Lucca Gomes', 'lucca.gomes@example.com');

-- Inserir estados
INSERT INTO loja.estado (sigla_estado) VALUES ('SP'), ('RJ'), ('MG');

-- Inserir cidades
INSERT INTO loja.cidade (nome, id_estado) VALUES
('São Paulo', 1),
('Rio de Janeiro', 2),
('Belo Horizonte', 3);

-- Inserir bairros
INSERT INTO loja.bairro (nome, id_cidade) VALUES
('Centro', 1),
('Copacabana', 2),
('Savassi', 3);

-- Inserir logradouros
INSERT INTO loja.logradouro (nome, tipo, id_cidade) VALUES
('Rua das Flores', 'Rua', 1),
('Avenida Central', 'Avenida', 2),
('Praça da Liberdade', 'Praça', 3);

-- Inserir CEPs
INSERT INTO loja.cep_end (cep, id_logradouro, id_bairro) VALUES
('10000000', 1, 1),
('20000000', 2, 2),
('30000000', 3, 3);

-- Inserir endereços
INSERT INTO loja.endereco (numero, complemento, id_cep) VALUES
(123, NULL, 1),
(456, NULL, 2),
(789, NULL, 3);

-- Associar endereços aos clientes
INSERT INTO loja.cliente_endereco (id_cliente, id_endereco) VALUES
(1, 1),
(2, 2),
(3, 3);

-- Criar pedidos
INSERT INTO loja.pedido (valor_total, data_pedido, id_cliente) VALUES
(109.98, '2024-09-01T18:25:43.511Z', 1),
(49.99, '2024-09-02T10:15:32.123Z', 2),
(129.98, '2024-09-03T08:30:12.789Z', 3);

-- Inserir action figures nos pedidos
INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario) VALUES
(1, 1, 1, 59.99),  -- Pedido 1: Goku
(1, 2, 1, 49.99),  -- Pedido 1: Naruto
(2, 8, 1, 49.99),  -- Pedido 2: Shiro
(3, 3, 1, 69.99),  -- Pedido 3: Darth Vader
(3, 6, 1, 59.99);  -- Pedido 3: Batman

-- Atualizar estoque das action figures após os pedidos
UPDATE loja.action_figure SET estoque = estoque - 1 WHERE id IN (1, 2, 8, 3, 6);

-- Atualizar preço de uma action figure
UPDATE loja.action_figure SET preco = 64.99 WHERE id = 6; -- Batman novo preço
