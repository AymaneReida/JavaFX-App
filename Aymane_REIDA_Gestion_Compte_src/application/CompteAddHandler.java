package application;

import daofx.ICompteDAO;
import daofx.CompteDaoImpl;
import daofx.Compte;

public class CompteAddHandler {
	ICompteDAO pdao = new CompteDaoImpl();
	CompteFormWindow formCompte = null;

	public CompteAddHandler(CompteFormWindow formCompte) {
		this.formCompte = formCompte;
	}

	public void addClick() {
		String nom = formCompte.compteNomTextField.getText();
		String prenom = formCompte.comptePrenomTextField.getText();
		String compte = formCompte.compteNumeroTextField.getText();
		double solde = Double.valueOf(formCompte.compteSoldeTextField.getText());
		if (!nom.equals("") && !prenom.equals("") && !compte.equals("")) {
			Compte c = new Compte(0, compte, nom, prenom, solde);
			pdao.add(c);
		}
	}
}
