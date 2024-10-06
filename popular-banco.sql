-- Inserir novos clientes
INSERT INTO loja.cliente (cpf, nome, email, senha) VALUES
('123.456.789-00', 'Felipe Dias', 'felipe.dias@example.com', 'senha123'),
('987.654.321-00', 'Carla Martins', 'carla.martins@example.com', 'senha123'),
('789.456.123-00', 'Rodrigo Silva', 'rodrigo.silva@example.com', 'senha123');

-- Inserir novas action figures
INSERT INTO loja.action_figure (personagem, universo, tamanho, preco, descricao, categoria, estoque, id_fabricante) VALUES
('Ainz Ooal Gown', 'Overlord', 20, 79.99, 'Action figure do personagem Ainz Ooal Gown de Overlord', 'Anime', 8, 1),
('Emilia', 'Re:Zero', 12, 49.99, 'Action figure da personagem Emilia de Re:Zero', 'Anime', 5, 2),
('Ichigo Kurosaki', 'Bleach', 18, 59.99, 'Action figure do personagem Ichigo Kurosaki de Bleach', 'Anime', 6, 3),
('Special Week', 'Uma Musume', 10, 39.99, 'Action figure da personagem Special Week de Uma Musume', 'Anime', 7, 4),
('Amiya', 'Arknights', 15, 49.99, 'Action figure da personagem Amiya de Arknights', 'Jogos', 10, 5);

-- Inserir materiais para as action figures
INSERT INTO loja.action_figure_material (id_action_figure, id_material) VALUES
((SELECT id FROM loja.action_figure WHERE personagem = 'Ainz Ooal Gown'), 1), 
((SELECT id FROM loja.action_figure WHERE personagem = 'Emilia'), 1), 
((SELECT id FROM loja.action_figure WHERE personagem = 'Ichigo Kurosaki'), 1),
((SELECT id FROM loja.action_figure WHERE personagem = 'Special Week'), 1),
((SELECT id FROM loja.action_figure WHERE personagem = 'Amiya'), 1);

-- Semana 1 (Setembro)
INSERT INTO loja.pedido (valor_total, data_pedido, id_cliente) VALUES
(149.98, '2024-09-02 10:00:00', 1),  -- Segunda
(79.99, '2024-09-03 12:30:00', 2),   -- Terça
(199.99, '2024-09-05 14:30:00', 3),  -- Quinta
(159.98, '2024-09-07 16:00:00', 1);  -- Sábado

-- Semana 2 (Setembro)
INSERT INTO loja.pedido (valor_total, data_pedido, id_cliente) VALUES
(99.99, '2024-09-09 10:30:00', 2),   -- Segunda
(249.97, '2024-09-11 14:00:00', 3),  -- Quarta
(179.97, '2024-09-13 15:30:00', 1),  -- Sexta
(89.99, '2024-09-14 11:00:00', 2);   -- Sábado

-- Semana 3 (Setembro)
INSERT INTO loja.pedido (valor_total, data_pedido, id_cliente) VALUES
(139.99, '2024-09-16 13:00:00', 3),  -- Segunda
(199.99, '2024-09-17 10:30:00', 1),  -- Terça
(109.99, '2024-09-19 12:00:00', 2),  -- Quinta
(179.99, '2024-09-20 14:45:00', 3);  -- Sexta

-- Semana 4 (Setembro)
INSERT INTO loja.pedido (valor_total, data_pedido, id_cliente) VALUES
(229.97, '2024-09-23 10:15:00', 1),  -- Segunda
(159.99, '2024-09-25 14:00:00', 2),  -- Quarta
(99.99, '2024-09-27 16:00:00', 3),   -- Sexta
(119.99, '2024-09-28 11:30:00', 1);  -- Sábado

-- Semana 1 (Outubro)
INSERT INTO loja.pedido (valor_total, data_pedido, id_cliente) VALUES
(179.99, '2024-10-02 13:00:00', 2),   -- Quarta
(199.99, '2024-10-04 16:30:00', 1),   -- Sexta
(239.97, '2024-10-05 12:00:00', 3),   -- Sábado
(199.99, '2024-10-06 14:00:00', 2);   -- Domingo

