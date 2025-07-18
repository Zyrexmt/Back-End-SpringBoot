package org.example.entities;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLI_ID")
    private Long cliId;

    @OneToMany(mappedBy = "endCliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "conCliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos = new ArrayList<>();

    @NotBlank(message = "Nome é obrigatório!")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres!")
    @Column(name = "CLI_NOME", nullable = false)
    private String cliNome;

    @NotBlank(message = "Cpf é obrigatório!")
    @CPF(message = "CPF inválido!")
    @Column(name = "CLI_CPF", length = 11 , nullable = false, unique = true)
    private String cliCpf;

    public Cliente() {}

    public Cliente(Long cliId, String cliCpf, String cliNome) {
        this.cliId = cliId;
        this.cliCpf = cliCpf;
        this.cliNome = cliNome;
    }

    // Getters e setters omitidos para brevidade...

    public Long getCliId() {
        return cliId;
    }

    public void setCliId(Long cliId) {
        this.cliId = cliId;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public String getCliNome() {
        return cliNome;
    }

    public void setCliNome(String cliNome) {
        this.cliNome = cliNome;
    }

    public String getCliCpf() {
        return cliCpf;
    }

    public void setCliCpf(String cliCpf) {
        this.cliCpf = cliCpf;
    }

    // Métodos auxiliares para manter os dois lados sincronizados

    public void addEndereco(Endereco endereco) {
        enderecos.add(endereco);
        endereco.setEndCliente(this);
    }

    public void removeEndereco(Endereco endereco) {
        enderecos.remove(endereco);
        endereco.setEndCliente(null);
    }

    public void addContato(Contato contato) {
        contatos.add(contato);
        contato.setConCliente(this);
    }

    public void removeContato(Contato contato) {
        contatos.remove(contato);
        contato.setConCliente(null);
    }
}
