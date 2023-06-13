package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Cliente;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {
}
