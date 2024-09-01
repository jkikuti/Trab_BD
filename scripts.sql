-----------------------------------
-- Inserindo valores nas tabelas --
-----------------------------------

-- Inserir materiais únicos
INSERT INTO loja.material (tipo_material) VALUES ('Plástico');
INSERT INTO loja.material (tipo_material) VALUES ('Metal');

-- Inserir action figures
INSERT INTO loja.action_figure (id, personagem, universo, fabricante, tamanho, preco, descricao, categoria, estoque) VALUES
(1, 'Goku', 'Dragon Ball', 'Bandai', 15, 59.99, 'Action figure do personagem Goku da série Dragon Ball', 'Anime', 10),
(2, 'Naruto Uzumaki', 'Naruto', 'Banpresto', 12, 49.99, 'Action figure do personagem Naruto Uzumaki da série Naruto', 'Anime', 5),
(3, 'Darth Vader', 'Star Wars', 'Hasbro', 18, 69.99, 'Action figure do personagem Darth Vader da série Star Wars', 'Filmes', 7),
(4, 'Homem-Aranha', 'Marvel', 'Hasbro', 10, 39.99, 'Action figure do personagem Homem-Aranha da série Marvel', 'Filmes', 3),
(5, 'Mickey Mouse', 'Disney', 'Funko', 8, 29.99, 'Action figure do personagem Mickey Mouse da série Disney', 'Desenhos', 2),
(6, 'Batman', 'DC', 'McFarlane Toys', 14, 59.99, 'Action figure do personagem Batman da série DC', 'Filmes', 4),
(7, 'Sora', 'Kingdom Hearts', 'Square Enix', 15, 59.99, 'Action figure do personagem Sora da série Kingdom Hearts', 'Jogos', 10),
(8, 'Shiro', 'No Game No Life', 'Good Smile Company', 12, 49.99, 'Action figure do personagem Shiro da série No Game No Life', 'Anime', 5),
(9, 'Link', 'The Legend of Zelda', 'Good Smile Company', 12, 49.99, 'Action figure do personagem Link da série The Legend of Zelda', 'Jogos', 5),
(10, 'Kratos', 'God of War', 'NECA', 18, 69.99, 'Action figure do personagem Kratos da série God of War', 'Jogos', 7);

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

-- Inserindo clientes
INSERT INTO loja.cliente (id, cpf, nome, email, id_pedido) VALUES
(1, '123.123.123-12', 'João Kikuti', 'joao.kikuti@example.com', NULL),
(2, '456.456.456-45', 'Gregorio Favero', 'gregorio.favero@example.com', NULL),
(3, '789.789.789-78', 'Lucca Gomes', 'lucca.gomes@example.com', NULL);

-- Inserindo endereços dos clientes
INSERT INTO loja.endereco (id, end_num, estado, cidade, bairro, rua, cep, id_cliente) VALUES
(1, 123, 'SP', 'São Paulo', 'Centro', 'Rua das Flores', 10000000, 1),
(2, 456, 'RJ', 'Rio de Janeiro', 'Copacabana', 'Avenida Central', 20000000, 2),
(3, 789, 'MG', 'Belo Horizonte', 'Savassi', 'Praça da Liberdade', 30000000, 3);

---------------------------------------------------------
-- Inserindo valores nas tabelas ao ralizar uma compra --
---------------------------------------------------------

-- Criando pedidos
INSERT INTO loja.pedido (id, valor_total, data_pedido) VALUES
(1, 109.98, '2024-09-01T18:25:43.511Z'),
(2, 49.99, '2024-09-02T10:15:32.123Z'),
(3, 129.98, '2024-09-03T08:30:12.789Z');

-- Associando pedidos aos clientes
UPDATE loja.cliente SET id_pedido = 1 WHERE id = 1;
UPDATE loja.cliente SET id_pedido = 2 WHERE id = 2;
UPDATE loja.cliente SET id_pedido = 3 WHERE id = 3;

-- Inserindo action figures nos pedidos
INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade) VALUES
(1, 1, 1),  -- Pedido 1: Goku
(1, 2, 1),  -- Pedido 1: Naruto
(2, 8, 1),  -- Pedido 2: Shiro
(3, 3, 1),  -- Pedido 3: Darth Vader
(3, 6, 1);  -- Pedido 3: Batman

-- Atualizando estoque das action figures após os pedidos
UPDATE loja.action_figure SET estoque = estoque - 1 WHERE id = 1; -- Goku
UPDATE loja.action_figure SET estoque = estoque - 1 WHERE id = 2; -- Naruto
UPDATE loja.action_figure SET estoque = estoque - 1 WHERE id = 8; -- Shiro
UPDATE loja.action_figure SET estoque = estoque - 1 WHERE id = 3; -- Darth Vader
UPDATE loja.action_figure SET estoque = estoque - 1 WHERE id = 6; -- Batman

-- Após a finalização da compra, setar id_pedido dos clientes para NULL
UPDATE loja.cliente SET id_pedido = NULL WHERE id_pedido IS NOT NULL;

--------------------------------------------
-- Atualizando valor de uma action figure --
--------------------------------------------

-- Simulando uma atualização de preço em uma action figure
UPDATE loja.action_figure SET preco = 64.99 WHERE id = 6; -- Batman novo preço