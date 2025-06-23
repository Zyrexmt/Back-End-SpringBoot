package org.example.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class FormaPagamento  implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long fpgId;

        @NotBlank(message = "Descrição é obrigatório!")
        @Size(max = 100, message = "Descrição deve ter no máximo 100 caracteres")
        private String fpgDescricao;

        @NotBlank(message = "Definir a forma de pagamento é obrigatório")
        @Pattern(regexp = "^(Credito|Debito|Pix|Boleto)$",
                message = "Definir a forma de pagamento é obrigatório (Crédito, Débito, Pix ou Boleto)")
        private String fpgTipo;

        @NotNull(message = "Permite Parcelamento é obrigatório")
        private Boolean fpgPermiteParcelamento;

        @NotNull(message = "Número máximo de parcelas é obrigatório")
        @Min(value = 1, message = "Número máximo de parcelas deve ser pelo menos 1")
        @Max(value = 12, message = "Número máximo de parcelas não pode ser maior que 12")
        private Integer fpgNumMaxParcelas;

        @NotNull(message = "Taxa adicional é obrigatório")
        @DecimalMin(value = "0.00", message = "Taxa adicional não pode ser negativa")
        private BigDecimal fpgTaxaAdicional;

        // Constructors, getters, and setters
        public FormaPagamento() {
        }

        public FormaPagamento(Long fpgId, String fpgDescricao, String fpgTipo,
                              Boolean fpgPermiteParcelamento, Integer fpgNumMaxParcelas,
                              BigDecimal fpgTaxaAdicional) {
            this.fpgId = fpgId;
            this.fpgDescricao = fpgDescricao;
            this.fpgTipo = fpgTipo;
            this.fpgPermiteParcelamento = fpgPermiteParcelamento;
            this.fpgNumMaxParcelas = fpgNumMaxParcelas;
            this.fpgTaxaAdicional = fpgTaxaAdicional;
        }

        public Long getFpgId() {
            return fpgId;
        }

        public void setFpgId(Long fpgId) {
            this.fpgId = fpgId;
        }

        public String getFpgDescricao() {
            return fpgDescricao;
        }

        public void setFpgDescricao(String fpgDescricao) {
            this.fpgDescricao = fpgDescricao;
        }

        public String getFpgTipo() {
            return fpgTipo;
        }

        public void setFpgTipo(String fpgTipo) {
            this.fpgTipo = fpgTipo;
        }

        public Boolean getFpgPermiteParcelamento() {
            return fpgPermiteParcelamento;
        }

        public void setFpgPermiteParcelamento(Boolean fpgPermiteParcelamento) {
            this.fpgPermiteParcelamento = fpgPermiteParcelamento;
        }

        public Integer getFpgNumMaxParcelas() {
            return fpgNumMaxParcelas;
        }

        public void setFpgNumMaxParcelas(Integer fpgNumMaxParcelas) {
            this.fpgNumMaxParcelas = fpgNumMaxParcelas;
        }

        public BigDecimal getFpgTaxaAdicional() {
            return fpgTaxaAdicional;
        }

        public void setFpgTaxaAdicional(BigDecimal fpgTaxaAdicional) {
            this.fpgTaxaAdicional = fpgTaxaAdicional;
        }
    }