package com.frankmoley.lil.data.repository;

import com.frankmoley.lil.data.entity.Service;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ServiceRepository implements PanacheRepository<Service> {

    private final EntityManager em;

    public ServiceRepository(EntityManager em) {
        this.em = em;
    }

    public List<Service> getAllServices() {
        List<Service> services = this.em.createQuery("select service from Service service", Service.class).getResultList();
        return services;
    }
}

