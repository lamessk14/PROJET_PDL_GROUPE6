package testAPI;

import java.util.Set;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

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
 * Class to test the connection with OFF dump with the API MongoDB Java Driver
 * 
 */

public class JavaDriver {

	public static void main(String[] args) {
		// Connect to MongoDB server
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		
		// Display all collections from selected database
		DB db = mongo.getDB("test");
		Set<String> tables = db.getCollectionNames();
	
		for(String coll : tables){
			System.out.println(coll);
		}
		
		// Find document where “_id=20003470”, and display it with DBCursor
		DBCollection table = db.getCollection("products");

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id" , "20003470");

		DBCursor cursor = table.find(searchQuery);

		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		
		mongo.close();

	}
}
