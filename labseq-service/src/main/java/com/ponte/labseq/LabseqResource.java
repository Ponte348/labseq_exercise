package com.ponte.labseq;

import java.math.BigInteger;

import io.quarkus.cache.CacheResult;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
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
    @CacheResult(cacheName = "labseqCache")
    public Response getSequence(Integer n) {
        try {
            BigInteger result = labseqService.sequence(n);
            return Response.ok(result).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Input must be a non-negative integer.")
                           .build();
        }
    }
}
