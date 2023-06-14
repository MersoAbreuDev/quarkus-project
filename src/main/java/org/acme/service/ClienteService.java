package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entity.Cliente;
import org.acme.repository.ClienteRepository;
import org.acme.requestDTO.ClienteRequestDTO;
import org.acme.responseDTO.ClienteResponseDTO;

import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    private ClienteRepository clienteRepository;


    public List<Cliente> findAllClientes(){
        return clienteRepository.findAll().list();
    }

    public ClienteResponseDTO addCliente(ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequestDTO.getNome());
        clienteRepository.persist(cliente);
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setId(cliente.getId());
        clienteResponseDTO.setNome(cliente.getNome());
        return clienteResponseDTO;
    }

    public void removeCliente(Long id){
      Cliente cliente = this.clienteRepository.findById(id);
      this.clienteRepository.delete(cliente);
    }

    public ClienteResponseDTO atualizaCliente(Long id, ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = clienteRepository.findById(id);

        if(cliente != null) {
            cliente.setNome(clienteRequestDTO.getNome());
            cliente.persist();
        }
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setId(cliente.getId());
        clienteResponseDTO.setNome(cliente.getNome());
        return clienteResponseDTO;
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id);
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setId(cliente.getId());
        clienteResponseDTO.setNome(cliente.getNome());
        return clienteResponseDTO;
    }


}
