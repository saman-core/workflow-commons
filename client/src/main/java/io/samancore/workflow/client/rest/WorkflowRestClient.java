package io.samancore.workflow.client.rest;

import io.samancore.workflow.model.State;
import io.samancore.workflow.model.Transition;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;

import java.util.List;

@Path("")
@RegisterClientHeaders
public interface WorkflowRestClient {

    @GET
    @Path("state/{stateId}")
    State getEstate(@PathParam("stateId") String stateId);

    @GET
    @Path("state/{stateId}/transitions")
    List<Transition> getTransitions(@PathParam("stateId") String stateId);
}
