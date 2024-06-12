package com.transportapi.model.dao;


import org.springframework.data.repository.CrudRepository;
import com.transportapi.model.entity.User;

public interface UserDao extends CrudRepository<User,Long>{
   public User findUserByEmail(String email); 
}
