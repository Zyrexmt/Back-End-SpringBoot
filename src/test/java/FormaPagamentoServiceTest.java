import org.example.entities.FormaPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class FormaPagamentoServiceTest {

    private FormaPagamento formaPagamento;
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        formaPagamento = new FormaPagamento(1L, "Cartão de Crédito", "Credito", true, 12, new BigDecimal("1.50"));
    }

    @Test
    void testConstrutorEGetters() {
        assertEquals(1L, formaPagamento.getFpgId());
        assertEquals("Cartão de Crédito", formaPagamento.getFpgDescricao());
        assertEquals("Credito", formaPagamento.getFpgTipo());
        assertTrue(formaPagamento.getFpgPermiteParcelamento());
        assertEquals(12, formaPagamento.getFpgNumMaxParcelas());
        assertEquals(new BigDecimal("1.50"), formaPagamento.getFpgTaxaAdicional());
    }

    @Test
    void testSettersValidos() {
        formaPagamento.setFpgDescricao("Permite o cliente realizar o pagamento parcelado, ou a vista.");
        formaPagamento.setFpgTipo("Credito");
        formaPagamento.setFpgPermiteParcelamento(true);
        formaPagamento.setFpgNumMaxParcelas(6);
        formaPagamento.setFpgTaxaAdicional(new BigDecimal("0.75"));

        assertEquals("Permite o cliente realizar o pagamento parcelado, ou a vista.", formaPagamento.getFpgDescricao());
        assertEquals("Credito", formaPagamento.getFpgTipo());
        assertTrue(formaPagamento.getFpgPermiteParcelamento());
        assertEquals(6, formaPagamento.getFpgNumMaxParcelas());
        assertEquals(new BigDecimal("0.75"), formaPagamento.getFpgTaxaAdicional());
    }

    @Test
    void testDescricaoNula() {
        formaPagamento.setFpgDescricao(null);
        Set<ConstraintViolation<FormaPagamento>> violations = validator.validate(formaPagamento);
        assertFalse(violations.isEmpty());
        assertEquals("Descrição é obrigatório!", violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("fpgDescricao"))
                .findFirst().get().getMessage());
    }

    @Test
    void testFpgTipoNulo() {
        formaPagamento.setFpgTipo(null);
        Set<ConstraintViolation<FormaPagamento>> violations = validator.validate(formaPagamento);
        assertFalse(violations.isEmpty());
        assertEquals("Definir a forma de pagamento é obrigatório", violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("fpgTipo"))
                .findFirst().get().getMessage());
    }

    @Test
    void testFpgTipoInvalido() {
        formaPagamento.setFpgTipo("Cheque");
        Set<ConstraintViolation<FormaPagamento>> violations = validator.validate(formaPagamento);
        assertFalse(violations.isEmpty());
        assertEquals("Definir a forma de pagamento é obrigatório (Crédito, Débito, Pix ou Boleto)", violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("fpgTipo"))
                .findFirst().get().getMessage());
    }

    @Test
    void testFpgPermiteParcelamentoNulo() {
        formaPagamento.setFpgPermiteParcelamento(null);
        Set<ConstraintViolation<FormaPagamento>> violations = validator.validate(formaPagamento);
        assertFalse(violations.isEmpty());
        assertEquals("Permite Parcelamento é obrigatório", violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("fpgPermiteParcelamento"))
                .findFirst().get().getMessage());
    }

    @Test
    void testFpgNumeroMaximoParcelasNulo() {
        formaPagamento.setFpgNumMaxParcelas(null);
        Set<ConstraintViolation<FormaPagamento>> violations = validator.validate(formaPagamento);
        assertFalse(violations.isEmpty());
        assertEquals("Número máximo de parcelas é obrigatório", violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("fpgNumMaxParcelas"))
                .findFirst().get().getMessage());
    }

    @Test
    void testFpgNumeroMaximoParcelasInvalido() {
        formaPagamento.setFpgNumMaxParcelas(0);
        Set<ConstraintViolation<FormaPagamento>> violations = validator.validate(formaPagamento);
        assertFalse(violations.isEmpty());
        assertEquals("Número máximo de parcelas deve ser pelo menos 1", violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("fpgNumMaxParcelas"))
                .findFirst().get().getMessage());
    }

    @Test
    void testFpgTaxaAdicionalNula() {
        formaPagamento.setFpgTaxaAdicional(null);
        Set<ConstraintViolation<FormaPagamento>> violations = validator.validate(formaPagamento);
        assertFalse(violations.isEmpty());
        assertEquals("Taxa adicional é obrigatório", violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("fpgTaxaAdicional"))
                .findFirst().get().getMessage());
    }

    @Test
    void testFpgTaxaAdicionalNegativa() {
        formaPagamento.setFpgTaxaAdicional(new BigDecimal("-1.00"));
        Set<ConstraintViolation<FormaPagamento>> violations = validator.validate(formaPagamento);
        assertFalse(violations.isEmpty());
        assertEquals("Taxa adicional não pode ser negativa", violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("fpgTaxaAdicional"))
                .findFirst().get().getMessage());
    }
}