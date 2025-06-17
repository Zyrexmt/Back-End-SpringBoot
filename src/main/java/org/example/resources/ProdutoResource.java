package org.example.resources;

import org.example.entities.Produto;
import org.example.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public List<Produto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Produto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Produto insert(@RequestBody Produto obj) {
        return service.insert(obj);
    }

    @PutMapping("/{id}")
    public Produto update(@PathVariable Long id, @RequestBody Produto obj) {
        return service.update(id, obj);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
