package daofx;

import java.time.LocalDate;

public class Operation {
	private long id;
	private LocalDate date;
	private double montant;
	private String type;
	private Compte compte = new Compte();
	
	public long getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getMontant() {
		return montant;
	}

	public String getType() {
		return type;
	}

	public Compte getCompte() {
		return compte;
	}

	public Operation(long id, long idCompte, String type, double montant, LocalDate date, String nom, String prenom) {
		this.id = id;
		this.compte.setId(idCompte);
		this.type = type;
		this.montant = montant;
		this.date = date;
		this.compte.setNom(nom);
		this.compte.setPrenom(prenom);
	}
	
	public Operation(long id, long idCompte, String type, double montant, LocalDate date) {
		this.id = id;
		this.compte.setId(idCompte);
		this.type = type;
		this.montant = montant;
		this.date = date;
	}
}
