package com.example.Gestion_stock_management.repository;

import com.example.Gestion_stock_management.model.Produit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Produit,String> {
    Optional<Produit> findByNomAndDescriptionAndCategorie(String nom, String description, String categorie);
    List<Produit> findByCategorie(String categorie);
    List<Produit> findByPrix(double prix);
    List<Produit> findByNom(String nom);
}
