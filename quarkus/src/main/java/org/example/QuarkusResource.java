package org.example;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.HelidonClient;
import org.example.client.OpenLibertyClient;

@Path("/v1")
public class QuarkusResource {

    @Inject
    private Configuration configuration;

    @Inject
    @RestClient
    private HelidonClient helidonClient;

    @Inject
    @RestClient
    private OpenLibertyClient openLibertyClient;

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
    @Path("/greetOpenLiberty")
    @Produces(MediaType.TEXT_PLAIN)
    public String greetOpenLiberty() {
        return String.format("Quarkus said: %s.", this.openLibertyClient.hello(this.configuration.getAppName()));
    }
}