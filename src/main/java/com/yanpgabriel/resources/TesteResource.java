package com.yanpgabriel.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("teste")
public class TesteResource {
    
    @GET
    @Path("public")
    public Response publico() {
        return Response.ok("public").build();
    }
    
    @GET
    @RolesAllowed("admin")
    @Path("admin")
    public Response admin() {
        return Response.ok("admin").build();
    }
    
    @GET
    @RolesAllowed({"user"})
    @Path("user")
    public Response user() {
        return Response.ok("user").build();
    }
    
}
