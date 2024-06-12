package com.transportapi.model.dao;

import org.springframework.data.repository.CrudRepository;
import com.transportapi.model.entity.Location;

public interface LocationDao extends CrudRepository<Location, Long>{
    
}
