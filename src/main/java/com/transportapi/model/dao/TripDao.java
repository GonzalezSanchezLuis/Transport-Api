package com.transportapi.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.transportapi.model.entity.Trip;

public interface TripDao extends CrudRepository<Trip, Long>{
    Long findRouteIdByTripId(Long tripId);
}
