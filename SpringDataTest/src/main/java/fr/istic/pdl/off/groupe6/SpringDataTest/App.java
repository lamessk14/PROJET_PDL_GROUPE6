package fr.istic.pdl.off.groupe6.SpringDataTest;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

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
 * Class to test the connection with OFF dump with the API Spring Data for MongoDB
 * 
 */

public class App 
{
    
	private static ApplicationContext ctx;

	public static void main( String[] args )
    {

		ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		// Prepare query to search product where pnns_groups_1 = Beverages
		Query searchProductQuery = new Query(Criteria.where("pnns_groups_1").is("Beverages"));

		// Query to search first product where pnns_groups_1 = Beverages
		ProductSD product = mongoOperation.findOne(searchProductQuery, ProductSD.class);
		System.out.println("Product = " + product.toString());
		
		// Query to search and count products where pnns_groups_1 = Beverages
		List<ProductSD> listProducts = mongoOperation.find(searchProductQuery, ProductSD.class);
		System.out.println("Number of products = " + listProducts.size());
		
    }
	
}
