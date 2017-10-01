package fr.istic.pdl.off.groupe6.JavaDriverTest;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

/**
 * 
 * PDL MIAGE 1718
 * Projet #2 (OpenFoodFacts2CSV)
 * Group 6
 * 
 * @authors HERNANDEZ Maykol, ADDA Raoul, MACKONGO Louise-Agnès, ZOHOUN Nellya, TCHIDIME Hugues
 * @version 1.1
 * @since 2017-09-21
 * 
 * Class to test the connection with OFF dump with the API MongoDB Java Driver
 * 
 */

public class App 
{

	public static void main(String[] args) {
		// Connect to MongoDB server
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		
		// Access a Database "test"
		MongoDatabase database = mongoClient.getDatabase("test");
		
		// Access a Collection "products"
		MongoCollection<Document> collection = database.getCollection("products");

		// Find the First Document in a Collection
		Document myDoc = collection.find().first();
		System.out.println(myDoc.toJson());
		
		// Get A Single Document That Matches a Filter where “pnns_groups_1=Beverages”
		myDoc = collection.find(eq("pnns_groups_1", "Beverages")).first();
		System.out.println(myDoc.toJson());
		
		// Get All Documents That Match a Filter where “pnns_groups_1=Beverages”
		MongoCursor<Document> cursor = collection.find(gt("pnns_groups_1", "Beverages")).iterator();
		try {
		    while (cursor.hasNext()) {
		        System.out.println(cursor.next().toJson());
		    }
		} finally {
		    cursor.close();
		}
		
		mongoClient.close();

	}
}
