package application;

import java.util.List;

import daofx.IProduitDAO;
import daofx.ProduitDaoImpl;
import daofx.Produit;

public class ProduitSearchListHandler {
	ProduitSearchListWindow searchListWindow = null;
	IProduitDAO pdao = new ProduitDaoImpl();
	String designation;

	public void setSearchListWindow(ProduitSearchListWindow searchListWindow) {
		this.searchListWindow = searchListWindow;
	}

	public ProduitSearchListHandler(String designation) {
		this.designation = designation;
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updateProduitsSearchListWindows() {
		List<Produit> list = pdao.getAll(designation);
		searchListWindow.produitsObservableList.addAll(list);
		calculerNombre();
	}

	private void calculerNombre() {
		int nombre = 0;
		if (searchListWindow.produitsObservableList.isEmpty()) {
			searchListWindow.nombreLabelValue.setText(nombre + "");
		} else {
			for (Produit p : searchListWindow.produitsObservableList) {
				nombre++;
				searchListWindow.nombreLabelValue.setText(nombre + "");
			}
		}
	}
}
