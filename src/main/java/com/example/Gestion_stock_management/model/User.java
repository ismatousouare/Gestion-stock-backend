package com.example.Gestion_stock_management.model;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Users")
@Data
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
}
