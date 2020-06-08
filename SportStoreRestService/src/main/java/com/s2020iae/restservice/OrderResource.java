package com.s2020iae.restservice;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import com.s2020iae.restservice.model.Orders;
import com.s2020iae.restservice.service.OrderService;
/**
 *
 * @author anon
 */
@Path("/orders")
public class OrderResource {
    @Path("{id}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getOrderById(@PathParam("id") int id) {
        Orders order = OrderService.getOrderById(id);
        if(order == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("We could not find the requested order(s) resource").build();
        }
        return Response.ok(order).build();
    }
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllOrders() {
        List<Orders> orders = OrderService.getAllOrders();
        if(orders == null || orders.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("We could not find the requested order(s) resource").build();
        }

        return Response.ok(orders).build();
    }
    @Path("/last")
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getOrderLastId() {
        int lastId = OrderService.getOrderLastId();
        if(lastId == -1) {
            return Response.status(Response.Status.NOT_FOUND).entity("We could not find the requested order(s) resource").build();
        }
        return Response.ok().entity(lastId).build();
    }
    @POST
    @Consumes( { MediaType.APPLICATION_JSON } )
    @Produces( { MediaType.APPLICATION_JSON } )
    public Response addOrder(Orders order) {

        if(order == null){
            System.out.println("A");
            return Response.status(400).entity("Please add employee details !!").build();
        }

        if(OrderService.AddOrder(order)) {
            System.out.println("B");
            return Response.ok().entity("Order Added Successfully").build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    @POST
    public Response addOrder( @FormParam("firstname") String firstname,
                            @FormParam("lastname") String lastname,
                            @FormParam("email") String email,
                            @FormParam("phone") String phone,
                            @FormParam("address") String address,
                            @FormParam("city") String city,
                            @FormParam("state") String state,
                            @FormParam("zip") int zip,
                            @FormParam("billaddr") String billaddr,
                            @FormParam("billcity") String billcity,
                            @FormParam("billstate") String billstate,
                            @FormParam("billzip") int billzip,
                            @FormParam("method") String method,
                            @FormParam("cardname") String cardname,
                            @FormParam("cardnumber") String cardnumber,
                            @FormParam("expmonth") int expmonth,
                            @FormParam("expyear") int expyear,
                            @FormParam("cvv") int cvv,
                            @FormParam("price") String price) {
        Orders order = new Orders();
        order.setId(0);
        order.setFirstName( firstname);
        order.setLastName( lastname);
        order.setEmail( email);
        order.setPhone( phone);
        order.setAddress( address);
        order.setCity( city);
        order.setState( state);
        order.setPrice( price);
        order.setZip( zip);
        order.setBillAddr( billaddr);
        order.setBillCity( billcity);
        order.setBillState( billstate);
        order.setBillZip( billzip);
        order.setMethod( method);
        order.setCardName( cardname);
        order.setCardNumber( cardnumber);
        order.setExpMonth( expmonth);
        order.setExpYear( expyear);
        order.setCvv( cvv);
        if(OrderService.AddOrder(order)) {
            return Response.ok().entity("Order Added Successfully").build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updateOrder(@PathParam("id") int id, Orders order) {
        Orders retrievedProduct = OrderService.getOrderById(id);
        if(retrievedProduct == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested resource").build();
        }
        order.setId(id);
        if(order.getFirstName() == null) {
            order.setFirstName(retrievedProduct.getFirstName());
        }
        if(order.getLastName() == null) {
            order.setLastName(retrievedProduct.getLastName());
        }
        if(order.getEmail() == null) {
            order.setEmail(retrievedProduct.getEmail());
        }
        if(order.getPhone() == null) {
            order.setPhone(retrievedProduct.getPhone());
        }
        if (order.getAddress() == null) {
            order.setAddress(retrievedProduct.getAddress());
        }
        if (order.getPrice() == null) {
            order.setPrice(retrievedProduct.getPrice());
        }
        if (order.getCity() == null) {
            order.setCity(retrievedProduct.getCity());
        }
        if (order.getState() == null) {
            order.setState(retrievedProduct.getState());
        }
        if (order.getZip()==0){
            order.setZip(retrievedProduct.getZip());
        }
        if (order.getBillAddr() == null) {
            order.setBillAddr(retrievedProduct.getBillAddr());
        }
        if (order.getBillCity() == null) {
            order.setBillCity(retrievedProduct.getBillCity());
        }
        if (order.getBillState() == null) {
            order.setBillState(retrievedProduct.getBillState());
        }
        if (order.getBillZip()==0){
            order.setBillZip(retrievedProduct.getBillZip());
        }
        if (order.getMethod() == null) {
            order.setMethod(retrievedProduct.getMethod());
        }
        if (order.getCardName() == null) {
            order.setCardName(retrievedProduct.getCardName());
        }
        if (order.getCardNumber() == null) {
            order.setCardNumber(retrievedProduct.getCardNumber());
        }
        if (order.getExpMonth()==0)
            order.setExpMonth(retrievedProduct.getExpMonth());
        if(order.getExpYear()==0)
            order.setExpYear(retrievedProduct.getExpYear());
        if(order.getCvv()==0)
            order.setCvv(retrievedProduct.getCvv());
        //This calls the JDBC method which in turn calls the the UPDATE SQL command
        if(OrderService.updateOrder(order)) {
            return Response.ok().entity(order).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public Response deleteOrder(@PathParam("id") int id) {
        Orders retrievedOrder = OrderService.getOrderById(id);
        if(retrievedOrder == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("We could not find the requested order(s) resource").build();
        }
        if(OrderService.deleteOrder(retrievedOrder)) {
            return Response.ok().entity("Order Deleted Successfully").build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
