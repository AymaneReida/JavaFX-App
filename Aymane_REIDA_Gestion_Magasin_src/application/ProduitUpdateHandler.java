package application;

import java.time.LocalDate;

import daofx.IProduitDAO;
import daofx.ProduitDaoImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import daofx.Produit;

public class ProduitUpdateHandler {
	IProduitDAO pdao = new ProduitDaoImpl();
	ProduitUpdateWindow updateProduit = null;

	public ProduitUpdateHandler(ProduitUpdateWindow updateProduit) {
		this.updateProduit = updateProduit;
	}

	public void updateClick() {
		long id = Long.valueOf(updateProduit.produitIdTextField.getText());
		String designation = updateProduit.produitDesignationTextField.getText();
		int qteStock = Integer.valueOf(updateProduit.produitQteTextField.getText());
		double prixAchat = Double.valueOf(updateProduit.produitPrixAchatTextField.getText());
		double prixVente = Double.valueOf(updateProduit.produitPrixVenteTextField.getText());
		LocalDate date = updateProduit.produitDateSaisieDatePicker.getValue();
		// Date datesql = Date.valueOf(date);
		if (!designation.equals("") && date != null) {
			Produit p = new Produit(id, designation, qteStock, prixAchat, prixVente, date);
			pdao.update(p);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Modifier un élément");
			alert.setHeaderText(null);
			alert.setContentText("Un élément a été modifié");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(updateProduit.window);

			alert.showAndWait();
		}
	}
}
