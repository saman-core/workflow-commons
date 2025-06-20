package io.samancore.workflow.client.rest;

import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import io.samancore.workflow.client.WorkflowClient;
import io.samancore.workflow.model.State;
import io.samancore.workflow.model.Transition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.List;

@ApplicationScoped
public class WorkflowRestClientWrapper implements WorkflowClient {

    @Inject
    Logger log;

    @ConfigProperty(name = "api.url-prefix")
    String urlPrefix;

    @ConfigProperty(name = "api.url-suffix")
    String urlSuffix;

    @Override
    public State getState(String moduleName, String productName, String stateId) {
        log.debugf("WorkflowRestClientWrapper.getState %s %s %s;", moduleName, productName, stateId);
        var url = generateUrl(moduleName);
        var conditionTemplateRestClient = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(WorkflowRestClient.class);
        return conditionTemplateRestClient.getState(productName, stateId);
    }

    @Override
    public Transition getTransition(String moduleName, String productName, String stateId) {
        log.debugf("WorkflowRestClientWrapper.getTransition %s %s %s;", moduleName, productName, stateId);
        var url = generateUrl(moduleName);
        var conditionTemplateRestClient = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(WorkflowRestClient.class);
        return conditionTemplateRestClient.getTransition(productName, stateId);
    }

    @Override
    public List<Transition> getTransitionsByState(String moduleName, String productName, String stateId) {
        log.debugf("WorkflowRestClientWrapper.getTransitionsByState %s %s %s;", moduleName, productName, stateId);
        var url = generateUrl(moduleName);
        var conditionTemplateRestClient = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(WorkflowRestClient.class);
        return conditionTemplateRestClient.getTransitionsByState(productName, stateId);
    }

    @Override
    public List<Transition> getInitialTransitions(String moduleName, String productName) {
        log.debugf("WorkflowRestClientWrapper.getInitialTransitions %s %s;", moduleName, productName);
        var url = generateUrl(moduleName);
        var conditionTemplateRestClient = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(WorkflowRestClient.class);
        return conditionTemplateRestClient.getInitialTransitions(productName);
    }

    private String generateUrl(String moduleName) {
        return urlPrefix.concat(moduleName).concat("-workflow").concat(urlSuffix);
    }
}
