package com.transportapi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transportapi.cofig.Documentation.ApiDocumentationLocation;
import com.transportapi.model.entity.Location;
import com.transportapi.services.LocationInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/location/")
@Tag(name = "Location", description = "Controlador registro de ubicación del usuario")
public class LocationController {
    
    @Autowired
    private  LocationInterface locationService;

    @PostMapping("save")
    @Operation(summary = "Registrar la ubicacion del usuario",description = "Registrar un usuario")
    @ApiDocumentationLocation.LocationApiResponses

    public ResponseEntity<Location> saveLocation(Principal principal,@RequestBody Location location){
        Location savedLocation = locationService.saveLocation(principal,location);
        return ResponseEntity.status(201).body(savedLocation);
    }

    @GetMapping("get/{id}")
    @Operation(summary = "Obtiene la ubicacion del usuario registrada en la base de datos",description = "Obtiene la ubicacion gurdada en la base de datos")
    @ApiDocumentationLocation.LocationApiResponses
    
    public ResponseEntity<Location> findLocationById(@PathVariable Long id){
        Location location = locationService.findLocationById(id);
        return ResponseEntity.status(200).body(location);
    }

    @PutMapping("update/{id}")
    @Operation(summary = "Actualiza la ubicacion del usuario registrada en la base de datos",description = "actualiza  ubicacion guardada en la base de datos")
    @ApiDocumentationLocation.LocationApiResponses
    
    public ResponseEntity<Location> update(@PathVariable Long id, @RequestBody Location location){
        try {
            Location updatedLocation = locationService.updateLocation(id, location); 
            return ResponseEntity.ok(updatedLocation);
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}
