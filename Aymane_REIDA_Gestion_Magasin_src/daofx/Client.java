package daofx;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Client implements Serializable {
	private long id;
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private String adresse;
	private LocalDate date;
	// private List<Vente> ventes;
	private Compte compte;

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return nom + "\t" + prenom + "\t" + telephone + "\t" + email + "\t" + adresse + "\t" + date;
	}

	public Client(long id, String nom, String prenom, String telephone, String email, String adresse, LocalDate date) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.email = email;
		this.adresse = adresse;
		this.date = date;
	}

	public Client(long id, String nom, String prenom, String telephone) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
	}

	public Client() {
	}
}
