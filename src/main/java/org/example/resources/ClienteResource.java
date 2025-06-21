package org.example.resources;

import org.example.dto.ClienteDTO;
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
    public ResponseEntity<List<ClienteDTO>> getAll() {
        List<Cliente> cliente = service.findAll();
        List<ClienteDTO> listDTO = cliente.stream().map(service::toNewDTO).collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        Cliente obj = service.findById(id);
        ClienteDTO dto = service.toNewDTO(obj);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insert(@RequestBody ClienteDTO dto) {
        Cliente cliente = service.fromDTO(dto);
        Cliente saved = service.insert(cliente);
        ClienteDTO savedDTO = service.toNewDTO(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        Cliente updated = service.update(id, dto);
        ClienteDTO updatedDTO = service.toNewDTO(updated);
        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}