package daofx;

public class LigneCommande {
	private long id;
	private int qte;
	private double sousTotal;
	private Vente vente = new Vente();
	private Produit produit = new Produit();
	private String designation;
	private double prixVente;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public double getSousTotal() {
		return sousTotal;
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public String getDesignation() {
		return designation;
	}

	public double getPrixVente() {
		return prixVente;
	}

	@Override
	public String toString() {
		return produit.getDesignation() + "\t" + prixVente + "\t" + qte + "\t" + sousTotal;
	}

	public LigneCommande(long id, int qte, Produit produit) {
		this.id = id;
		this.qte = qte;
		this.produit = produit;
		this.sousTotal = qte * produit.getPrixVente();
	}
	
	public LigneCommande(long id, int qte, long id_vente, long id_produit, String designation, double prixVente) {
		this.designation = designation;
		this.prixVente = prixVente;
		this.id = id;
		this.qte = qte;
		this.produit.setId(id_produit);
		this.vente.setId(id_vente);
		this.sousTotal = qte * prixVente;
	}
	
	public LigneCommande(long id, int qte, long id_produit, double prixVente) {
		this.id = id;
		this.qte = qte;
		this.produit.setId(id_produit);
		this.prixVente = prixVente;
		this.sousTotal = qte * prixVente;
	}
	
	public LigneCommande(long id, int qte, long id_vente, long id_produit, double prixVente) {
		this.id = id;
		this.qte = qte;
		this.produit.setId(id_produit);
		this.vente.setId(id_vente);
		this.prixVente = prixVente;
		this.sousTotal = qte * prixVente;
	}
}
