package com.ponte.labseq;

import java.math.BigInteger;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/labseq")
@Tag(name = "Labseq", description = "Labseq sequence operations")
public class LabseqResource {

    @Inject
    LabseqService labseqService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Welcome message")
    public String hello() {
        return "Welcome to the labseq exercise.";
    }

    @GET
    @Path("/{n}")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(
        summary = "Get sequence value",
        description = "Returns the value of the labseq sequence at position n"
    )
    @APIResponse(responseCode = "200", description = "Successful operation")
    @APIResponse(responseCode = "400", description = "Invalid input")
    @APIResponse(responseCode = "500", description = "Internal server error")
    public Response getSequence(
            @Parameter(description = "Position in the sequence", example = "10") 
            @PathParam("n") String nStr) {
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

            // Return as string because Angular can't handle big numbers
            return Response.ok(result.toString()).build();
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