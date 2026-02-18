package com.frankmoley.lil.web.rest;

import com.frankmoley.lil.data.entity.Service;
import com.frankmoley.lil.data.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

public class ServiceEndpoint {

    private final ServiceRepository serviceRepository;

    public ServiceEndpoint(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GET
    public List<Service> getAllServices(){
        return this.serviceRepository.listAll();
    }

    @POST
    @ResponseStatus(201)
    @Transactional
    public Service addService(Service service){
        this.serviceRepository.persist(service);
        return service;
    }

    @GET
    @Path("/{id}")
    public Service getService(@RestPath("id")long id){
        Service service = this.serviceRepository.findById(id);
        if(service == null){
            throw new WebApplicationException(404);
        }
        return service;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @ResponseStatus(204)
    public void updateService(@RestPath("id")long id, Service service){
        if (id != service.getId()){
            throw new WebApplicationException(400);
        }
        this.serviceRepository.persist(service);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @ResponseStatus(205)
    public void deleteService(@RestPath("id")long id){
        this.serviceRepository.deleteById(id);
    }
}
