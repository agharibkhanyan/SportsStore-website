package com.s2020iae.restservice;

import com.s2020iae.restservice.model.Product;
import com.s2020iae.restservice.model.Tax;
import com.s2020iae.restservice.service.ProductService;
import com.s2020iae.restservice.service.TaxService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tax")
public class TaxResource {
    @Path("{zip}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getTaxByZip(@PathParam("zip") int zip) {
        Tax tax = TaxService.getTaxByZip(zip);
        if(tax == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("We could not find the requested product(s) resource").build();
        }
        return Response.ok(tax).build();
    }
}
