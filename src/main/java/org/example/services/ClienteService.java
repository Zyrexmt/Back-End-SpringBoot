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
            //Atualiza os dados do cliente
            entity.setCliNome(objDTO.getCliNome());
            entity.setCliCpf(objDTO.getCliCpf());

            // Atualiza o endereco do cliente
            Endereco endereco = entity.getEnderecos().get(0);
            // Assumindo que há apenas um endereço por cliente
            endereco.setEndRua(objDTO.getEndRua());
            endereco.setEndNumero(objDTO.getEndNumero());
            endereco.setEndCidade(objDTO.getEndCidade());
            endereco.setEndCep(objDTO.getEndCep());
            endereco.setEndEstado(objDTO.getEndEstado());

            // Atualiza o contato
            Contato contato = entity.getContatos().get(0);
            // Assumindo que há apenas um contato por cliente
            contato.setConCelular(objDTO.getConCelular());
            contato.setConTelefoneComercial(objDTO.getConTelefoneComercial());
            contato.setConEmail(objDTO.getConEmail());

            // Salva as alterações
            repository.save(entity);

            return entity;
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
        Cliente cliente = new Cliente();

        // Mapeamento básico
        cliente.setCliNome(dto.getCliNome());
        cliente.setCliCpf(dto.getCliCpf());

        // Mapeamento de endereço
        Endereco endereco = new Endereco();
        endereco.setEndRua(dto.getEndRua());
        endereco.setEndNumero(dto.getEndNumero());
        endereco.setEndCidade(dto.getEndCidade());
        endereco.setEndCep(dto.getEndCep());
        endereco.setEndEstado(dto.getEndEstado());
        cliente.setEnderecos(Collections.singletonList(endereco));

        // Mapeamento de contato
        Contato contato = new Contato();
        contato.setConCelular(dto.getConCelular());
        contato.setConTelefoneComercial(dto.getConTelefoneComercial());
        contato.setConEmail(dto.getConEmail());
        cliente.setContatos(Collections.singletonList(contato));

        return cliente;
    }

    public ClienteDTO toNewDTO(Cliente obj) {
        ClienteDTO dto = new ClienteDTO();

// Mapeie os atributos comuns entre Cliente e ClienteNewDTO
        dto.setCliId(obj.getCliId());
        dto.setCliNome(obj.getCliNome());
        dto.setCliCpf(obj.getCliCpf());

// Atributos específicos de Endereco
        Endereco endereco = obj.getEnderecos().get(0);
        dto.setEndRua(endereco.getEndRua());
        dto.setEndNumero(endereco.getEndNumero());
        dto.setEndCidade(endereco.getEndCidade());
        dto.setEndCep(endereco.getEndCep());
        dto.setEndEstado(endereco.getEndEstado());

// Atributos específicos de Contato
        Contato contato = obj.getContatos().get(0);
        dto.setConCelular(contato.getConCelular());
        dto.setConTelefoneComercial(contato.getConTelefoneComercial());
        dto.setConEmail(contato.getConEmail());

        return dto;
    }


}
