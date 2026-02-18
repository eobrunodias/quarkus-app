package com.frankmoley.lil.data.repository;

import com.frankmoley.lil.data.entity.Vendor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class VendorRepository implements PanacheRepository<Vendor> {

    public Vendor findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Vendor findByName(String name) {
        return find("DEFAULTNAME", name).firstResult();
    }
}
