package applicationConsole;

import java.time.LocalDate;

public class Produit {
	private long id;
	private String designation;
	private int qte;
	private double prix;
	private LocalDate date;
	private double total;

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

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
		total = prix * qte;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
		total = prix * qte;
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

	@Override
	public String toString() {
		return designation + "\t" + qte + "\t" + prix + "\t" + date;
	}

	public Produit(long id, String designation, int qte, double prix, LocalDate date) {
		this.id = id;
		this.designation = designation;
		setQte(qte);
		setPrix(prix);
		this.date = date;
	}
}
