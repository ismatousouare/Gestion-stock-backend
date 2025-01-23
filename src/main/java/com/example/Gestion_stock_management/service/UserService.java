package com.example.Gestion_stock_management.service;

import com.example.Gestion_stock_management.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public Optional<User> findByUsername(String username);
    public List<User> getAllUsers();
}
