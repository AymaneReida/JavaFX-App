package application;

import daofx.IProduitDAO;
import daofx.ProduitDaoImpl;

public class ProduitSearchHandler {
	IProduitDAO pdao = new ProduitDaoImpl();
	ProduitSearchWindow searchProduit = null;

	public ProduitSearchHandler(ProduitSearchWindow searchProduit) {
		this.searchProduit = searchProduit;
	}
	
	public void searchClick() {
		String designation = searchProduit.produitDesignationTextField.getText();
		ProduitSearchListHandler searchListProduit = new ProduitSearchListHandler(designation);
		ProduitSearchListWindow searchListWindow = new ProduitSearchListWindow(searchListProduit);
	}
}
