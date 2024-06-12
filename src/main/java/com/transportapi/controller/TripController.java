package com.transportapi.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.transportapi.model.entity.Trip;
import com.transportapi.services.TripInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.transportapi.cofig.Documentation.ApiDocumentationTrip;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/trip/")
@Tag(name = "Trip", description = "Controlador de viajes")
public class TripController {
    @Autowired
    private TripInterface tripService;


    @PostMapping("register")
    @Operation(summary = "Registrar la informacion d eun nuevo viaje",description = "Registrar un viaje")
    @ApiDocumentationTrip.TripApiResponses
    public ResponseEntity<Trip> registerTrip(Principal principal,@RequestBody Trip trip){
        try {      
            Trip savedTrip = tripService.registerTrip(principal, trip);
            return ResponseEntity.status(201).body(savedTrip);
            
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
            
        }
    }
    
}
