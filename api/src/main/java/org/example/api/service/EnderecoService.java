package org.example.api.service;

import org.example.api.dto.EnderecoDTO;
import org.example.api.model.*;
import org.example.api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnderecoService {

    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;
    private final BairroRepository bairroRepository;
    private final LogradouroRepository logradouroRepository;
    private final CepEndRepository cepEndRepository;
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EstadoRepository estadoRepository, CidadeRepository cidadeRepository, 
                           BairroRepository bairroRepository, LogradouroRepository logradouroRepository,
                           CepEndRepository cepEndRepository, EnderecoRepository enderecoRepository) {
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.bairroRepository = bairroRepository;
        this.logradouroRepository = logradouroRepository;
        this.cepEndRepository = cepEndRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public Endereco inserirEnderecoCompleto(EnderecoDTO enderecoDTO) {

        // Verificar ou inserir o estado
        Estado estado = estadoRepository.findBySigla(enderecoDTO.getEstado());
        if (estado == null) {
            estado = new Estado();
            estado.setSiglaEstado(enderecoDTO.getEstado());
            estado = estadoRepository.save(estado);
        }

        // Verificar ou inserir a cidade
        Cidade cidade = cidadeRepository.findByNomeAndIdEstado(enderecoDTO.getCidade(), estado.getId());
        if (cidade == null) {
            cidade = new Cidade();
            cidade.setNome(enderecoDTO.getCidade());
            cidade.setIdEstado(estado.getId());
            cidade = cidadeRepository.save(cidade);
        }

        // Verificar ou inserir o bairro
        Bairro bairro = bairroRepository.findByNomeAndIdCidade(enderecoDTO.getBairro(), cidade.getId());
        if (bairro == null) {
            bairro = new Bairro();
            bairro.setNome(enderecoDTO.getBairro());
            bairro.setIdCidade(cidade.getId());
            bairro = bairroRepository.save(bairro);
        }

        // Verificar ou inserir o logradouro
        Logradouro logradouro = logradouroRepository.findByNomeAndIdCidade(enderecoDTO.getLogradouro(), cidade.getId());
        if (logradouro == null) {
            logradouro = new Logradouro();
            logradouro.setNome(enderecoDTO.getLogradouro());
            logradouro.setTipo(enderecoDTO.getTipoLogradouro());
            logradouro.setIdCidade(cidade.getId());
            logradouro = logradouroRepository.save(logradouro);
        }

        // Verificar ou inserir o cep
        CepEnd cepEnd = cepEndRepository.findByCep(enderecoDTO.getCep());
        if (cepEnd == null) {
            cepEnd = new CepEnd();
            cepEnd.setCep(enderecoDTO.getCep());
            cepEnd.setIdLogradouro(logradouro.getId());
            cepEnd.setIdBairro(bairro.getId());
            cepEnd = cepEndRepository.save(cepEnd);
        }

        // Inserir o endereço
        Endereco endereco = new Endereco();
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setIdCep(cepEnd.getId()); // Associa o novo CEP ao endereço
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco getEnderecoById(Integer id) {
        return enderecoRepository.findById(id);
    }

    // Método para atualizar o endereço completo
    @Transactional
    public Endereco updateEnderecoCompleto(Integer id, EnderecoDTO enderecoDTO) {
        // Verifica se o endereço existe no banco de dados
        Endereco enderecoExistente = enderecoRepository.findById(id);
        if (enderecoExistente == null) {
            return null;  // Retorna null caso o endereço não exista
        }

        // Atualizar ou verificar Estado
        Estado estado = estadoRepository.findBySigla(enderecoDTO.getEstado());
        if (estado == null) {
            estado = new Estado();
            estado.setSiglaEstado(enderecoDTO.getEstado());
            estado = estadoRepository.save(estado);
        }

        // Atualizar ou verificar Cidade
        Cidade cidade = cidadeRepository.findByNomeAndIdEstado(enderecoDTO.getCidade(), estado.getId());
        if (cidade == null) {
            cidade = new Cidade();
            cidade.setNome(enderecoDTO.getCidade());
            cidade.setIdEstado(estado.getId());
            cidade = cidadeRepository.save(cidade);
        }

        // Atualizar ou verificar Bairro
        Bairro bairro = bairroRepository.findByNomeAndIdCidade(enderecoDTO.getBairro(), cidade.getId());
        if (bairro == null) {
            bairro = new Bairro();
            bairro.setNome(enderecoDTO.getBairro());
            bairro.setIdCidade(cidade.getId());
            bairro = bairroRepository.save(bairro);
        }

        // Atualizar ou verificar Logradouro
        Logradouro logradouro = logradouroRepository.findByNomeAndIdCidade(enderecoDTO.getLogradouro(), cidade.getId());
        if (logradouro == null) {
            logradouro = new Logradouro();
            logradouro.setNome(enderecoDTO.getLogradouro());
            logradouro.setTipo(enderecoDTO.getTipoLogradouro());
            logradouro.setIdCidade(cidade.getId());
            logradouro = logradouroRepository.save(logradouro);
        }

        // Atualizar ou verificar CEP
        CepEnd cepEnd = cepEndRepository.findByCep(enderecoDTO.getCep());
        if (cepEnd == null) {
            cepEnd = new CepEnd();
            cepEnd.setCep(enderecoDTO.getCep());
            cepEnd.setIdLogradouro(logradouro.getId());
            cepEnd.setIdBairro(bairro.getId());
            cepEnd = cepEndRepository.save(cepEnd);
        }

        // Atualizar o Endereço
        Endereco enderecoAtualizado = new Endereco();
        enderecoAtualizado.setId(id);  // Mantém o ID do endereço original
        enderecoAtualizado.setNumero(enderecoDTO.getNumero());
        enderecoAtualizado.setComplemento(enderecoDTO.getComplemento());
        enderecoAtualizado.setIdCep(cepEnd.getId());  // Associa o novo CEP ao endereço

        // Atualiza o endereço no banco de dados
        enderecoRepository.update(id, enderecoAtualizado);

        return enderecoAtualizado;
    }


    @Transactional
    public boolean deleteEnderecoCompleto(Integer id) {
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco != null) {
            enderecoRepository.delete(id);
            return true;
        }
        return false;
    }
}
