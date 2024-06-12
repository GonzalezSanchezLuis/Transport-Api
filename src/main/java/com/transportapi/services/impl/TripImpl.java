package com.transportapi.services.impl;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.transportapi.model.dao.TripDao;
import com.transportapi.model.dao.UserDao;
import com.transportapi.model.entity.Trip;
import com.transportapi.model.entity.User;
import com.transportapi.services.TripInterface;


@Service
public class TripImpl implements TripInterface{
    @Autowired
    private TripDao tripDao;

    @Autowired
    private UserDao userDao;


    @Override
    public Trip registerTrip(Principal principal, Trip trip) {
        String username = principal.getName();
        User userId =  userDao.findUserByEmail(username);
        trip.setUser(userId);  
       
        return tripDao.save(trip);
    }
    
}
