package com.example.Gestion_stock_management.service;

import com.example.Gestion_stock_management.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    public Role saveRole(Role role);


    public List<Role> getAllRoles() ;

    public void deleteRole(String id) ;

}
