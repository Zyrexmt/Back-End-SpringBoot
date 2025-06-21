import org.example.entities.FormaPagamento;
import org.example.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/formapagamentos")
public class FormaPagamentoResource {

    @Autowired
    private FormaPagamento formaPagamento;

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> findALL(){
        List<FormaPagamento> funcoes = formaPagamento.find();
        return ResponseEntity.ok(funcoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> findById(@PathVariable Long id){
        FormaPagamento obj = formaPagamento.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<FormaPagamento> insert(@RequestBody FormaPagamento formaPagamento){
        FormaPagamento createdFormaPagamento = formaPagamento.insert(formaPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFormaPagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento){
        if(formaPagamento.update(id, formaPagamento)) {
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        formaPagamento.delete(id);
        return ResponseEntity.noContent().build();
    }

}
