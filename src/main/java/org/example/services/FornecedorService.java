package org.example.services;

import org.example.entities.Fornecedor;
import org.example.repositories.FornecedorRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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

    public Fornecedor insert(Fornecedor cargaHoraria) {
        return repository.save(cargaHoraria);
    }

    public boolean update(Long id, Fornecedor fornecedor) {
        Optional<Fornecedor> optionalFornecedor = repository.findById(id);
        if (optionalFornecedor.isPresent()) {
            Fornecedor existFornecedor = optionalFornecedor.get();
            existFornecedor.setNome(fornecedor.getNome());
            repository.save(fornecedor);
            return true;
        }
        return false;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
