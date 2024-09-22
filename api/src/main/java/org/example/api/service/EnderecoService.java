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

        Estado estado = estadoRepository.findBySigla(enderecoDTO.getEstado());
        if (estado == null) {
            estado = new Estado();
            estado.setSiglaEstado(enderecoDTO.getEstado());
            estado = estadoRepository.save(estado);
        }

        Cidade cidade = cidadeRepository.findByNomeAndIdEstado(enderecoDTO.getCidade(), estado.getId());
        if (cidade == null) {
            cidade = new Cidade();
            cidade.setNome(enderecoDTO.getCidade());
            cidade.setIdEstado(estado.getId());
            cidade = cidadeRepository.save(cidade);
        }

        Bairro bairro = bairroRepository.findByNomeAndIdCidade(enderecoDTO.getBairro(), cidade.getId());
        if (bairro == null) {
            bairro = new Bairro();
            bairro.setNome(enderecoDTO.getBairro());
            bairro.setIdCidade(cidade.getId());
            bairro = bairroRepository.save(bairro);
        }

        Logradouro logradouro = logradouroRepository.findByNomeAndIdCidade(enderecoDTO.getLogradouro(), cidade.getId());
        if (logradouro == null) {
            logradouro = new Logradouro();
            logradouro.setNome(enderecoDTO.getLogradouro());
            logradouro.setTipo(enderecoDTO.getTipoLogradouro());
            logradouro.setIdCidade(cidade.getId());
            logradouro = logradouroRepository.save(logradouro);
        }

        CepEnd cepEnd = cepEndRepository.findByCep(enderecoDTO.getCep());
        if (cepEnd == null) {
            cepEnd = new CepEnd();
            cepEnd.setCep(enderecoDTO.getCep());
            cepEnd.setIdLogradouro(logradouro.getId());
            cepEnd.setIdBairro(bairro.getId());
            cepEnd = cepEndRepository.save(cepEnd);
        }

        Endereco endereco = new Endereco();
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setIdCep(cepEnd.getId());
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> getAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Endereco getEnderecoById(Integer id) {
        return enderecoRepository.findById(id);
    }

    @Transactional
    public Endereco updateEnderecoCompleto(Integer id, EnderecoDTO enderecoDTO) {

        Endereco enderecoExistente = enderecoRepository.findById(id);
        if (enderecoExistente == null) {
            return null;
        }

        Estado estado = estadoRepository.findBySigla(enderecoDTO.getEstado());
        if (estado == null) {
            estado = new Estado();
            estado.setSiglaEstado(enderecoDTO.getEstado());
            estado = estadoRepository.save(estado);
        }

        Cidade cidade = cidadeRepository.findByNomeAndIdEstado(enderecoDTO.getCidade(), estado.getId());
        if (cidade == null) {
            cidade = new Cidade();
            cidade.setNome(enderecoDTO.getCidade());
            cidade.setIdEstado(estado.getId());
            cidade = cidadeRepository.save(cidade);
        }

        Bairro bairro = bairroRepository.findByNomeAndIdCidade(enderecoDTO.getBairro(), cidade.getId());
        if (bairro == null) {
            bairro = new Bairro();
            bairro.setNome(enderecoDTO.getBairro());
            bairro.setIdCidade(cidade.getId());
            bairro = bairroRepository.save(bairro);
        }

        Logradouro logradouro = logradouroRepository.findByNomeAndIdCidade(enderecoDTO.getLogradouro(), cidade.getId());
        if (logradouro == null) {
            logradouro = new Logradouro();
            logradouro.setNome(enderecoDTO.getLogradouro());
            logradouro.setTipo(enderecoDTO.getTipoLogradouro());
            logradouro.setIdCidade(cidade.getId());
            logradouro = logradouroRepository.save(logradouro);
        }

        CepEnd cepEnd = cepEndRepository.findByCep(enderecoDTO.getCep());
        if (cepEnd == null) {
            cepEnd = new CepEnd();
            cepEnd.setCep(enderecoDTO.getCep());
            cepEnd.setIdLogradouro(logradouro.getId());
            cepEnd.setIdBairro(bairro.getId());
            cepEnd = cepEndRepository.save(cepEnd);
        }

        Endereco enderecoAtualizado = new Endereco();
        enderecoAtualizado.setId(id);  // Mantém o ID do endereço original
        enderecoAtualizado.setNumero(enderecoDTO.getNumero());
        enderecoAtualizado.setComplemento(enderecoDTO.getComplemento());
        enderecoAtualizado.setIdCep(cepEnd.getId());

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
