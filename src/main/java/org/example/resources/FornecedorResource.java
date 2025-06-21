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
    public ResponseEntity<FornecedorDTO> findById(@PathVariable Long id) {
        Fornecedor obj = service.findById(id);
        FornecedorDTO dto = service.toNewDTO(obj);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> insert(@RequestBody FornecedorDTO dto){
        Fornecedor fornecedor = service.fromDTO(dto);
        Fornecedor saved = service.insert(fornecedor);
        FornecedorDTO savedDTO = service.toNewDTO(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDTO);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.deleteFornecedor(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody FornecedorDTO dto){
        Fornecedor updated = service.update(id, dto);
        FornecedorDTO updatedDTO = service.toNewDTO(updated);
        return ResponseEntity.ok(updatedDTO);
    }
}
