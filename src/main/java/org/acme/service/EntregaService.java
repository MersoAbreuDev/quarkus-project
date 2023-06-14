package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entity.Entrega;
import org.acme.entity.Pedido;
import org.acme.repository.EntregaRepository;
import org.acme.requestDTO.EntregaRequestDTO;
import org.acme.responseDTO.ClienteResponseDTO;
import org.acme.responseDTO.EntregaResponseDTO;
import org.acme.responseDTO.PedidoResponseDTO;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EntregaService {

    @Inject
    EntregaRepository entregaRepository;

    @Inject
    PedidoService pedidoService;

    public EntregaResponseDTO salvar(EntregaRequestDTO entregaRequestDTO) {
        Pedido pedido = this.pedidoService.buscarPedidoPorId(entregaRequestDTO.getIdPedido());

        Entrega entrega = new Entrega();
        entrega.setPedido(pedido);
        entrega.setLogradouro(entregaRequestDTO.getLogradouro());
        entrega.setBairro(entregaRequestDTO.getBairro());
        entrega.setNumero(entregaRequestDTO.getNumero());
        entrega.setCep(entregaRequestDTO.getCep());
        entrega.setCidade(entregaRequestDTO.getCidade());
        entrega.setEstado(entregaRequestDTO.getEstado());
        entrega.persist();

        ClienteResponseDTO clienteResponse = new ClienteResponseDTO();
        clienteResponse.setId(pedido.getCliente().getId());
        clienteResponse.setNome(pedido.getCliente().getNome());

        PedidoResponseDTO pedidoResponse = new PedidoResponseDTO();
        pedidoResponse.setCliente(clienteResponse);
        pedidoResponse.setDataPedido(pedido.getDataPedido());
        pedidoResponse.setId(pedido.getId());

        EntregaResponseDTO entregaResponse = new EntregaResponseDTO();
        entregaResponse.setId(entrega.getId());
        entregaResponse.setBairro(entrega.getBairro());
        entregaResponse.setCep(entrega.getCep());
        entregaResponse.setCidade(entrega.getCidade());
        entregaResponse.setEstado(entrega.getEstado());
        entregaResponse.setLogradouro(entrega.getLogradouro());
        entregaResponse.setNumero(entrega.getNumero());
        entregaResponse.setPedido(pedidoResponse);

        return entregaResponse;
    }


    public List<EntregaResponseDTO> buscarTodas() {
       List<Entrega> entregas =  entregaRepository.findAll().list();

       List<EntregaResponseDTO> entregaResponseDTOS = new ArrayList<>();

       for (Entrega entrega : entregas){
           EntregaResponseDTO entregaResponseDTO = new EntregaResponseDTO();
           PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO();
           entregaResponseDTO.setId(entrega.getId());
           entregaResponseDTO.setLogradouro(entrega.getLogradouro());
           entregaResponseDTO.setBairro(entrega.getBairro());
           entregaResponseDTO.setCidade(entrega.getCidade());
           entregaResponseDTO.setEstado(entrega.getEstado());
           entregaResponseDTO.setCep(entrega.getCep());
           entregaResponseDTO.setNumero(entrega.getNumero());
           entregaResponseDTO.setPedido(pedidoResponseDTO);
           entregaResponseDTOS.add(entregaResponseDTO);
       }
       return entregaResponseDTOS;
    }
}
