package com.transportapi.services;

import java.security.Principal;
import com.transportapi.model.entity.Trip;

public interface TripInterface {
    Trip registerTrip(Principal pricipal,Trip trip);
}
