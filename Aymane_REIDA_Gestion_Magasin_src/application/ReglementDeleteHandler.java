package application;

import java.util.List;

import daofx.IVenteDAO;
import daofx.VenteDaoImpl;
import daofx.Vente;

public class ReglementDeleteHandler {
	ReglementDeleteWindow deleteListWindow = null;

	public ReglementDeleteHandler(ReglementDeleteWindow deleteListWindow) {
		this.deleteListWindow = deleteListWindow;
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updateVentesListWindows() {
		IVenteDAO pdao = new VenteDaoImpl();
		List<Vente> list = pdao.getAll();
		deleteListWindow.ventesObservableList.addAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		if (deleteListWindow.ventesObservableList.isEmpty()) {
			deleteListWindow.totalLabelValue.setText(total + "");
		} else {
			for (Vente v : deleteListWindow.ventesObservableList) {
				total += v.getTotal();
				deleteListWindow.totalLabelValue.setText(total + "");
			}
		}
	}
}
