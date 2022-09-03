package org.example;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1")
public class QuarkusResource {

    private final Configuration configurationProvider;

    @Inject
    public QuarkusResource(Configuration configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) {
        return String.format("Hello %s. I'm %s", name, configurationProvider.getAppName());
    }
}