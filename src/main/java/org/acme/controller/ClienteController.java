package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Cliente;
import org.acme.requestDTO.ClienteRequestDTO;
import org.acme.responseDTO.ClienteResponseDTO;
import org.acme.service.ClienteService;

import java.util.ArrayList;
import java.util.List;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    ClienteService clienteService;

    @GET
    public List<Cliente> findAllClientes(){
        List<Cliente> clientes = new ArrayList<>();

        try {
            clientes = clienteService.findAllClientes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return clientes;
    }

    @POST
    @Transactional
    public Response addClientes(ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO clienteResponseDTO = clienteService.addCliente(clienteRequestDTO);
        return Response.ok(clienteResponseDTO).status(201).build();
    }


    @DELETE()
    @Path("{id}")
    @Transactional
    public Response removeCliente(@PathParam("id") Long id){
         clienteService.removeCliente(id);
        return Response.noContent().status(204).build();
    }

    @PUT()
    @Path("{id}")
    @Transactional
    public Response atualizaCliente(@PathParam("id") Long id,ClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO clienteResponseDTO = clienteService.atualizaCliente(id, clienteRequestDTO);
        return Response.ok(clienteResponseDTO).status(202).build();
    }
    @GET()
    @Path("{id}")
    public Response buscarClientePorId(@PathParam("id") Long id){
        ClienteResponseDTO clienteResponseDTO = clienteService.buscarPorId(id);
        return Response.ok(clienteResponseDTO).status(200).build();
    }


}
