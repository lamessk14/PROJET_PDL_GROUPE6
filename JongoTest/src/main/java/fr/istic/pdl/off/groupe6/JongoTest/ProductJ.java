package fr.istic.pdl.off.groupe6.JongoTest;

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

public class ProductJ {

	private long _id;
	private String product_name_en;

	public ProductJ() {

	}

	public ProductJ(int _id, String product_name_en) {
		super();
		this.product_name_en = product_name_en;
		this._id = _id;
	}

	public String getName() {
		return product_name_en;
	}

	public void setName(String product_name_en) {
		this.product_name_en = product_name_en;
	}

	public long getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	@Override
    public String toString() {
        return (_id + " " + product_name_en);
    }
	
}
