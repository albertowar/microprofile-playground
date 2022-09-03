
package org.example;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/v1")
@RequestScoped
public class HelidonResource {
    private final Configuration configurationProvider;

    @Inject
    public HelidonResource(Configuration configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    @GET
    @Path("/hello/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) {
        return String.format("Hello %s. I'm %s", name, this.configurationProvider.getAppName());
    }
}
