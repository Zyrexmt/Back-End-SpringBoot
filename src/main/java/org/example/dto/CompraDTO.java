package org.example.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CompraDTO {

    @NotNull
    private Long proId;

    @NotNull
    @Min(1)
    private Integer compraQuantidade;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    private BigDecimal compraPrecoVenda;

    public CompraDTO() {
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public Integer getCompraQuantidade() {
        return compraQuantidade;
    }

    public void setCompraQuatidade(Integer compraQuatidade) {
        this.compraQuantidade = compraQuantidade;
    }

    public BigDecimal getCompraPrecoVenda() {
        return compraPrecoVenda;
    }

    public void setCompraPrecoVenda(BigDecimal compraPrecoVenda) {
        this.compraPrecoVenda = compraPrecoVenda;
    }
}
