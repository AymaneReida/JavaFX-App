package application;

import java.time.LocalDate;
import java.util.List;

import daofx.IVenteDAO;
import daofx.VenteDaoImpl;
import daofx.Vente;

public class ReglementAddHandler {
	IVenteDAO pdao = new VenteDaoImpl();
	ReglementFormWindow formReglement = null;

	public ReglementAddHandler(ReglementFormWindow formReglement) {
		this.formReglement = formReglement;
	}

	public void addClick() {
		long id = Long.valueOf(formReglement.venteIdTextField.getText());
		new PaiementsFormWindow(id, formReglement);
	}

	public void updateVentesListWindows() {
		List<Vente> list = pdao.getAll();
		formReglement.ventesObservableList.setAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		if (formReglement.ventesObservableList.isEmpty()) {
			formReglement.totalLabelValue.setText(total + "");
		} else {
			for (Vente v : formReglement.ventesObservableList) {
				total += v.getTotal();
				formReglement.totalLabelValue.setText(total + "");
			}
		}
	}
}
