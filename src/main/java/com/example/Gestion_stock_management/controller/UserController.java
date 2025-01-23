package com.example.Gestion_stock_management.controller;

import com.example.Gestion_stock_management.ServiceImpl.UserServiceImpl;
import com.example.Gestion_stock_management.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    public final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    @PostMapping
    public ResponseEntity <User> createUser(@RequestBody User user){
        User newUser = userServiceImpl.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/username")
    public ResponseEntity<User> getUser(@PathVariable String username){
        return ResponseEntity.ok(userServiceImpl.findByUsername(username).orElseThrow());
    }

    @GetMapping
    public ResponseEntity <List<User>>listAllUsers(){
        List<User> listUsers = userServiceImpl.getAllUsers();
        return ResponseEntity.ok(listUsers);

    }


}