-- Semana 2 (Outubro) - Inclui pedidos no Dia das Crianças
INSERT INTO loja.pedido (valor_total, data_pedido, id_cliente) VALUES
(249.97, '2024-10-08 15:30:00', 1),   -- Terça
(109.99, '2024-10-09 10:30:00', 2),   -- Quarta
(299.97, '2024-10-12 11:00:00', 3),   -- Sábado (Dia das Crianças)
(179.97, '2024-10-12 12:00:00', 1);   -- Sábado (Dia das Crianças)

-- Semana 3 (Outubro)
INSERT INTO loja.pedido (valor_total, data_pedido, id_cliente) VALUES
(149.99, '2024-10-15 11:30:00', 2),   -- Terça
(199.99, '2024-10-16 14:00:00', 3),   -- Quarta
(229.99, '2024-10-18 15:30:00', 1),   -- Sexta
(259.99, '2024-10-19 12:00:00', 3);   -- Sábado

-- Semana 4 (Outubro)
INSERT INTO loja.pedido (valor_total, data_pedido, id_cliente) VALUES
(159.99, '2024-10-21 16:30:00', 2),   -- Segunda
(249.97, '2024-10-23 10:00:00', 1),   -- Quarta
(199.99, '2024-10-25 14:30:00', 3),   -- Sexta
(159.99, '2024-10-26 12:00:00', 2);   -- Sábado

-- Inserir action figures compradas nos pedidos

-- Semana 1 (Setembro)
INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 1, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Ainz Ooal Gown'
WHERE p.data_pedido = '2024-09-02 10:00:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 2, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Emilia'
WHERE p.data_pedido = '2024-09-03 12:30:00';

-- Semana 2 (Setembro)
INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 1, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Ichigo Kurosaki'
WHERE p.data_pedido = '2024-09-09 10:30:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 3, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Special Week'
WHERE p.data_pedido = '2024-09-11 14:00:00';

-- Semana 3 (Setembro)
INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 1, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Amiya'
WHERE p.data_pedido = '2024-09-16 13:00:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 2, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Ainz Ooal Gown'
WHERE p.data_pedido = '2024-09-17 10:30:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 1, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Emilia'
WHERE p.data_pedido = '2024-09-19 12:00:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 3, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Special Week'
WHERE p.data_pedido = '2024-09-20 14:45:00';

-- Semana 4 (Setembro)
INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 2, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Ichigo Kurosaki'
WHERE p.data_pedido = '2024-09-23 10:15:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 3, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Amiya'
WHERE p.data_pedido = '2024-09-25 14:00:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 1, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Ainz Ooal Gown'
WHERE p.data_pedido = '2024-09-27 16:00:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 2, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Special Week'
WHERE p.data_pedido = '2024-09-28 11:30:00';

-- Semana 1 (Outubro)
INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 3, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Ichigo Kurosaki'
WHERE p.data_pedido = '2024-10-12 11:00:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 2, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Special Week'
WHERE p.data_pedido = '2024-10-12 12:00:00';

-- Semana 2 (Outubro)
INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 3, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Ainz Ooal Gown'
WHERE p.data_pedido = '2024-10-08 15:30:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 2, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Emilia'
WHERE p.data_pedido = '2024-10-09 10:30:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 1, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Special Week'
WHERE p.data_pedido = '2024-10-12 11:00:00';

-- Semana 3 (Outubro)
INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 1, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Amiya'
WHERE p.data_pedido = '2024-10-15 11:30:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 2, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Ichigo Kurosaki'
WHERE p.data_pedido = '2024-10-16 14:00:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 1, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Ainz Ooal Gown'
WHERE p.data_pedido = '2024-10-18 15:30:00';

-- Semana 4 (Outubro)
INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 2, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Amiya'
WHERE p.data_pedido = '2024-10-21 16:30:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 1, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Emilia'
WHERE p.data_pedido = '2024-10-23 10:00:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 3, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Ichigo Kurosaki'
WHERE p.data_pedido = '2024-10-25 14:30:00';

INSERT INTO loja.pedido_tem_action_figure (id_pedido, id_action_figure, pedido_quantidade, valor_unitario)
SELECT p.id, a.id, 1, a.preco
FROM loja.pedido p
JOIN loja.action_figure a ON a.personagem = 'Ainz Ooal Gown'
WHERE p.data_pedido = '2024-10-26 12:00:00';