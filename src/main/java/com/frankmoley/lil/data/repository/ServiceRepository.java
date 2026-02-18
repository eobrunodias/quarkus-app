package com.frankmoley.lil.data.repository;

import com.frankmoley.lil.data.entity.Service;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ServiceRepository implements PanacheRepository<Service> {
}

