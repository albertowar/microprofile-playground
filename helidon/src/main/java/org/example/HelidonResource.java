
package org.example;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.client.OpenLibertyClient;
import org.example.client.QuarkusClient;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/v1")
@RequestScoped
public class HelidonResource {
    @Inject
    private Configuration configuration;

    @Inject
    @RestClient
    private OpenLibertyClient openLibertyClient;

    @Inject
    @RestClient
    private QuarkusClient quarkusClient;

    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) {
        return String.format("Hello %s. I'm %s", name, this.configuration.getAppName());
    }

    @POST
    @Path("/greetOpenLiberty")
    @Produces(MediaType.TEXT_PLAIN)
    public String greetOpenLiberty() {
        return String.format("OpenLiberty said: %s.", this.openLibertyClient.hello(this.configuration.getAppName()));
    }

    @POST
    @Path("/greetQuarkus")
    @Produces(MediaType.TEXT_PLAIN)
    public String greetQuarkus() {
        return String.format("Quarkus said: %s.", this.quarkusClient.hello(this.configuration.getAppName()));
    }
}
