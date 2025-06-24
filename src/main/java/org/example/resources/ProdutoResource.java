package org.example.resources;

import org.example.entities.Fornecedor;
import org.example.entities.Produto;
import org.example.repositories.FornecedorRepository;
import org.example.services.ProdutoService;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        List<Produto> funcoes = service.getAll();
        return ResponseEntity.ok(funcoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        Produto obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Produto> insert(@RequestParam Long forId, @RequestBody Produto produto){
       Produto newProduct = service.insert(forId, produto);
        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestParam Long forId, @RequestBody Produto produto){

        try {
            Produto existe = service.findById(id);
            produto.setProId(id);
            produto.setFornecedor(null);
            Produto update = service.insert(forId, produto);
            return ResponseEntity.ok(update);
        } catch (ResourceNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
        ResponseEntity.noContent().build();
    }
}
