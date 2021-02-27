package application;

import java.util.List;

import daofx.IVenteDAO;
import daofx.VenteDaoImpl;
import daofx.Vente;

public class ReglementsListHandler {
	ReglementsListWindow listWindow = null;

	public ReglementsListHandler(ReglementsListWindow listWindow) {
		this.listWindow = listWindow;
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updateVentesListWindows() {
		IVenteDAO pdao = new VenteDaoImpl();
		List<Vente> list = pdao.getAll();
		listWindow.ventesObservableList.addAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		if (listWindow.ventesObservableList.isEmpty()) {
			listWindow.totalLabelValue.setText(total + "");
		} else {
			for (Vente v : listWindow.ventesObservableList) {
				total += v.getTotal();
				listWindow.totalLabelValue.setText(total + "");
			}
		}
	}
}
