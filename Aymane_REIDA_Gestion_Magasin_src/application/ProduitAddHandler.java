package application;

import java.sql.Date;
import java.time.LocalDate;

import daofx.IProduitDAO;
import daofx.ProduitDaoImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import daofx.Produit;

public class ProduitAddHandler {
	IProduitDAO pdao = new ProduitDaoImpl();
	ProduitFormWindow formProduit = null;

	public ProduitAddHandler(ProduitFormWindow formProduit) {
		this.formProduit = formProduit;
	}

	public void addClick() {
		String designation = formProduit.produitDesignationTextField.getText();
		int qteStock = Integer.valueOf(formProduit.produitQteTextField.getText());
		double prixAchat = Double.valueOf(formProduit.produitPrixAchatTextField.getText());
		double prixVente = Double.valueOf(formProduit.produitPrixVenteTextField.getText());
		LocalDate date = formProduit.produitDateSaisieDatePicker.getValue();
		// Date datesql = Date.valueOf(date);
		if (!designation.equals("") && date != null) {
			Produit p = new Produit(0, designation, qteStock, prixAchat, prixVente, date);
			pdao.add(p);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Ajouter un élément");
			alert.setHeaderText(null);
			alert.setContentText("Un nouveau élément a été ajouté");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(formProduit.window);

			alert.showAndWait();
		}
	}
}
