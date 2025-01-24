package com.example.Gestion_stock_management.ServiceImpl;

import com.example.Gestion_stock_management.exception.ResourceNotFoundException;
import com.example.Gestion_stock_management.model.Produit;
import com.example.Gestion_stock_management.repository.ProductRepository;
import com.example.Gestion_stock_management.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Produit addProduit(Produit produit) {
        Optional<Produit> existingProduct = productRepository.findByNomAndDescriptionAndCategorie(
                produit.getNom(), produit.getDescription(), produit.getCategorie());

        if (existingProduct.isPresent()) {
            Produit existing = existingProduct.get();
            existing.setQuantite(existing.getQuantite() + produit.getQuantite());
            return productRepository.save(existing);
        } else {
            return productRepository.save(produit);
        }

    }

    @Override
    public Produit updateProduit(String id, Produit produit) {
        Optional<Produit> produitExist= productRepository.findById(id);

        Produit exist = produitExist.orElseThrow(()->new ResourceNotFoundException("Product not exist"));
        exist.setNom(produit.getNom());
        exist.setPrix(produit.getPrix());
        exist.setCategorie(produit.getCategorie());
        exist.setQuantite(produit.getQuantite());
        return productRepository.save(exist);
    }

    @Override
    public void deleteProduit(String id) {
        Optional<Produit> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found");
        }
    }
    @Override
    public void deleteProduitWithQuantity(String id, int quantiteToRemove) {
        Optional<Produit> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Produit product = existingProduct.get();
            if (product.getQuantite() <= quantiteToRemove) {
                productRepository.deleteById(id);
            } else {
                product.setQuantite(product.getQuantite() - quantiteToRemove);
                productRepository.save(product);
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
        List<Produit> products = productRepository.findByNom(nom);
        if (!products.isEmpty()) {
            productRepository.deleteAll(products);
        } else {
            throw new RuntimeException(" Product not found");
        }
    }


    @Override
    public void deleteProduitByNomWithQuantite(String nom, int quantite) {
        List<Produit> products = productRepository.findByNom(nom);
        if (!products.isEmpty()) {
            for (Produit product : products) {
                if (product.getQuantite() <= quantite) {
                    productRepository.deleteById(product.getId());
                } else {
                    product.setQuantite(product.getQuantite() - quantite);
                    productRepository.save(product);
                }
            }
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public Produit findProduitById(String id) {
        return productRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not exist"));
    }

    @Override
    public List<Produit> findProduitByCategorie(String categorie) {
        return productRepository.findByCategorie(categorie);
    }

    @Override
    public List<Produit> findProduitByPrix(double prix) {
        return productRepository.findByPrix(prix);
    }

    @Override
    public List<Produit> listAllProduits() {
        return productRepository.findAll();
    }
}
