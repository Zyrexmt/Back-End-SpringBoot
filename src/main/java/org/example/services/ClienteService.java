package org.example.services;

import org.example.dto.ClienteDTO;
import org.example.entities.Cliente;
import org.example.entities.Contato;
import org.example.entities.Endereco;
import org.example.entities.FormaPagamento;
import org.example.repositories.ClienteRepository;
import org.example.repositories.EnderecoRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.example.services.exeptions.ValueBigForAtributeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new
                ResourceNotFoundException(id));
    }

    public Cliente insert(Cliente obj){
        try {
            obj.setCliId(null);
            obj = repository.save(obj);
            enderecoRepository.saveAll(obj.getEnderecos());
            return obj;
        } catch (DataIntegrityViolationException e){
            throw new ValueBigForAtributeException(e
                    .getMessage());
        }
    }

    public Cliente update(Long id, ClienteDTO objDTO){
        try {
            Cliente entity = findById(id);

            // Atualiza os dados do cliente
            entity.setCliNome(objDTO.getCliNome());
            entity.setCliCpf(objDTO.getCliCpf());

            // Atualiza o endereco do cliente
            Endereco endereco;
            if (!entity.getEnderecos().isEmpty()) {
                endereco = entity.getEnderecos().get(0);
            } else {
                endereco = new Endereco();
                entity.addEndereco(endereco);  // usando método auxiliar, implemente se ainda não tiver
            }
            endereco.setEndRua(objDTO.getEndRua());
            endereco.setEndNumero(objDTO.getEndNumero());
            endereco.setEndCidade(objDTO.getEndCidade());
            endereco.setEndCep(objDTO.getEndCep());
            endereco.setEndEstado(objDTO.getEndEstado());

            // Atualiza o contato do cliente
            Contato contato;
            if (!entity.getContatos().isEmpty()) {
                contato = entity.getContatos().get(0);
            } else {
                contato = new Contato();
                entity.addContato(contato); // método auxiliar também, igual no Cliente entity
            }
            contato.setConCelular(objDTO.getConCelular());
            contato.setConTelefoneComercial(objDTO.getConTelefoneComercial());
            contato.setConEmail(objDTO.getConEmail());

            // Salva as alterações
            return repository.save(entity);

        } catch (DataIntegrityViolationException e){
            throw new ValueBigForAtributeException(e.getMessage());
        }
    }

    public void deleteCliente(Long id){
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public Cliente fromDTO(ClienteDTO dto) {
        Cliente cliente = new Cliente(null, dto.getCliCpf(), dto.getCliNome());

        // Criar endereço vinculando ao cliente
        Endereco endereco = new Endereco(null, cliente, dto.getEndRua(), dto.getEndNumero(),
                dto.getEndCidade(), dto.getEndCep(), dto.getEndEstado());

        // Criar contato vinculando ao cliente
        Contato contato = new Contato(null, cliente, dto.getConCelular(), dto.getConTelefoneComercial(),
                dto.getConEmail());

        // Usar os métodos auxiliares para manter o relacionamento sincronizado
        cliente.addEndereco(endereco);
        cliente.addContato(contato);

        return cliente;
    }

    public ClienteDTO toNewDTO(Cliente obj) {
        ClienteDTO dto = new ClienteDTO();

        dto.setCliId(obj.getCliId());
        dto.setCliNome(obj.getCliNome());
        dto.setCliCpf(obj.getCliCpf());

        if (obj.getEnderecos() != null && !obj.getEnderecos().isEmpty()) {
            Endereco endereco = obj.getEnderecos().get(0);
            dto.setEndRua(endereco.getEndRua());
            dto.setEndNumero(endereco.getEndNumero());
            dto.setEndCidade(endereco.getEndCidade());
            dto.setEndCep(endereco.getEndCep());
            dto.setEndEstado(endereco.getEndEstado());
        }

        if (obj.getContatos() != null && !obj.getContatos().isEmpty()) {
            Contato contato = obj.getContatos().get(0);
            dto.setConCelular(contato.getConCelular());
            dto.setConTelefoneComercial(contato.getConTelefoneComercial());
            dto.setConEmail(contato.getConEmail());
        }

        return dto;
    }



}
