package org.example.resources;

import javafx.concurrent.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.example.entities.Cliente;
import org.example.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {


    @Autowired
    public ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> cliente = service.findALL();
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Cliente> insert(@RequestBody Cliente cliente) {
        Cliente createdCliente = service.insert(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (service.update(id, cliente)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}