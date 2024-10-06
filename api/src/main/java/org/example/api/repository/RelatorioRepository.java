package org.example.api.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RelatorioRepository {

    private final JdbcTemplate jdbcTemplate;

    public RelatorioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getRelatorioReceitas(String dataInicial, String dataFinal) {
        String sql = """
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
                p.data_pedido::timestamp AT TIME ZONE 'UTC' BETWEEN TO_TIMESTAMP(?, 'YYYY-MM-DD') AND TO_TIMESTAMP(?, 'YYYY-MM-DD') + INTERVAL '1 day' - INTERVAL '1 second'
            GROUP BY 
                DATE_TRUNC('month', p.data_pedido::timestamp AT TIME ZONE 'UTC')
            ORDER BY 
                periodo;
        """;
        return jdbcTemplate.queryForList(sql, dataInicial, dataFinal);
    }

    public List<Map<String, Object>> getActionFiguresMaisVendidas(String dataInicial, String dataFinal) {
        String sql = """
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
                p.data_pedido::timestamp AT TIME ZONE 'UTC' BETWEEN TO_TIMESTAMP(?, 'YYYY-MM-DD') AND TO_TIMESTAMP(?, 'YYYY-MM-DD') + INTERVAL '1 day' - INTERVAL '1 second'
            GROUP BY 
                af.personagem
            ORDER BY 
                total_vendas DESC;
        """;
        return jdbcTemplate.queryForList(sql, dataInicial, dataFinal);
    }

    public List<Map<String, Object>> getRelatorioVendasPorPeriodo(String dataInicial, String dataFinal) {
        String sql = """
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
                p.data_pedido::timestamp AT TIME ZONE 'UTC' BETWEEN TO_TIMESTAMP(?, 'YYYY-MM-DD') AND TO_TIMESTAMP(?, 'YYYY-MM-DD') + INTERVAL '1 day' - INTERVAL '1 second'
            ORDER BY 
                p.data_pedido, af.personagem;
        """;
        return jdbcTemplate.queryForList(sql, dataInicial, dataFinal);
    }
}
