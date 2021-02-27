package daofx;

import java.io.Serializable;
import java.time.LocalDate;

public class Paiement implements Serializable {
	private long id;
	private int key;
	private double montant;
	private LocalDate date;
	private String type;
	private String numeroCheque;
	private LocalDate dateEcheance;
	private String proprietaire;
	private String banque;
	private Vente vente = new Vente();

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumeroCheque() {
		return numeroCheque;
	}

	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	public LocalDate getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(LocalDate dateEcheance) {
		this.dateEcheance = dateEcheance;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getBanque() {
		return banque;
	}

	public void setBanque(String banque) {
		this.banque = banque;
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}

	public Paiement(long id, double montant, LocalDate date, String type, String numeroCheque, LocalDate dateEcheance,
			String proprietaire, String banque, long id_vente) {
		this.id = id;
		this.montant = montant;
		this.date = date;
		this.type = type;
		this.numeroCheque = numeroCheque;
		this.dateEcheance = dateEcheance;
		this.proprietaire = proprietaire;
		this.banque = banque;
		this.vente.setId(id_vente);
	}
}
