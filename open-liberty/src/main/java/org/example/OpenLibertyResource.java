package org.example;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.HelidonClient;
import org.example.client.QuarkusClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/v1")
public class OpenLibertyResource {

    @Inject
    private Configuration configuration;

    @Inject
    @RestClient
    private HelidonClient helidonClient;

    @Inject
    @RestClient
    private QuarkusClient quarkusClient;

    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) {
        return String.format("Hello %s. I'm %s", name, configuration.getAppName());
    }

    @POST
    @Path("/greetHelidon")
    @Produces(MediaType.TEXT_PLAIN)
    public String greetHelidon() {
        return String.format("Helidon said: %s.", this.helidonClient.hello(this.configuration.getAppName()));
    }

    @POST
    @Path("/greetQuarkus")
    @Produces(MediaType.TEXT_PLAIN)
    public String greetQuarkus() {
        return String.format("Quarkus said: %s.", this.quarkusClient.hello(this.configuration.getAppName()));
    }
}