package com.transportapi.cofig.Documentation;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public class ApiDocumentationLocation {
    public interface Schemas{
        @Schema(name = "Location", description = "Controlador de ubicaciones")
        class LocationSchema extends com.transportapi.model.entity.Location{}
    }

    @RequestBody(
        description = "Registro de ubicacion del usuario",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = Schemas.LocationSchema.class)
        ))
    public @interface RegisterRequestBody {}

    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Operación exitosa",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Schemas.LocationSchema.class)
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
    public @interface LocationApiResponses {}
}
