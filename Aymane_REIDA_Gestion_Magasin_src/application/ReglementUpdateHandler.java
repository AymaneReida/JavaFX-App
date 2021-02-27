package application;

import java.time.LocalDate;
import java.util.List;

import daofx.IVenteDAO;
import daofx.VenteDaoImpl;
import daofx.Vente;

public class ReglementUpdateHandler {
	IVenteDAO pdao = new VenteDaoImpl();
	ReglementUpdateWindow updateReglement = null;

	public ReglementUpdateHandler(ReglementUpdateWindow updateReglement) {
		this.updateReglement = updateReglement;
	}

	public void updateClick() {
		long id = Long.valueOf(updateReglement.venteIdTextField.getText());
		new PaiementsUpdateWindow(id, updateReglement);
	}

	public void updateVentesListWindows() {
		List<Vente> list = pdao.getAll();
		updateReglement.ventesObservableList.addAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		if (updateReglement.ventesObservableList.isEmpty()) {
			updateReglement.totalLabelValue.setText(total + "");
		} else {
			for (Vente v : updateReglement.ventesObservableList) {
				total += v.getTotal();
				updateReglement.totalLabelValue.setText(total + "");
			}
		}
	}
}
