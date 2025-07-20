package org.example.services;

import org.example.dto.CompraDTO;
import org.example.dto.VendaDTO;
import org.example.entities.*;
import org.example.repositories.ClienteRepository;
import org.example.repositories.FormaPagamentoRepository;
import org.example.repositories.ProdutoRepository;
import org.example.repositories.VendaRepository;
import org.example.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    public List<VendaDTO> getAll(){
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VendaDTO findDTOById(Long id){
        Venda venda = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        return toDTO(venda);
    }

    public Venda insert(VendaDTO dto) {
        Venda venda = new Venda();

        Cliente cliente = clienteRepository.findById(dto.getCliId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getCliId()));

        FormaPagamento formaPagamento = formaPagamentoRepository.findById(dto.getFpgId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getFpgId()));

        venda.setCliente(cliente);
        venda.setFormaPagamento(formaPagamento);
        venda.setVendaCodigo(dto.getVendaCodigo());
        venda.setVendaData(dto.getVendaData());

        List<Compra> compras = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (CompraDTO compraDTO : dto.getCompras()) {
            Produto produto = produtoRepository.findById(compraDTO.getProId())
                    .orElseThrow(() -> new ResourceNotFoundException(compraDTO.getProId()));

            produtoService.reduzirEstoque(produto.getProId(), compraDTO.getCompraQuantidade());

            Compra compra = new Compra();
            compra.setProduto(produto);
            compra.setCompraQuantidade(compraDTO.getCompraQuantidade());
            compra.setCompraPrecoVenda(compraDTO.getCompraPrecoVenda());
            compra.setVenda(venda);

            BigDecimal subtotal = compraDTO.getCompraPrecoVenda()
                    .multiply(BigDecimal.valueOf(compraDTO.getCompraQuantidade()));
            total = total.add(subtotal);

            compras.add(compra);
        }

        venda.setCompras(compras);
        venda.setVendaValorTotal(total);

        return repository.save(venda);

    }

    public VendaDTO toDTO(Venda venda){
        VendaDTO dto = new VendaDTO();

        dto.setVendaCodigo(venda.getVendaCodigo());
        dto.setVendaData(venda.getVendaData());
        dto.setCliId(venda.getCliente().getCliId());
        dto.setFpgId(venda.getFormaPagamento().getFpgId());
        dto.setVendaValorTotal(venda.getVendaValorTotal());

        List<CompraDTO> comprasDTO = venda.getCompras()
                .stream()
                .map(compra -> {
                    CompraDTO compraDTO = new CompraDTO();
                    compraDTO.setProId(compra.getProduto().getProId());
                    compraDTO.setCompraQuatidade(compra.getCompraQuantidade());
                    compraDTO.setCompraPrecoVenda(compra.getCompraPrecoVenda());
                    return compraDTO;
                })
                .collect(Collectors.toList());

        dto.setCompras(comprasDTO);

        return dto;
    }

}
