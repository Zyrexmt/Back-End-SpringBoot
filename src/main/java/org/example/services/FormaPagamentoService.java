package org.example.services;

import org.example.entities.Contato;
import org.example.entities.FormaPagamento;
import org.example.repositories.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {
    @Autowired
    public FormaPagamentoRepository repository;

    public List<FormaPagamento> findAll(){
        return repository.findAll();
    }

    public Optional<FormaPagamento> findById(Long id){
        return repository.findById(id);
    }
    public FormaPagamento insert(FormaPagamento formaPagamento){
        return repository.save(formaPagamento);
    }

    public void delete(Long id ){
        repository.deleteById(id);
    }

    public boolean update(Long id, FormaPagamento formaPagamento){
        Optional<FormaPagamento>optional=repository.findById(id);
        if (optional.isPresent()){
            FormaPagamento formaPagamento1 = optional.get();
            formaPagamento1.setDescricao(formaPagamento.getDescricao());
            formaPagamento1.setTipo(formaPagamento.getTipo());
            repository.save(formaPagamento1);
            return true;
        }
        return false;
    }
}
