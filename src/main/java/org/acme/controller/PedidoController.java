package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Pedido;
import org.acme.requestDTO.PedidoRequestDTO;
import org.acme.responseDTO.EntregaResponseDTO;
import org.acme.responseDTO.PedidoResponseDTO;
import org.acme.service.PedidoService;

import java.util.ArrayList;
import java.util.List;

@Path("/pedidos")
public class PedidoController {

    @Inject
    PedidoService pedidoService;

    @POST
    @Transactional
    public Response salvar(PedidoRequestDTO pedidoRequestDTO){
        PedidoResponseDTO pedidoResponseDTO = pedidoService.salvar(pedidoRequestDTO);
        return Response.ok(pedidoResponseDTO).status(201).build();
    }

    @GET
    public List<PedidoResponseDTO> buscarTodos(){
        List<PedidoResponseDTO> pedidoResponseDTOS = new ArrayList<>();
        pedidoResponseDTOS = pedidoService.buscarTodos();
        return pedidoResponseDTOS;
    }
}
