package com.example.Gestion_stock_management.controller;

import com.example.Gestion_stock_management.ServiceImpl.RoleServiceImpl;
import com.example.Gestion_stock_management.model.Role;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    public final RoleServiceImpl roleServiceImpl;

    public RoleController(RoleServiceImpl roleServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
    }


    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody @Valid Role role) {
        try {
            Role savedRole = roleServiceImpl.saveRole(role);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping("/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name)
    {
        return ResponseEntity.ok(roleServiceImpl.findByName(name).orElseThrow());
    }
    @GetMapping
    public ResponseEntity<List<Role>>getAllRoles(){
        return ResponseEntity.ok(roleServiceImpl.getAllRoles());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable String id){
        roleServiceImpl.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
