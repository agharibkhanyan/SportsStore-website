package com.s2020iae.restservice;

import com.s2020iae.restservice.model.State;
import com.s2020iae.restservice.service.StateService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/states")
public class StateResource {
    @Path("{state}")
    @GET
    @Produces( { MediaType.APPLICATION_JSON })
    public Response getAllStates(@PathParam("state") String state) {
        List<State> stateList = StateService.getAllStates(state);
        if(stateList == null || stateList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("We could not find the requested state(s) resource").build();
        }
        return Response.ok(stateList).build();
    }
}
