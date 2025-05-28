package io.samancore.workflow.client.rest;

import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import io.samancore.workflow.client.WorkflowReactiveClient;
import io.samancore.workflow.model.State;
import io.samancore.workflow.model.Transition;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.List;

@ApplicationScoped
public class WorkflowReactiveRestClientWrapper implements WorkflowReactiveClient {

    @Inject
    Logger log;

    @ConfigProperty(name = "api.url-prefix")
    String urlPrefix;

    @ConfigProperty(name = "api.url-suffix")
    String urlSuffix;

    @Override
    public Uni<State> getState(String moduleName, String productName, String stateId) {
        log.debugf("WorkflowRestClientWrapper.getState %s %s %s;", moduleName, productName, stateId);
        var url = generateUrl(moduleName);
        var conditionTemplateRestClient = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(WorkflowRestClient.class);
        return conditionTemplateRestClient.getStateReactive(productName, stateId);
    }

    @Override
    public Uni<Transition> getTransition(String moduleName, String productName, String stateId) {
        log.debugf("WorkflowRestClientWrapper.getTransition %s %s %s;", moduleName, productName, stateId);
        var url = generateUrl(moduleName);
        var conditionTemplateRestClient = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(WorkflowRestClient.class);
        return conditionTemplateRestClient.getTransitionReactive(productName, stateId);
    }

    @Override
    public Uni<List<Transition>> getTransitionsByState(String moduleName, String productName, String stateId) {
        log.debugf("WorkflowRestClientWrapper.getTransitionsByState %s %s %s;", moduleName, productName, stateId);
        var url = generateUrl(moduleName);
        var conditionTemplateRestClient = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(WorkflowRestClient.class);
        return conditionTemplateRestClient.getTransitionsByStateReactive(productName, stateId);
    }

    @Override
    public Uni<List<Transition>> getInitialTransitions(String moduleName, String productName) {
        log.debugf("WorkflowRestClientWrapper.getInitialTransitions %s %s;", moduleName, productName);
        var url = generateUrl(moduleName);
        var conditionTemplateRestClient = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(WorkflowRestClient.class);
        return conditionTemplateRestClient.getInitialTransitionsReactive(productName);
    }

    private String generateUrl(String moduleName) {
        return urlPrefix.concat(moduleName).concat("-workflow").concat(urlSuffix);
    }
}
