package com.example.Gestion_stock_management.exception;

public class ResourceNotFoundException extends  RuntimeException{
    // Constructeur avec message d'erreur
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Constructeur avec message et cause (optionnel)
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
