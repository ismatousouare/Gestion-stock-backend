package com.example.Gestion_stock_management.ServiceImpl;

import com.example.Gestion_stock_management.model.User;
import com.example.Gestion_stock_management.repository.UserRepository;
import com.example.Gestion_stock_management.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl( UserRepository userRepository) {
       this.userRepository = userRepository;
    }


    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
