package com.transportapi.cofig.Documentation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class ApiDocumentationTrip {
    public interface Schemas{
       @Schema(name = "Trip", description = "Controlador Trip")
       class TripSchema extends com.transportapi.model.entity.Trip{} 
    }

     @RequestBody(
        description = "Registro de un nuevo viaje",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = Schemas.TripSchema.class)
        ))
    public @interface RegisterRequestBody {}

     // Define una anotación para las respuestas de un viaje
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Schemas.TripSchema.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error del servidor",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = String.class)
            )
        )
    })
    public @interface TripApiResponses {}
}
