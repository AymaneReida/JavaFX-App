package application;

import java.util.List;

import daofx.IProduitDAO;
import daofx.ProduitDaoImpl;
import daofx.Produit;

public class ProduitsListHandler {
	ProduitsListWindow listWindow = null;
	LignesCommandeFormWindow ligneCommandeListWindow = null;
	LignesCommandeUpdateFormWindow ligneCommandeUpdateListWindow = null;

	public ProduitsListHandler(ProduitsListWindow listWindow) {
		this.listWindow = listWindow;
	}

	public ProduitsListHandler(LignesCommandeFormWindow ligneCommandeListWindow) {
		this.ligneCommandeListWindow = ligneCommandeListWindow;
	}

	public ProduitsListHandler(LignesCommandeUpdateFormWindow ligneCommandeUpdateListWindow) {
		this.ligneCommandeUpdateListWindow = ligneCommandeUpdateListWindow;
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updateProduitsListWindows() {
		/*
		 * List<Produit> list = new ArrayList<>(); list.add(new Produit(1, "Galaxy S5",
		 * 5, 5500, LocalDate.now())); list.add(new Produit(2, "Galaxy S6", 5, 6500,
		 * LocalDate.now())); list.add(new Produit(3, "Galaxy S7", 6, 7500,
		 * LocalDate.now())); list.add(new Produit(4, "Galaxy S8", 6, 8500,
		 * LocalDate.now())); list.add(new Produit(5, "Galaxy S9", 7, 9500,
		 * LocalDate.now()));
		 */
		IProduitDAO pdao = new ProduitDaoImpl();
		List<Produit> list = pdao.getAll();
		listWindow.produitsObservableList.addAll(list);
		calculerNombre();
	}

	public void updateLigneCommandeProduitsListWindows() {
		IProduitDAO pdao = new ProduitDaoImpl();
		List<Produit> list = ((ProduitDaoImpl) pdao).getLigneCommandeAll();
		ligneCommandeListWindow.produitsObservableList.addAll(list);
	}

	public void updateLigneCommandeProduitsUpdateListWindows() {
		IProduitDAO pdao = new ProduitDaoImpl();
		List<Produit> list = ((ProduitDaoImpl) pdao).getLigneCommandeAll();
		ligneCommandeUpdateListWindow.produitsObservableList.addAll(list);
	}

	private void calculerNombre() {
		int nombre = 0;
		if (listWindow.produitsObservableList.isEmpty()) {
			listWindow.nombreLabelValue.setText(nombre + "");
		} else {
			for (Produit p : listWindow.produitsObservableList) {
				nombre++;
				listWindow.nombreLabelValue.setText(nombre + "");
			}
		}
	}
}
