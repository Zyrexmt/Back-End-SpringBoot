import org.example.entities.FormaPagamento;
import org.example.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity
}
