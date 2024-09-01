-- Relatório de Receitas
SELECT 
    DATE_TRUNC('month', p.data_pedido::timestamp AT TIME ZONE 'UTC') AS periodo,
    SUM(ptaf.pedido_quantidade * af.preco) AS receita_total
FROM 
    loja.pedido p
JOIN 
    loja.pedido_tem_action_figure ptaf ON p.id = ptaf.id_pedido
JOIN 
    loja.action_figure af ON ptaf.id_action_figure = af.id
WHERE 
    p.data_pedido::timestamp AT TIME ZONE 'UTC' BETWEEN '2024-09-01T00:00:00Z' AND '2024-09-01T23:59:59Z'
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
    p.data_pedido::timestamp AT TIME ZONE 'UTC' BETWEEN '2024-09-01T00:00:00Z' AND '2024-09-01T23:59:59Z'
GROUP BY 
    af.personagem
ORDER BY 
    total_vendas DESC;


-- Relatório de Vendas por Período
SELECT 
    p.data_pedido::timestamp AT TIME ZONE 'UTC' AS data_venda,
    af.personagem AS titulo_action_figure,
    ptaf.pedido_quantidade AS quantidade_vendida,
    (ptaf.pedido_quantidade * af.preco) AS receita_gerada
FROM 
    loja.pedido p
JOIN 
    loja.pedido_tem_action_figure ptaf ON p.id = ptaf.id_pedido
JOIN 
    loja.action_figure af ON ptaf.id_action_figure = af.id
WHERE 
    p.data_pedido::timestamp AT TIME ZONE 'UTC' BETWEEN '2024-09-01T00:00:00Z' AND '2024-09-01T23:59:59Z'
ORDER BY 
    p.data_pedido, af.personagem;
