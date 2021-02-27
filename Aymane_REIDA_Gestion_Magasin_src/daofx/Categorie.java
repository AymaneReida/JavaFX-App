package daofx;

import java.util.List;

public class Categorie {
	private long id;
	private String intitule;
	private List<Produit> produits;

	public List<Produit> getProduits() {
		return produits;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Categorie(long id, String intitule) {
		super();
		this.id = id;
		this.intitule = intitule;
	}
}
