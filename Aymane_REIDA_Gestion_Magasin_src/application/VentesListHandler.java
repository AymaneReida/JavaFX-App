package application;

import java.util.List;

import daofx.IVenteDAO;
import daofx.VenteDaoImpl;
import daofx.Vente;

public class VentesListHandler {
	VentesListWindow listWindow = null;

	public VentesListHandler(VentesListWindow listWindow) {
		this.listWindow = listWindow;
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updateVentesListWindows() {
		/*
		 * List<Produit> list = new ArrayList<>(); list.add(new Produit(1, "Galaxy S5",
		 * 5, 5500, LocalDate.now())); list.add(new Produit(2, "Galaxy S6", 5, 6500,
		 * LocalDate.now())); list.add(new Produit(3, "Galaxy S7", 6, 7500,
		 * LocalDate.now())); list.add(new Produit(4, "Galaxy S8", 6, 8500,
		 * LocalDate.now())); list.add(new Produit(5, "Galaxy S9", 7, 9500,
		 * LocalDate.now()));
		 */
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
