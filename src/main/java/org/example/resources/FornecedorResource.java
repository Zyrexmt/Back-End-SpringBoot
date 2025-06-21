package org.example.resources;


import org.example.dto.FornecedorDTO;
import org.example.entities.Fornecedor;
import org.example.entities.Produto;
import org.example.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorResource {

    @Autowired
    public FornecedorService service;

    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> getAll(){
        List<Fornecedor> fornecedor = service.findAll();
        List<FornecedorDTO> listDTO = fornecedor.stream().map(service::toNewDTO).collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> findById(@PathVariable Long id) {
        Fornecedor obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Fornecedor> insert(@RequestBody Fornecedor fornecedor){
        Fornecedor createdFornecedor = service.insert(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFornecedor);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody Fornecedor fornecedor){
        if (service.update(id, fornecedor)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
