package io.samancore.workflow.client;

import io.samancore.workflow.model.State;
import io.samancore.workflow.model.Transition;

import java.util.List;

public interface WorkflowClient {

    State getState(String moduleName, String productName, String stateId);

    Transition getTransition(String moduleName, String productName, String transitionId);

    List<Transition> getTransitionsByState(String moduleName, String product, String stateId);

    List<Transition> getInitialTransitions(String moduleName, String product);
}