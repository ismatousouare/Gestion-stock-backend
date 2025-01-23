package com.example.Gestion_stock_management.service;

import com.example.Gestion_stock_management.model.Produit;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProduitService {

    Produit addProduit(Produit produit);
    Produit updateProduit(String id, Produit produit);
    void deleteProduit(String id);
    void deleteProduitWithQuantity(String id, int quantite);
    public void deleteProduitByNom(String nom, int quantite);

    void deleteProduitByNom(String nom);

    void deleteProduitByNomWithQuantite(String nom, int quantite);

    Produit findProduitById(String id);
    List<Produit> findProduitByCategorie(String categorie);
    List<Produit> findProduitByPrix(double prix);
    List<Produit> listAllProduits();
}
