package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entity.Cliente;
import org.acme.entity.Pedido;
import org.acme.repository.ClienteRepository;
import org.acme.repository.PedidoRepository;
import org.acme.requestDTO.PedidoRequestDTO;
import org.acme.responseDTO.ClienteResponseDTO;
import org.acme.responseDTO.PedidoResponseDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ClienteRepository clienteRepository;
    @Inject
    ClienteService clienteService;

    public Pedido buscarPedidoPorId(Long id) {
        Pedido pedido = this.pedidoRepository.findById(id);
        if(pedido == null) {
            throw new NullPointerException("Pedido não encontrada");
        }
        return pedido;
    }

    public PedidoResponseDTO salvar(PedidoRequestDTO pedidoRequestDTO) {

        Cliente cliente = this.clienteRepository.findById(pedidoRequestDTO.getIdCliente());

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDate.now());
        pedido.persist(pedido);

        ClienteResponseDTO clienteResponse = new ClienteResponseDTO();
        clienteResponse.setId(pedido.getCliente().getId());
        clienteResponse.setNome(pedido.getCliente().getNome());

        PedidoResponseDTO pedidoResponse = new PedidoResponseDTO();
        pedidoResponse.setCliente(clienteResponse);
        pedidoResponse.setDataPedido(pedido.getDataPedido());
        pedidoResponse.setId(pedido.getId());

        return pedidoResponse;
    }

    public List<PedidoResponseDTO> buscarTodos() {
        List<Pedido> listaPedido = this.pedidoRepository.findAll().list();
        List<PedidoResponseDTO> listaResponse = new ArrayList<>();

        for (Pedido listaPedidos : listaPedido) {
            ClienteResponseDTO clienteResponse = new ClienteResponseDTO();
            PedidoResponseDTO pedidoResponse = new PedidoResponseDTO();

            clienteResponse.setId(listaPedidos.getCliente().getId());
            clienteResponse.setNome(listaPedidos.getCliente().getNome());

            pedidoResponse.setCliente(clienteResponse);
            pedidoResponse.setDataPedido(listaPedidos.getDataPedido());
            pedidoResponse.setId(listaPedidos.getId());
            listaResponse.add(pedidoResponse);
        }

        return listaResponse;
    }
}
