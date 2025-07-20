package org.example.resources;


import org.example.dto.VendaDTO;
import org.example.entities.Venda;
import org.example.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaResource {

    @Autowired
    private VendaService service;

    @PostMapping
    public ResponseEntity<VendaDTO> insert(@Valid @RequestBody VendaDTO dto) {
        Venda novaVenda = service.insert(dto);
        VendaDTO responseDTO = service.toDTO(novaVenda);
        URI uri = URI.create("/vendas/" + novaVenda.getVendaId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<VendaDTO>> findAll(){
        List<VendaDTO> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> findById(@PathVariable Long id) {
        VendaDTO dto = service.findDTOById(id);
        return ResponseEntity.ok(dto);
    }
}
