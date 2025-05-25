package io.samancore.workflow.client;

import io.samancore.workflow.model.State;
import io.samancore.workflow.model.Transition;

import java.util.List;

public interface WorkflowClient {

    State getEstate(String moduleName, String productName, String stateId);

    List<Transition> getTransitions(String moduleName, String productName, String stateId);
}