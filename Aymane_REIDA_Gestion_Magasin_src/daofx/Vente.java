package daofx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Vente {
	private long id;
	private LocalDate date;
	private double total;
	private Client client = new Client();
	private List<LigneCommande> lignescommande = new ArrayList<LigneCommande>();
	private List<Paiement> paiements = new ArrayList<Paiement>();
	private String nom;
	private String prenom;
	private double paye = 0;
	private double reste = 0;

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
		this.reste = this.total - this.paye;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<LigneCommande> getLignescommande() {
		return lignescommande;
	}

	public void setLignescommande(List<LigneCommande> lignescommande) {
		this.lignescommande = lignescommande;
	}

	public List<Paiement> getPaiements() {
		return paiements;
	}

	public void setPaiements(List<Paiement> paiements) {
		this.paiements = paiements;
	}

	public double getPaye() {
		return paye;
	}

	public void setPaye(double paye) {
		this.paye = paye;
		this.reste = this.total - this.paye;
	}

	public double getReste() {
		return reste;
	}

	public void setReste(double reste) {
		this.reste = reste;
	}

	@Override
	public String toString() {
		return client.getNom() + "\t" + client.getNom() + "\t" + total + "\t" + date;
	}

	public Vente(long id, LocalDate date, Client client, double total, List<LigneCommande> lignescommande,
			List<Paiement> paiements) {
		this.id = id;
		this.date = date;
		this.total = total;
		this.client = client;
		this.lignescommande = lignescommande;
		this.paiements = paiements;
	}

	public Vente(long id, LocalDate date, double total, long id_client) {
		this.id = id;
		this.date = date;
		this.total = total;
		this.client.setId(id_client);
	}

	public Vente(long id, LocalDate date, long id_client) {
		this.id = id;
		this.date = date;
		this.client.setId(id_client);
	}

	public Vente() {
	}

	public Vente(long id, LocalDate date, double total, double paye, double reste, long id_client, String nom,
			String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
		this.date = date;
		this.total = total;
		this.paye = paye;
		this.reste = reste;
		this.client.setId(id_client);
	}
}
