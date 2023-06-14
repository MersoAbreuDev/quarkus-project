package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.requestDTO.EntregaRequestDTO;
import org.acme.responseDTO.ClienteResponseDTO;
import org.acme.responseDTO.EntregaResponseDTO;
import org.acme.service.EntregaService;

import java.util.ArrayList;
import java.util.List;

@Path("/entregas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EntregaController {


    @Inject
    EntregaService entregaService;

    @POST
    @Transactional
    public Response salvar(EntregaRequestDTO entregaRequestDTO){
        EntregaResponseDTO entregaResponseDTO = entregaService.salvar(entregaRequestDTO);
        return Response.ok(entregaResponseDTO).status(201).build();

    }

    @GET
    public List<EntregaResponseDTO> buscarTodos(){
        List<EntregaResponseDTO> clienteResponseDTOS = new ArrayList<>();
        clienteResponseDTOS = entregaService.buscarTodas();
        return clienteResponseDTOS;
    }
}
