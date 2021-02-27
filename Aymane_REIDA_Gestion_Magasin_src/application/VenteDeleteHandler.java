package application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import daofx.IVenteDAO;
import daofx.VenteDaoImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import daofx.Vente;

public class VenteDeleteHandler {
	IVenteDAO pdao = new VenteDaoImpl();
	VenteDeleteWindow deleteVente = null;

	public VenteDeleteHandler(VenteDeleteWindow deleteVente) {
		this.deleteVente = deleteVente;
	}

	public void deleteClick(int id) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Voulez vous vraiment supprimer cet élément?");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(deleteVente.window);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			pdao.delete(Long.valueOf(deleteVente.venteIdTextField.getText()));
			deleteVente.ventesObservableList.remove(id);
			calculerTotal();
		} else {
		    alert.close();
		}
	}

	public void updateVentesListWindows() {
		List<Vente> list = pdao.getAll();
		deleteVente.ventesObservableList.addAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		if (deleteVente.ventesObservableList.isEmpty()) {
			deleteVente.totalLabelValue.setText(total + "");
		} else {
			for (Vente v : deleteVente.ventesObservableList) {
				total += v.getTotal();
				deleteVente.totalLabelValue.setText(total + "");
			}
		}
	}
}
