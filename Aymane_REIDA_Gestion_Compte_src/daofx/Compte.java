package daofx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Compte implements Serializable {
	private long id;
	private String numero;
	private String nom;
	private String prenom;
	private List<Operation> operations = new ArrayList<Operation>();
	private double solde;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Compte() {

	}

	public Compte(long id, String numero, String nom, String prenom, double solde) {
		this.id = id;
		this.numero = numero;
		this.nom = nom;
		this.prenom = prenom;
		this.solde = solde;
	}
}
