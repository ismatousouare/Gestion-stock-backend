package com.example.Gestion_stock_management.ServiceImpl;

import com.example.Gestion_stock_management.model.Role;
import com.example.Gestion_stock_management.repository.RoleRepository;
import com.example.Gestion_stock_management.repository.UserRepository;
import com.example.Gestion_stock_management.service.RoleService;
import com.example.Gestion_stock_management.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role saveRole(Role role) {

        Optional<Role> existingRole = roleRepository.findByName(role.getName());
        if (existingRole.isPresent()) {
            throw new IllegalArgumentException("Role '" + role.getName() + "' already exist.");
        }
        return roleRepository.save(role);
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }
}
