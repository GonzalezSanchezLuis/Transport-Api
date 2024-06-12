package com.transportapi.services.impl;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.transportapi.model.dao.LocationDao;
import com.transportapi.model.dao.UserDao;
import com.transportapi.model.entity.Location;
import com.transportapi.model.entity.User;
import com.transportapi.services.LocationInterface;

@Service
public class LocationImpl implements LocationInterface{
    @Autowired
    private LocationDao locationDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Location saveLocation(Principal principal, Location location) {
        String username = principal.getName();
        User userId =  userDao.findUserByEmail(username);
        location.setUser(userId); 
        return locationDao.save(location);
    }

    @Override
    public Location findLocationById(Long id) {
       Location findLocationById =  locationDao.findById(id).orElseThrow();
       if(findLocationById != null){
         return findLocationById;
       }else{
         throw new IllegalArgumentException("Invalid location ID: " + id);
       }
    }

    @SuppressWarnings("null")
    @Override
    public Location updateLocation(Long id,Location location) {
     Location existingLocation = locationDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid location ID: " + id));
     if (existingLocation != null) {
         existingLocation.setDestinationLongitude(location.getDestinationLongitude());
         existingLocation.setDestinationLatitude(location.getDestinationLatitude());
         existingLocation.setDestinationName(location.getDestinationName());
         existingLocation.setDistance(location.getDistance());
         existingLocation.setOriginLongitude(location.getOriginLongitude());
         existingLocation.setOriginLatitude(location.getOriginLatitude());
         existingLocation.setOriginName(location.getOriginName());
         existingLocation.setEstimatedDuration(location.getEstimatedDuration());
     }
    
    return locationDao.save(existingLocation);


    }
    
}
