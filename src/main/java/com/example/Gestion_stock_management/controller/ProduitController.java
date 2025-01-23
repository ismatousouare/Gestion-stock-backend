package com.example.Gestion_stock_management.controller;

import com.example.Gestion_stock_management.ServiceImpl.ProduitServiceImpl;
import com.example.Gestion_stock_management.model.Produit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProduitController {
    private final ProduitServiceImpl produitServiceImpl;

    public ProduitController(ProduitServiceImpl produitService) {
        this.produitServiceImpl = produitService;
    }

    @PostMapping
    public ResponseEntity <Produit> createProduct(@RequestBody Produit produit){
        Produit newProduct = produitServiceImpl.addProduit(produit);
        return ResponseEntity.ok(newProduct);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<Produit> updateProduct(@PathVariable String id,@RequestBody Produit produit){
        Produit updateProduct = produitServiceImpl.updateProduit(id,produit);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/full/{id}")
    public ResponseEntity <Void> deleteProduct(@PathVariable String id){
        produitServiceImpl.deleteProduit(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deleteProductWithQuantity(@PathVariable String id, @RequestParam int quantite) {
        produitServiceImpl.deleteProduitWithQuantity(id, quantite);
    }
    @DeleteMapping("/deleteQuantityByNom")
    public void updateQuantityByNom(@RequestParam String nom, @RequestParam int quantity) {
        produitServiceImpl.deleteProduitByNomWithQuantite(nom, quantity);
    }
    @DeleteMapping("/deleteByNom")
    public void deleteProductByNom(@RequestParam String nom) {
        produitServiceImpl.deleteProduitByNom(nom);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Produit> findProductById(@PathVariable String id){
        Produit findById = produitServiceImpl.findProduitById(id);
        return  ResponseEntity.ok(findById);
    }
    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Produit>> findProductByCategory(@PathVariable String categorie) {
        List<Produit> findByCat = produitServiceImpl.findProduitByCategorie(categorie);
        if (findByCat.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findByCat);
    }

    @GetMapping("/prix/{prix}")
    public ResponseEntity<List<Produit>> findProductByPrice(@PathVariable double prix) {
        List<Produit> findByPrix = produitServiceImpl.findProduitByPrix(prix);
        if (findByPrix.isEmpty()) {
            return ResponseEntity.ok().body(findByPrix);
        }
        return ResponseEntity.ok(findByPrix);
    }

    @GetMapping
    public ResponseEntity <List<Produit>> listAllProducts(){
        List <Produit> listProducts = produitServiceImpl.listAllProduits();
        return ResponseEntity.ok(listProducts);
    }
}
