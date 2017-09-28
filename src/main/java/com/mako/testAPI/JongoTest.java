package com.mako.testAPI;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

/**
 * 
 * PDL MIAGE 1718
 * Projet #2 (OpenFoodFacts2CSV)
 * Group 6
 * 
 * @authors HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agnès, ZOHOUN Nellya, TCHIDIME Hugues
 * @version 1.0
 * @since 2017-09-21
 * 
 * Class to test the connection with OFF dump with the API Jongo
 * 
 */

public class JongoTest {

	public static void main(String[] args) {
		
		// Connect to MongoDB server
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		
		// Select database test
		DB database = mongoClient.getDB("test");
		
		// Jongo API entry point
		Jongo jongo = new Jongo(database);
		MongoCollection products = jongo.getCollection("products");
		
		// Query to search first product where pnns_groups_1 = Beverages
		Products one = products.findOne("{product_name_en: 'Orange Juice'}").as(Products.class);
		System.out.println("Product = " + one.getName() + " " + one.getId());
		
		// Query to search and count products where pnns_groups_1 = Beverages
		MongoCursor<Products> all = products.find("{pnns_groups_1: 'Beverages'}").as(Products.class);
		System.out.println("Number of products = " + all.count());
		
		// Close connection
		mongoClient.close();

	}
}
