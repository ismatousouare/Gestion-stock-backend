package com.example.Gestion_stock_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionStockManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionStockManagementApplication.class, args);

		// Affichage du message de bienvenue
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		System.out.println("Bienvenue dans l'application Gestion Stock Management !");
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
	}
}
