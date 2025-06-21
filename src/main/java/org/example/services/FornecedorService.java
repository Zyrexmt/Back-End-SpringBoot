package org.example.services;

import org.example.dto.FornecedorDTO
import org.example.entities.Fornecedor;
import org.example.entities.Produto;
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
            return obj;
        }catch (DataIntegrityViolationException e){
            throw new ValueBigForAtributeException(e
                    .getMessage());
        }
    }

    public Fornecedor update(Long id, FornecedorDTO forDTO) {
        try{
            Fornecedor entity = findById(id);
            entity.setForNomeFantasia(forDTO.getForNomeFantasia());
            entity.setForNomeFantasia(forDTO.getForNomeFantasia());
            entity.setForCnpj(forDTO.getForCnpj());
            entity.setForRazaoSocial(forDTO.getForRazaoSocial());

            repository.save(entity);
            return entity;
        }catch (DataIntegrityViolationException e){
            throw new ValueBigForAtributeException(e.getMessage());
        }
    }

    public void delete(Long id) {
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw  new ResourceNotFoundException(id);
        }
    }

    public Fornecedor fromDTO(FornecedorDTO forDTO){
        Fornecedor obj = new Fornecedor(null, forDTO.getForNomeFantasia(), forDTO.getForCnpj(), forDTO.getForRazaoSocial());
    }
}
