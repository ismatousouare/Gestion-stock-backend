package com.example.Gestion_stock_management.repository;

import com.example.Gestion_stock_management.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends MongoRepository<Role,String> {

    Optional<Role> findByName(String role);
}
