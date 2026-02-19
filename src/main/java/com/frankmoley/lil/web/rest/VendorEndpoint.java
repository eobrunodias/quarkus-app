package com.frankmoley.lil.web.rest;

import com.frankmoley.lil.data.entity.Vendor;
import com.frankmoley.lil.data.repository.VendorRepository;
import io.quarkus.runtime.util.StringUtil;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import java.util.ArrayList;
import java.util.List;

@Path("/rest/vendors")
@Produces("application/json")
@Consumes("application/json")
public class VendorEndpoint {
    private final VendorRepository vendorRepository;

    public VendorEndpoint(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @GET
    public List<Vendor> getVendors(@QueryParam("email")String email, @QueryParam("name")String name){
        if(StringUtil.isNullOrEmpty(email) && StringUtil.isNullOrEmpty(name)){
            return this.vendorRepository.listAll();
        }else{
            List<Vendor> vendors = new ArrayList<>();
            if(!StringUtil.isNullOrEmpty(email) && !StringUtil.isNullOrEmpty(name)){
                Vendor vendor = this.vendorRepository.findByEmailAndName(email, name);
                vendors.add(vendor);
            }else if(!StringUtil.isNullOrEmpty(email)){
                Vendor vendor = this.vendorRepository.findByEmail(email);
                vendors.add(vendor);
            }else{
                Vendor vendor = this.vendorRepository.findByName(name);
                vendors.add(vendor);
            }
            return vendors;
        }
    }

    @POST
    @ResponseStatus(201)
    @Transactional
    public Vendor addVendor(Vendor vendor){
        this.vendorRepository.persist(vendor);
        return vendor;
    }

    @GET
    @Path("/{id}")
    public Vendor getVendor(@RestPath("id")long id){
        Vendor vendor = this.vendorRepository.findById(id);
        if (vendor == null){
            throw new WebApplicationException(404);
        }
        return vendor;
    }

    @PUT
    @Path("/{id}")
    @ResponseStatus(204)
    @Transactional
    public void updateVendor(@RestPath("id")long id, Vendor vendor){
        if (id != vendor.getId()){
            throw new WebApplicationException(400);
        }
        this.vendorRepository.persist(vendor);
    }

    @DELETE
    @Path("/{id}")
    @ResponseStatus(205)
    @Transactional
    public void deleteVendor(@RestPath("id")long id) {
        this.vendorRepository.deleteById(id);
    }

}
