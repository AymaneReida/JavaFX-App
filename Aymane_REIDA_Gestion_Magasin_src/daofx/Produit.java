package daofx;

import java.time.LocalDate;
import java.util.List;

public class Produit {
	private long id;
	private String designation;
	private int qteStock;
	private double prixAchat;
	private double prixVente;
	private LocalDate date;
	private Categorie categorie;
	private List<LigneCommande> lignescommande;

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getQteStock() {
		return qteStock;
	}

	public void setQteStock(int qteStock) {
		this.qteStock = qteStock;
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}

	public double getPrixAchat() {
		return prixAchat;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return designation + "\t" + prixAchat + "\t" + prixVente + "\t" + date + "\t" + categorie.getIntitule();
	}

	public Produit(long id, String designation, int qteStock, double prixAchat, double prixVente, LocalDate date) {
		this.id = id;
		this.designation = designation;
		this.qteStock = qteStock;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
		this.date = date;
	}

	public Produit(long id, String designation, int qteStock, double prixAchat, double prixVente) {
		this.id = id;
		this.designation = designation;
		this.qteStock = qteStock;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
	}

	public Produit() {
	}
}
