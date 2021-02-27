package application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import daofx.IProduitDAO;
import daofx.ProduitDaoImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import daofx.Produit;

public class ProduitDeleteHandler {
	IProduitDAO pdao = new ProduitDaoImpl();
	ProduitDeleteWindow deleteProduit = null;

	public ProduitDeleteHandler(ProduitDeleteWindow deleteProduit) {
		this.deleteProduit = deleteProduit;
	}

	public void deleteClick(int id) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Voulez vous vraiment supprimer cet élément?");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(deleteProduit.window);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			pdao.delete(Long.valueOf(deleteProduit.produitIdTextField.getText()));
			deleteProduit.produitsObservableList.remove(id);
			calculerNombre();
		} else {
		    alert.close();
		}
	}

	public void updateProduitsListWindows() {
		IProduitDAO pdao = new ProduitDaoImpl();
		List<Produit> list = pdao.getAll();
		deleteProduit.produitsObservableList.addAll(list);
		calculerNombre();
	}

	private void calculerNombre() {
		int nombre = 0;
		if (deleteProduit.produitsObservableList.isEmpty()) {
			deleteProduit.nombreLabelValue.setText(nombre + "");
		} else {
			for (Produit p : deleteProduit.produitsObservableList) {
				nombre++;
				deleteProduit.nombreLabelValue.setText(nombre + "");
			}
		}
	}
}
