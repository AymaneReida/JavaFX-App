package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import daofx.IPaiementDAO;
import daofx.IVenteDAO;
import daofx.PaiementDaoImpl;
import daofx.VenteDaoImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import daofx.Paiement;
import daofx.Vente;

public class PaiementsDeleteListHandler {
	IPaiementDAO pdao = new PaiementDaoImpl();
	IVenteDAO pdaoVente = new VenteDaoImpl();
	PaiementsDeleteListWindow deleteListWindow = null;
	ReglementDeleteWindow reglementDeleteListWindow = null;
	long idVente = 0;

	public PaiementsDeleteListHandler(PaiementsDeleteListWindow deleteListWindow) {
		this.deleteListWindow = deleteListWindow;
	}

	public void setReglementDeleteListWindow(ReglementDeleteWindow reglementDeleteListWindow) {
		this.reglementDeleteListWindow = reglementDeleteListWindow;
	}

	public void setIdVente(long idVente) {
		this.idVente = idVente;
	}

	public void deleteClick(int id) {
		if (!deleteListWindow.paiementsObservableList.get(id).getType().equals("CARTE BANCAIRE")) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Voulez vous vraiment supprimer cet élément?");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(deleteListWindow.window);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				pdao.delete(Long.valueOf(deleteListWindow.paiementIdTextField.getText()));
				deleteListWindow.paiementsObservableList.remove(id);
				calculerTotal();
				Vente v = pdaoVente.getOne(this.idVente);
				v.setPaye(Double.valueOf(deleteListWindow.totalLabelValue.getText()));
				pdaoVente.update(v);
				updateVentesListWindows();
			} else {
			    alert.close();
			}
		}
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updatePaiementsListWindows() {
		List<Paiement> list = pdao.getAll(idVente);
		deleteListWindow.paiementsObservableList.addAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		if (deleteListWindow.paiementsObservableList.isEmpty()) {
			deleteListWindow.totalLabelValue.setText(total + "");
		} else {
			for (Paiement p : deleteListWindow.paiementsObservableList) {
				total += p.getMontant();
				deleteListWindow.totalLabelValue.setText(total + "");
			}
		}
	}

	public void updateVentesListWindows() {
		List<Vente> list = new ArrayList<>();
		list = pdaoVente.getAll();
		reglementDeleteListWindow.ventesObservableList.setAll(list);
		updateTotal();
	}

	private void updateTotal() {
		double total = 0;
		if (reglementDeleteListWindow.ventesObservableList.isEmpty()) {
			reglementDeleteListWindow.totalLabelValue.setText(total + "");
		} else {
			for (Vente v : reglementDeleteListWindow.ventesObservableList) {
				total += v.getTotal();
				reglementDeleteListWindow.totalLabelValue.setText(total + "");
			}
		}
	}
}
