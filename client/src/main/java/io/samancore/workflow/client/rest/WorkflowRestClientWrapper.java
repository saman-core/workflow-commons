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
    public State getEstate(String moduleName, String productName, String stateId) {
        log.debugf("WorkflowRestClientWrapper.getEstate %s %s %s;", moduleName, productName, stateId);
        var url = generateUrl(moduleName, productName);
        var conditionTemplateRestClient = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(WorkflowRestClient.class);
        return conditionTemplateRestClient.getEstate(stateId);
    }

    @Override
    public List<Transition> getTransitions(String moduleName, String productName, String stateId) {
        log.debugf("WorkflowRestClientWrapper.getTransitions %s %s %s;", moduleName, productName, stateId);
        var url = generateUrl(moduleName, productName);
        var conditionTemplateRestClient = QuarkusRestClientBuilder.newBuilder()
                .baseUri(URI.create(url))
                .build(WorkflowRestClient.class);
        return conditionTemplateRestClient.getTransitions(stateId);
    }

    private String generateUrl(String moduleName, String productName) {
        return urlPrefix.concat(moduleName).concat("-workflow-").concat(productName).concat(urlSuffix);
    }
}
