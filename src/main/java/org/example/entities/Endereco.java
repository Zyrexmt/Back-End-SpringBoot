package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "END_ID")
    private Long endId;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "END_CLI_ID")
    private Cliente endCliente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "END_FOR_ID")
    private Fornecedor endFornecedor;

    @NotBlank(message = "Obrigatório")
    @Size(max = 150, message = "Limite de 150 caracteres")
    @Column(name = "END_RUA", nullable = false, length = 150)
    private String endRua;

    @NotBlank(message = "Obrigatório")
    @Size(max = 10, message = "Limite de 10 caracteres")
    @Column(name = "END_NUMERO", nullable = false, length = 10)
    private String endNumero;

    @NotBlank(message = "Obrigatório")
    @Size(max = 100, message = "Limite de 100 caracteres")
    @Column(name = "END_CIDADE", nullable = false, length = 100)
    private String endCidade;

    @NotBlank(message = "Obrigatório")
    @Size(max = 8, message = "Limite de 8 caracteres")
    @Column(name = "END_CEP", length = 8, nullable = false)
    private String endCep;

    @NotBlank(message = "Obrigatório")
    @Size(max = 100, message = "Limite de 100 caracteres")
    @Column(name = "END_ESTADO", length = 100, nullable = false)
    private String endEstado;

    public Endereco() {
    }

    public Endereco(Long endId, Fornecedor endFornecedor, String endRua, String endNumero, String endCidade, String endCep, String endEstado) {
        this.endId = endId;
        this.endFornecedor = endFornecedor;
        this.endRua = endRua;
        this.endNumero = endNumero;
        this.endCidade = endCidade;
        this.endCep = endCep;
        this.endEstado = endEstado;
    }

    public Endereco(Long endId, Cliente endCliente, String endRua, String endNumero, String endCidade, String endCep, String endEstado) {
        this.endId = endId;
        this.endCliente = endCliente;
        this.endRua = endRua;
        this.endNumero = endNumero;
        this.endCidade = endCidade;
        this.endCep = endCep;
        this.endEstado = endEstado;
    }

    public Long getEndId() {
        return endId;
    }

    public void setEndId(Long endId) {
        this.endId = endId;
    }

    public Cliente getEndCliente() {
        return endCliente;
    }

    public void setEndCliente(Cliente endCliente) {
        this.endCliente = endCliente;
    }

    public Fornecedor getEndFornecedor() {
        return endFornecedor;
    }

    public void setEndFornecedor(Fornecedor endFornecedor) {
        this.endFornecedor = endFornecedor;
    }

    public String getEndRua() {
        return endRua;
    }

    public void setEndRua(String endRua) {
        this.endRua = endRua;
    }

    public String getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(String endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndCidade() {
        return endCidade;
    }

    public void setEndCidade(String endCidade) {
        this.endCidade = endCidade;
    }

    public String getEndCep() {
        return endCep;
    }

    public void setEndCep(String endCep) {
        this.endCep = endCep;
    }

    public String getEndEstado() {
        return endEstado;
    }

    public void setEndEstado(String endEstado) {
        this.endEstado = endEstado;
    }
}