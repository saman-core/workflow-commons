package io.samancore.workflow.client;

import io.samancore.workflow.model.State;
import io.samancore.workflow.model.Transition;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface WorkflowReactiveClient {

    Uni<State> getState(String moduleName, String productName, String stateId);

    Uni<Transition> getTransition(String moduleName, String productName, String transitionId);

    Uni<List<Transition>> getTransitionsByState(String moduleName, String product, String stateId);

    Uni<List<Transition>> getInitialTransitions(String moduleName, String product);
}