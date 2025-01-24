package com.example.Gestion_stock_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "produits")
@Data
public class Produit {
    @Id
    private String id;
    private String nom;
    private String description;
    private double prix;
    private int quantite;
    private String categorie;

}
