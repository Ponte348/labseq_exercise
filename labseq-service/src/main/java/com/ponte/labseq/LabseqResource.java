package com.ponte.labseq;

import java.math.BigInteger;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/labseq")
public class LabseqResource {

    @Inject
    LabseqService labseqService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Welcome to the labseq exercise.";
    }

    @GET
    @Path("/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSequence(@PathParam("n") String nStr) {
        try {
            Integer n;
            try {
                n = Integer.parseInt(nStr);
            } catch (NumberFormatException e) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Input must be a valid integer.")
                               .build();
            }
            
            BigInteger result = labseqService.sequence(n);
            
            return Response.ok(result).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            System.err.println("Error processing request: " + e.getMessage());
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("An unexpected error occurred.")
                           .build();
        }
    }
}