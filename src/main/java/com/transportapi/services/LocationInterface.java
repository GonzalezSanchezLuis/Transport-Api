package com.transportapi.services;

import java.security.Principal;
import com.transportapi.model.entity.Location;

public interface LocationInterface {
    Location saveLocation(Principal principal,Location location);
    Location findLocationById(Long id);
    Location updateLocation(Long id,Location location);
}
