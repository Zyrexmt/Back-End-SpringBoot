package org.example.resources;

import org.example.entities.Fornecedor;
import org.example.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value = "/fornecedor")
public class FornecedorResource {
    @Autowired
    public FornecedorService service;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> getAll(){
        List<Fornecedor> fornecedor = service.findAll();
        return ResponseEntity.ok(fornecedor);
    }
    @GetMapping("/id")
    public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
        Optional<Fornecedor> fornecedor = Optional.ofNullable(service.findById(id));
        return fornecedor.map(ResponseEntity::ok).orElseGet(()
                -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Fornecedor> insert(@RequestBody Fornecedor fornecedor){
        Fornecedor created = service.insert(fornecedor);
        return ResponseEntity.status(HttpStatus.MULTI_STATUS.CREATED).body(created);
    }
    @DeleteMapping("/id")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/id")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Fornecedor fornecedor){
        if (service.update(id, fornecedor)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
