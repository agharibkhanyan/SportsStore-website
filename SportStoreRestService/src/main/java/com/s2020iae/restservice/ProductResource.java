package com.s2020iae.restservice;

import com.s2020iae.restservice.model.Product;
import com.s2020iae.restservice.service.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
public class ProductResource {
    @Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getProductById(@PathParam("id") int id) {
        Product product = ProductService.getProductById(id);
        if(product == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("We could not find the requested product(s) resource").build();
        }

        return Response.ok(product).build();
    }

    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllProducts() {
        List<Product> productList = ProductService.getAllProducts();

        if(productList == null || productList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("We could not find the requested product(s) resource").build();
        }

        return Response.ok(productList).build();
    }

     @POST
     @Consumes({MediaType.APPLICATION_FORM_URLENCODED}) //This method accepts form parameters.
     public Response addProduct(Product product) {

         if(ProductService.AddProduct(product)) {
             return Response.ok().entity("Product Added Successfully").build();
         }

         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


     }

     @POST
     public Response addProduct(@FormParam("name") String name,
                                @FormParam("summary") String summary,
                                @FormParam("thumbnail") String thumbnail,
                                @FormParam("category") String category,
                                @FormParam("detail") String detail,
                                @FormParam("price") Float price) {

         Product product = new Product();
         product.setId(0);
         product.setName(name);
         product.setSummary(summary);
         product.setThumbnail(thumbnail);
         product.setCategory(category);
         product.setDetail(detail);
         product.setPrice(price);

         if(ProductService.AddProduct(product)) {
             return Response.ok().entity("Product Added Successfully").build();
         }

         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


     }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateProduct(@PathParam("id") int id, Product product) {

        Product retrievedProduct = ProductService.getProductById(id);

        if(retrievedProduct == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested product(s) resource").build();
        }

        product.setId(id);

        if(product.getDetail() == null) {
            product.setDetail(retrievedProduct.getDetail());
        }

        if (product.getSummary() == null) {
            product.setSummary(retrievedProduct.getSummary());
        }
        
        if (product.getName()==null){
            product.setName(retrievedProduct.getName());
        }
        
        if (product.getThumbnail()==null){
            product.setThumbnail(retrievedProduct.getThumbnail());
        }
        
        if (product.getCategory()==null){
            product.setCategory(retrievedProduct.getCategory());
        }
        
        if (product.getPrice()==null){
            product.setPrice(retrievedProduct.getPrice());
        }

        if(ProductService.updateProduct(product)) {

            return Response.ok().entity(product).build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response deleteProduct(@PathParam("id") int id) {

        Product retrievedProduct = ProductService.getProductById(id);

        if(retrievedProduct == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested product(s) resource").build();
        }

        if(ProductService.deleteProduct(retrievedProduct)) {
            return Response.ok().entity("Product Deleted Successfully").build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();


    }

}
