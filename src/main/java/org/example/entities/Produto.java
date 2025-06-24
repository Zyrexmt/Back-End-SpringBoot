package org.example.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_ID")
    private Long proId;

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String proNome;

    @NotBlank(message = "A descrição do produto é obrigatória.")
    private String proDescricao;

    @NotNull(message = "O preço de custo é obrigatório.")
    @Digits(integer = 10, fraction = 2, message = "O preço de custo deve ter no máximo 10 dígitos inteiros e 2 casas decimais.")
    private BigDecimal proPrecoCusto;

    @NotNull(message = "O preço de venda é obrigatório.")
    @Digits(integer = 10, fraction = 2, message = "O preço de venda deve ter no máximo 10 dígitos inteiros e 2 casas decimais.")
    private BigDecimal proPrecoVenda;

    @NotNull(message = "A quantidade em estoque é obrigatória.")
    private Integer proQuantidadeEstoque;

    @NotBlank(message = "A categoria do produto é obrigatória.")
    private String proCategoria;

    @NotBlank(message = "O código de barras do produto é obrigatório.")
    private String proCodigoBarras;

    @NotBlank(message = "A marca do produto é obrigatória.")
    private String proMarca;

    @NotBlank(message = "A unidade de medida do produto é obrigatória.")
    private String proUnidadeMedida;

    @NotBlank(message = "O status de ativo do produto é obrigatório.")
    private String proAtivo;

    @NotNull(message = "A data de cadastro é obrigatória.")
    private LocalDateTime proDataCadastro;

    @NotNull(message = "A data de atualização é obrigatória.")
    private LocalDateTime proDataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "for_id")
    private Fornecedor fornecedor;

    public Produto() {}

    // Getters e Setters

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProNome() {
        return proNome;
    }

    public void setProNome(String proNome) {
        this.proNome = proNome;
    }

    public String getProDescricao() {
        return proDescricao;
    }

    public void setProDescricao(String proDescricao) {
        this.proDescricao = proDescricao;
    }

    public BigDecimal getProPrecoCusto() {
        return proPrecoCusto;
    }

    public void setProPrecoCusto(BigDecimal proPrecoCusto) {
        this.proPrecoCusto = proPrecoCusto;
    }

    public BigDecimal getProPrecoVenda() {
        return proPrecoVenda;
    }

    public void setProPrecoVenda(BigDecimal proPrecoVenda) {
        this.proPrecoVenda = proPrecoVenda;
    }

    public Integer getProQuantidadeEstoque() {
        return proQuantidadeEstoque;
    }

    public void setProQuantidadeEstoque(Integer proQuantidadeEstoque) {
        this.proQuantidadeEstoque = proQuantidadeEstoque;
    }

    public String getProCategoria() {
        return proCategoria;
    }

    public void setProCategoria(String proCategoria) {
        this.proCategoria = proCategoria;
    }

    public String getProCodigoBarras() {
        return proCodigoBarras;
    }

    public void setProCodigoBarras(String proCodigoBarras) {
        this.proCodigoBarras = proCodigoBarras;
    }

    public String getProMarca() {
        return proMarca;
    }

    public void setProMarca(String proMarca) {
        this.proMarca = proMarca;
    }

    public String getProUnidadeMedida() {
        return proUnidadeMedida;
    }

    public void setProUnidadeMedida(String proUnidadeMedida) {
        this.proUnidadeMedida = proUnidadeMedida;
    }

    public String getProAtivo() {
        return proAtivo;
    }

    public void setProAtivo(String proAtivo) {
        this.proAtivo = proAtivo;
    }

    public LocalDateTime getProDataCadastro() {
        return proDataCadastro;
    }

    public void setProDataCadastro(LocalDateTime proDataCadastro) {
        this.proDataCadastro = proDataCadastro;
    }

    public LocalDateTime getProDataAtualizacao() {
        return proDataAtualizacao;
    }

    public void setProDataAtualizacao(LocalDateTime proDataAtualizacao) {
        this.proDataAtualizacao = proDataAtualizacao;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
