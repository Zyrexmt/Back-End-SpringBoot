package org.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CON_ID")
    private Long conId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CON_CLI_ID")
    private Cliente conCliente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CON_FOR_ID")
    private Fornecedor conFornecedor;

    @NotBlank(message = "Celular é obrigatório")
    @Pattern(regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}-\\d{4}", message = "Celular deve estar no formato (99) 99999-9999")
    @Size(max = 15, message = "Celular deve ter no máximo 15 caracteres")
    @Column(name = "CON_CELULAR", length = 15, nullable = false)
    private String conCelular;

    @NotBlank(message = "Telefone comercial é obrigatório")
    @Pattern(regexp = "\\(?\\d{2}\\)?\\s?\\d{4,5}-\\d{4}", message = "Telefone deve estar no formato (99) 99999-9999")
    @Size(max = 15, message = "Telefone deve ter no máximo 15 caracteres")
    @Column(name = "CON_TELEFONE_COMERCIAL", length = 15, nullable = false)
    private String conTelefoneComercial;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
    @Column(length = 100, name = "CON_EMAIL", nullable = false)
    private String conEmail;

    public Contato() {
    }

    public Contato(Long conId, Fornecedor conFornecedor, String conCelular, String conTelefoneComercial, String conEmail) {
        this.conId = conId;
        this.conFornecedor = conFornecedor;
        this.conCelular = conCelular;
        this.conTelefoneComercial = conTelefoneComercial;
        this.conEmail = conEmail;
    }

    public Contato(Long conId, Cliente conCliente, String conCelular, String conTelefoneComercial, String conEmail) {
        this.conId = conId;
        this.conCliente = conCliente;
        this.conCelular = conCelular;
        this.conTelefoneComercial = conTelefoneComercial;
        this.conEmail = conEmail;
    }

    public Long getConId() {
        return conId;
    }

    public void setConId(Long conId) {
        this.conId = conId;
    }

    public Cliente getConCliente() {
        return conCliente;
    }

    public void setConCliente(Cliente conCliente) {
        this.conCliente = conCliente;
    }

    public Fornecedor getConFornecedor() {
        return conFornecedor;
    }

    public void setConFornecedor(Fornecedor conFornecedor) {
        this.conFornecedor = conFornecedor;
    }

    public String getConCelular() {
        return conCelular;
    }

    public void setConCelular(String conCelular) {
        this.conCelular = conCelular;
    }

    public String getConTelefoneComercial() {
        return conTelefoneComercial;
    }

    public void setConTelefoneComercial(String conTelefoneComercial) {
        this.conTelefoneComercial = conTelefoneComercial;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }
}
