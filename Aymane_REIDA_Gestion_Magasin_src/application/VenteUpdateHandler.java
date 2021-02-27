package application;

import java.time.LocalDate;
import java.util.List;

import daofx.IVenteDAO;
import daofx.VenteDaoImpl;
import daofx.Vente;

public class VenteUpdateHandler {
	IVenteDAO pdao = new VenteDaoImpl();
	VenteUpdateWindow updateVente = null;

	public VenteUpdateHandler(VenteUpdateWindow updateVente) {
		this.updateVente = updateVente;
	}

	public void updateClick() {
		long id = Long.valueOf(updateVente.venteIdTextField.getText());
		LocalDate date = updateVente.venteDateSaisieDatePicker.getValue();
		if (date != null) {
			Vente v = pdao.getOne(id);
			v.setDate(date);
			new LignesCommandeUpdateFormWindow(v);
		}
	}

	public void updateVentesListWindows() {
		List<Vente> list = pdao.getAll();
		updateVente.ventesObservableList.addAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		if (updateVente.ventesObservableList.isEmpty()) {
			updateVente.totalLabelValue.setText(total + "");
		} else {
			for (Vente v : updateVente.ventesObservableList) {
				total += v.getTotal();
				updateVente.totalLabelValue.setText(total + "");
			}
		}
	}
}
