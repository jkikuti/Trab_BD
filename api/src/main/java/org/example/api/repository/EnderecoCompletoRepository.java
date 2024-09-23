package org.example.api.repository;

import org.example.api.model.Endereco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnderecoCompletoRepository {

    private final JdbcTemplate jdbcTemplate;

    public EnderecoCompletoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Endereco findEnderecoCompleto(String estadoSigla, String cidadeNome, String bairroNome, String logradouroNome, String cep, Integer numero, String complemento) {
        String sql = "SELECT e.id, e.numero, e.complemento, e.id_cep " +
                     "FROM loja.endereco e " +
                     "JOIN loja.cep_end ce ON e.id_cep = ce.id " +
                     "JOIN loja.logradouro l ON ce.id_logradouro = l.id " +
                     "JOIN loja.bairro b ON l.id_cidade = b.id_cidade " +
                     "JOIN loja.cidade c ON b.id_cidade = c.id " +
                     "JOIN loja.estado es ON c.id_estado = es.id " +
                     "WHERE es.sigla_estado = ? AND c.nome = ? AND b.nome = ? AND l.nome = ? " +
                     "AND ce.cep = ? AND e.numero = ? " +
                     (complemento != null ? "AND e.complemento = ?" : "AND e.complemento IS NULL");

        List<Endereco> enderecos = jdbcTemplate.query(
            sql, 
            complemento != null ? new Object[]{estadoSigla, cidadeNome, bairroNome, logradouroNome, cep, numero, complemento}
                                : new Object[]{estadoSigla, cidadeNome, bairroNome, logradouroNome, cep, numero},
            (rs, rowNum) -> {
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setIdCep(rs.getInt("id_cep"));
                return endereco;
            });

        return enderecos.isEmpty() ? null : enderecos.get(0);
    }
}
