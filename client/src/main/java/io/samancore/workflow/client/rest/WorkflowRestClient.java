package io.samancore.workflow.client.rest;

import io.samancore.workflow.model.State;
import io.samancore.workflow.model.Transition;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;

import java.util.List;

@Path("")
@RegisterClientHeaders
public interface WorkflowRestClient {

    @GET
    @Path("{product}/state/{stateId}")
    State getState(@PathParam("product") String product, @PathParam("stateId") String stateId);

    @GET
    @Path("{product}/transition/{transitionId}")
    Transition getTransition(@PathParam("product") String product, @PathParam("transitionId") String transitionId);

    @GET
    @Path("{product}/state/{stateId}/transitions")
    List<Transition> getTransitionsByState(@PathParam("product") String product, @PathParam("stateId") String stateId);

    @GET
    @Path("{product}/initial-transitions")
    List<Transition> getInitialTransitions(@PathParam("product") String product);

    @GET
    @Path("{product}/state/{stateId}")
    Uni<State> getStateReactive(@PathParam("product") String product, @PathParam("stateId") String stateId);

    @GET
    @Path("{product}/transition/{transitionId}")
    Uni<Transition> getTransitionReactive(@PathParam("product") String product, @PathParam("transitionId") String transitionId);

    @GET
    @Path("{product}/state/{stateId}/transitions")
    Uni<List<Transition>> getTransitionsByStateReactive(@PathParam("product") String product, @PathParam("stateId") String stateId);

    @GET
    @Path("{product}/initial-transitions")
    Uni<List<Transition>> getInitialTransitionsReactive(@PathParam("product") String product);
}
