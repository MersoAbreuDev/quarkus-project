package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Entrega;

@ApplicationScoped
public class EntregaRepository implements PanacheRepository<Entrega> {


}
