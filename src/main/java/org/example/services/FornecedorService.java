package org.example.services;

import org.example.dto.FornecedorDTO;
import org.example.entities.Contato;
import org.example.entities.Endereco;
import org.example.entities.Fornecedor;
import org.example.entities.Produto;
import org.example.repositories.EnderecoRepository;
import org.example.repositories.FornecedorRepository;
import org.example.repositories.ProdutoRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.example.services.exeptions.ValueBigForAtributeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;


    public List<Fornecedor> findAll() {
        return repository.findAll();
    }

    public Fornecedor findById(Long id) {
        Optional<Fornecedor> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Fornecedor insert(Fornecedor obj){
        try{
            obj.setForId(null);
            obj = repository.save(obj);
            enderecoRepository.saveAll(obj.getEnderecos());
            return obj;
        }catch (DataIntegrityViolationException e){
            throw new ValueBigForAtributeException(e
                    .getMessage());
        }
    }

    public Fornecedor update(Long id, FornecedorDTO forDTO) {
        try{
            //Fornecedor
            Fornecedor entity = findById(id);
            entity.setForNomeFantasia(forDTO.getForNomeFantasia());
            entity.setForNomeFantasia(forDTO.getForNomeFantasia());
            entity.setForCnpj(forDTO.getForCnpj());
            entity.setForRazaoSocial(forDTO.getForRazaoSocial());

            //Endereço
            Endereco endereco = entity.getEnderecos().get(0);
            endereco.setEndRua(forDTO.getEndRua());
            endereco.setEndNumero(forDTO.getEndNumero());
            endereco.setEndCidade(forDTO.getEndCidade());
            endereco.setEndCep(forDTO.getEndCep());
            endereco.setEndEstado(forDTO.getEndEstado());

            //Contato
            Contato contato = entity.getContatos().get(0);
            contato.setConCelular(forDTO.getConCelular());
            contato.setConTelefoneComercial(forDTO.getConTelefoneComercial());
            contato.setConEmail(forDTO.getConEmail());

            repository.save(entity);
            return entity;
        }catch (DataIntegrityViolationException e){
            throw new ValueBigForAtributeException(e.getMessage());
        }
    }

    public void deleteFornecedor(Long id) {
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw  new ResourceNotFoundException(id);
        }
    }

    public Fornecedor fromDTO(FornecedorDTO forDTO){
        Fornecedor obj = new Fornecedor(null, forDTO.getForNomeFantasia(), forDTO.getForCnpj(), forDTO.getForRazaoSocial());

        Endereco ender = new Endereco(null, obj, forDTO.getEndRua(), forDTO.getEndNumero(),
                forDTO.getEndCidade(), forDTO.getEndCep(), forDTO.getEndEstado());

        Contato contato = new Contato(null, obj,forDTO.getConCelular(), forDTO.getConTelefoneComercial(),
                forDTO.getConEmail());

        obj.getEnderecos().add(ender);
        obj.getContatos().add(contato);

        return obj;
    }

    public FornecedorDTO toNewDTO(Fornecedor obj){
        FornecedorDTO dto = new FornecedorDTO();

        //Fornecedor
        dto.setForId(obj.getForId());
        dto.setForCnpj(obj.getForCnpj());
        dto.setForNomeFantasia(obj.getForNomeFantasia());
        dto.setForRazaoSocial(obj.getForRazaoSocial());

        //Endereço
        Endereco endereco = obj.getEnderecos().get(0);
        dto.setEndRua(endereco.getEndRua());
        dto.setEndNumero(endereco.getEndNumero());
        dto.setEndCidade(dto.getEndCidade());
        dto.setEndCep(endereco.getEndCep());
        dto.setEndEstado(endereco.getEndEstado());

        //Contato
        Contato contato = obj.getContatos().get(0);
        dto.setConCelular(contato.getConCelular());
        dto.setConTelefoneComercial(contato.getConTelefoneComercial());
        dto.setConEmail(contato.getConEmail());

        return dto;
    }
}
