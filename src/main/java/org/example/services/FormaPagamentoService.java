package org.example.services;

import org.example.entities.FormaPagamento;
import org.example.entities.Produto;
import org.example.repositories.FormaPagamentoRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
@Service
public class FormaPagamentoService {
    @Autowired
    public FormaPagamentoRepository repository;

    public List<FormaPagamento> getAll(){
        return repository.findAll();
    }

    public Optional<FormaPagamento> findById(Long id){
        Optional<FormaPagamento> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
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
            FormaPagamento formaPagamentoSistema = optional.get();
            formaPagamentoSistema.setFpgTipo(formaPagamento.getFpgTipo());
            formaPagamentoSistema.setFpgDescricao(formaPagamento.getFpgDescricao());
            formaPagamentoSistema.setFpgNumMaxParcelas(formaPagamento.getFpgNumMaxParcelas());
            formaPagamentoSistema.setFpgPermiteParcelamento(formaPagamento.getFpgPermiteParcelamento());
            formaPagamentoSistema.setFpgTaxaAdicional(formaPagamento.getFpgTaxaAdicional());
            repository.save(formaPagamentoSistema);
            return true;
        }
        return false;
    }
}
