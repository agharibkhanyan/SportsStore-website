package com.s2020iae.restservice;

import com.s2020iae.restservice.model.Item;
import com.s2020iae.restservice.service.ItemService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/items")
public class ItemResource {
    @Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON }) //This provides only JSON responses
    public Response getAllItems(@PathParam("id") int id) {
        List<Item> itemList = ItemService.getAllItems(id);
        if(itemList == null || itemList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("We could not find the requested items(s) resource").build();
        }

        return Response.ok(itemList).build();
    }

     @POST
     @Consumes( { MediaType.APPLICATION_JSON } )
     @Produces( { MediaType.APPLICATION_JSON } )
     public Response addItem(Item item) {

         if(ItemService.AddItem(item)) {
             return Response.ok().entity("Item Added Successfully").build();
         }
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
     }

     @POST
     public Response addItem(@FormParam("orderid") int orderId,
                            @FormParam("productid") int productId) {
         Item item = new Item();
         item.setId(0);
         item.setOrderId(orderId);
         item.setProductId(productId);
         if(ItemService.AddItem(item)) {
             return Response.ok().entity("Item Added Successfully").build();
         }
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
     }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response deleteItem(@PathParam("id") int id) {

        List<Item> itemList = ItemService.getAllItems(id);
        if(itemList == null || itemList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("We could not find the requested items(s) resource").build();
        }
        
        if(ItemService.deleteItem(id)) {
            return Response.ok().entity("Item Deleted Successfully").build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
