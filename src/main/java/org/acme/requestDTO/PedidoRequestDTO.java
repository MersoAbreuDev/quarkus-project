package org.acme.requestDTO;

public class PedidoRequestDTO {
    private Long id;

    private Long idPedido;

    public Long getIdCliente() {
        return idPedido;
    }

    public void setIdCliente(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
