package com.example.Gestion_stock_management.ServiceImpl;

import com.example.Gestion_stock_management.exception.ResourceNotFoundException;
import com.example.Gestion_stock_management.model.Produit;
import com.example.Gestion_stock_management.repository.ProduitRepository;
import com.example.Gestion_stock_management.service.ProduitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImpl implements ProduitService {
    private final ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public Produit addProduit(Produit produit) {
        Optional<Produit> existingProduct = produitRepository.findByNomAndDescriptionAndCategorie(
                produit.getNom(), produit.getDescription(), produit.getCategorie());

        if (existingProduct.isPresent()) {
            Produit existing = existingProduct.get();
            existing.setQuantite(existing.getQuantite() + produit.getQuantite());
            return produitRepository.save(existing);
        } else {
            return produitRepository.save(produit);
        }

    }

    @Override
    public Produit updateProduit(String id, Produit produit) {
        Optional<Produit> produitExist= produitRepository.findById(id);

        Produit exist = produitExist.orElseThrow(()->new ResourceNotFoundException("Product not exist"));
        exist.setNom(produit.getNom());
        exist.setPrix(produit.getPrix());
        exist.setCategorie(produit.getCategorie());
        exist.setQuantite(produit.getQuantite());
        return produitRepository.save(exist);
    }

    @Override
    public void deleteProduit(String id) {
        Optional<Produit> existingProduct = produitRepository.findById(id);
        if (existingProduct.isPresent()) {
            produitRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
    @Override
    public void deleteProduitWithQuantity(String id, int quantiteToRemove) {
        Optional<Produit> existingProduct = produitRepository.findById(id);
        if (existingProduct.isPresent()) {
            Produit product = existingProduct.get();
            if (product.getQuantite() <= quantiteToRemove) {
                produitRepository.deleteById(id);
            } else {
                product.setQuantite(product.getQuantite() - quantiteToRemove);
                produitRepository.save(product);
            }
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public void deleteProduitByNom(String nom, int quantite) {

    }

    @Override
    public void deleteProduitByNom(String nom) {
        List<Produit> products = produitRepository.findByNom(nom);
        if (!products.isEmpty()) {
            produitRepository.deleteAll(products);
        } else {
            throw new RuntimeException(" Product not found");
        }
    }


    @Override
    public void deleteProduitByNomWithQuantite(String nom, int quantite) {
        List<Produit> products = produitRepository.findByNom(nom);
        if (!products.isEmpty()) {
            for (Produit product : products) {
                if (product.getQuantite() <= quantite) {
                    produitRepository.deleteById(product.getId());
                } else {
                    product.setQuantite(product.getQuantite() - quantite);
                    produitRepository.save(product);
                }
            }
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public Produit findProduitById(String id) {
        return produitRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not exist"));
    }

    @Override
    public List<Produit> findProduitByCategorie(String categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    @Override
    public List<Produit> findProduitByPrix(double prix) {
        return produitRepository.findByPrix(prix);
    }

    @Override
    public List<Produit> listAllProduits() {
        return produitRepository.findAll();
    }
}
