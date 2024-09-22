package org.example.api.repository;

import org.example.api.model.ActionFigure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ActionFigureRepository {

    private final JdbcTemplate jdbcTemplate;

    public ActionFigureRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ActionFigure> findAll() {
        String sql = "SELECT id, personagem, universo, tamanho, preco, descricao, categoria, estoque, id_fabricante FROM loja.action_figure";
        return jdbcTemplate.query(sql, new ActionFigureRowMapper());
    }

    public ActionFigure findById(Integer id) {
        String sql = "SELECT id, personagem, universo, tamanho, preco, descricao, categoria, estoque, id_fabricante FROM loja.action_figure WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ActionFigureRowMapper(), id);
    }

    public void save(ActionFigure actionFigure) {
        String sql = "INSERT INTO loja.action_figure (personagem, universo, tamanho, preco, descricao, categoria, estoque, id_fabricante) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, actionFigure.getPersonagem(), actionFigure.getUniverso(), actionFigure.getTamanho(), actionFigure.getPreco(), actionFigure.getDescricao(), actionFigure.getCategoria(), actionFigure.getEstoque(), actionFigure.getIdFabricante());
    }

    public void update(Integer id, ActionFigure actionFigure) {
        String sql = "UPDATE loja.action_figure SET personagem = ?, universo = ?, tamanho = ?, preco = ?, descricao = ?, categoria = ?, estoque = ?, id_fabricante = ? WHERE id = ?";
        jdbcTemplate.update(sql, actionFigure.getPersonagem(), actionFigure.getUniverso(), actionFigure.getTamanho(), actionFigure.getPreco(), actionFigure.getDescricao(), actionFigure.getCategoria(), actionFigure.getEstoque(), actionFigure.getIdFabricante(), id);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM loja.action_figure WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Método para filtrar action figures por diferentes critérios
    public List<ActionFigure> findWithFilters(Integer idFabricante, String categoria, String universo, String personagem) {
        StringBuilder sql = new StringBuilder("SELECT id, personagem, universo, tamanho, preco, descricao, categoria, estoque, id_fabricante FROM loja.action_figure WHERE 1=1");
        
        List<Object> params = new ArrayList<>();

        if (idFabricante != null) {
            sql.append(" AND id_fabricante = ?");
            params.add(idFabricante);
        }
        if (categoria != null && !categoria.isEmpty()) {
            sql.append(" AND categoria LIKE ?");
            params.add("%" + categoria + "%");
        }
        if (universo != null && !universo.isEmpty()) {
            sql.append(" AND universo LIKE ?");
            params.add("%" + universo + "%");
        }
        if (personagem != null && !personagem.isEmpty()) {
            sql.append(" AND personagem LIKE ?");
            params.add("%" + personagem + "%");
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), new ActionFigureRowMapper());
    }

    private static class ActionFigureRowMapper implements RowMapper<ActionFigure> {
        @Override
        public ActionFigure mapRow(ResultSet rs, int rowNum) throws SQLException {
            ActionFigure actionFigure = new ActionFigure();
            actionFigure.setId(rs.getInt("id"));
            actionFigure.setPersonagem(rs.getString("personagem"));
            actionFigure.setUniverso(rs.getString("universo"));
            actionFigure.setTamanho(rs.getInt("tamanho"));
            actionFigure.setPreco(rs.getDouble("preco"));
            actionFigure.setDescricao(rs.getString("descricao"));
            actionFigure.setCategoria(rs.getString("categoria"));
            actionFigure.setEstoque(rs.getInt("estoque"));
            actionFigure.setIdFabricante(rs.getInt("id_fabricante"));
            return actionFigure;
        }
    }
}
