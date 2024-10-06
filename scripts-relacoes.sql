-- Relatório de Receitas
SELECT 
    DATE_TRUNC('month', p.data_pedido::timestamp AT TIME ZONE 'UTC') AS periodo,
    SUM(ptaf.pedido_quantidade * ptaf.valor_unitario) AS receita_total
FROM 
    loja.pedido p
JOIN 
    loja.pedido_tem_action_figure ptaf ON p.id = ptaf.id_pedido
JOIN 
    loja.action_figure af ON ptaf.id_action_figure = af.id
WHERE 
    p.data_pedido::timestamp AT TIME ZONE 'UTC' BETWEEN '2024-09-01T00:00:00Z' AND '2024-09-30T23:59:59Z'
GROUP BY 
    DATE_TRUNC('month', p.data_pedido::timestamp AT TIME ZONE 'UTC')
ORDER BY 
    periodo;



-- Relatório de Action Figures Mais Vendidas
SELECT 
    af.personagem AS titulo_action_figure,
    SUM(ptaf.pedido_quantidade) AS total_vendas
FROM 
    loja.pedido p
JOIN 
    loja.pedido_tem_action_figure ptaf ON p.id = ptaf.id_pedido
JOIN 
    loja.action_figure af ON ptaf.id_action_figure = af.id
WHERE 
    p.data_pedido::timestamp AT TIME ZONE 'UTC' BETWEEN '2024-09-01T00:00:00Z' AND '2024-09-30T23:59:59Z' -- Ajuste o intervalo conforme necessário
GROUP BY 
    af.personagem
ORDER BY 
    total_vendas DESC;



-- Relatório de Vendas por Período
SELECT 
    p.data_pedido::timestamp AT TIME ZONE 'UTC' AS data_venda,
    af.personagem AS titulo_action_figure,
    ptaf.pedido_quantidade AS quantidade_vendida,
    (ptaf.pedido_quantidade * ptaf.valor_unitario) AS receita_gerada
FROM 
    loja.pedido p
JOIN 
    loja.pedido_tem_action_figure ptaf ON p.id = ptaf.id_pedido
JOIN 
    loja.action_figure af ON ptaf.id_action_figure = af.id
WHERE 
    p.data_pedido::timestamp AT TIME ZONE 'UTC' BETWEEN '2024-09-01T00:00:00Z' AND '2024-09-30T23:59:59Z' -- Ajuste o intervalo conforme necessário
ORDER BY 
    p.data_pedido, af.personagem;


-- script para visualizar a relação entre cliente e endereço
SELECT 
    c.id AS cliente_id,
    c.nome AS cliente_nome,
    c.cpf AS cliente_cpf,
    c.email AS cliente_email,
    e.numero AS endereco_numero,
    e.complemento AS endereco_complemento,
    cep.cep AS endereco_cep,
    l.nome AS logradouro_nome,
    l.tipo AS logradouro_tipo,
    b.nome AS bairro_nome,
    ci.nome AS cidade_nome,
    est.sigla_estado AS estado_sigla
FROM 
    loja.cliente c
JOIN 
    loja.cliente_endereco ce ON c.id = ce.id_cliente
JOIN 
    loja.endereco e ON ce.id_endereco = e.id
JOIN 
    loja.cep_end cep ON e.id_cep = cep.id
JOIN 
    loja.logradouro l ON cep.id_logradouro = l.id
JOIN 
    loja.bairro b ON cep.id_bairro = b.id
JOIN 
    loja.cidade ci ON l.id_cidade = ci.id
JOIN 
    loja.estado est ON ci.id_estado = est.id;

-- script para visualizar a relação entre cliente e pedido (valor total)
SELECT 
    c.id AS cliente_id,
    c.nome AS cliente_nome,
    c.cpf AS cliente_cpf,
    p.id AS pedido_id,
    p.valor_total AS valor_pedido,
    p.data_pedido AS data_pedido
FROM 
    loja.cliente c
JOIN 
    loja.pedido p ON c.id = p.id_cliente;


-- script para visualizar a relação entre cliente, pedido e action figure (quantidade e valor unitário)
SELECT 
    c.id AS cliente_id,
    c.nome AS cliente_nome,
    c.cpf AS cliente_cpf,
    p.id AS pedido_id,
    p.data_pedido AS data_pedido,
    af.personagem AS action_figure_personagem,
    af.universo AS action_figure_universo,
    ptaf.pedido_quantidade AS quantidade,
    ptaf.valor_unitario AS valor_unitario
FROM 
    loja.cliente c
JOIN 
    loja.pedido p ON c.id = p.id_cliente
JOIN 
    loja.pedido_tem_action_figure ptaf ON p.id = ptaf.id_pedido
JOIN 
    loja.action_figure af ON ptaf.id_action_figure = af.id;


-- script para visualizar a relação entre action figure, imagens e materiais
SELECT 
    af.id AS action_figure_id,
    af.personagem AS action_figure_personagem,
    af.universo AS action_figure_universo,
    af.tamanho AS action_figure_tamanho,
    af.preco AS action_figure_preco,
    af.categoria AS action_figure_categoria,
    af.estoque AS action_figure_estoque,
    ARRAY_AGG(DISTINCT m.tipo_material) AS materiais,
    ARRAY_AGG(DISTINCT i.imagem_url) AS imagens
FROM 
    loja.action_figure af
LEFT JOIN 
    loja.action_figure_material afm ON af.id = afm.id_action_figure
LEFT JOIN 
    loja.material m ON afm.id_material = m.id
LEFT JOIN 
    loja.imagem i ON af.id = i.id_action_figure
GROUP BY 
    af.id, af.personagem, af.universo, af.tamanho, af.preco, af.categoria, af.estoque
ORDER BY 
    af.id;

-- Script atualizado para visualizar a relação completa entre estado, cidade, bairro, logradouro, tipo_logradouro, numero, complemento e cep
SELECT 
    est.sigla_estado AS estado_sigla,
    ci.nome AS cidade_nome,
    b.nome AS bairro_nome,
    l.tipo AS logradouro_tipo,
    l.nome AS logradouro_nome,
    cep.cep AS cep,
    e.numero AS endereco_numero,
    e.complemento AS endereco_complemento
FROM 
    loja.estado est
JOIN 
    loja.cidade ci ON est.id = ci.id_estado
JOIN 
    loja.bairro b ON ci.id = b.id_cidade
JOIN 
    loja.logradouro l ON ci.id = l.id_cidade
JOIN 
    loja.cep_end cep ON l.id = cep.id_logradouro AND b.id = cep.id_bairro
JOIN 
    loja.endereco e ON cep.id = e.id_cep
ORDER BY 
    est.sigla_estado, ci.nome, b.nome, l.nome, cep.cep, e.numero;

