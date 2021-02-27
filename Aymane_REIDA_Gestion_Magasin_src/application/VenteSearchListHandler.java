package application;

import java.util.List;

import daofx.IVenteDAO;
import daofx.VenteDaoImpl;
import daofx.Vente;

public class VenteSearchListHandler {
	VenteSearchListWindow searchListWindow = null;
	IVenteDAO pdao = new VenteDaoImpl();
	long id;

	public void setSearchListWindow(VenteSearchListWindow searchListWindow) {
		this.searchListWindow = searchListWindow;
	}

	public VenteSearchListHandler(long id) {
		this.id = id;
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updateVentesSearchListWindows() {
		List<Vente> list = pdao.getAll(id);
		searchListWindow.ventesObservableList.addAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		if (searchListWindow.ventesObservableList.isEmpty()) {
			searchListWindow.totalLabelValue.setText(total + "");
		} else {
			for (Vente v : searchListWindow.ventesObservableList) {
				total += v.getTotal();
				searchListWindow.totalLabelValue.setText(total + "");
			}
		}
	}
}
