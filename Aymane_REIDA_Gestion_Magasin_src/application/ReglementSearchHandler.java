package application;

import daofx.IVenteDAO;
import daofx.VenteDaoImpl;

public class ReglementSearchHandler {
	IVenteDAO pdao = new VenteDaoImpl();
	ReglementSearchWindow searchVente = null;

	public ReglementSearchHandler(ReglementSearchWindow searchVente) {
		this.searchVente = searchVente;
	}
	
	public void searchClick() {
		long id = Long.valueOf(searchVente.venteIdTextField.getText());
		ReglementSearchListHandler searchListVente = new ReglementSearchListHandler(id);
		ReglementSearchListWindow searchListWindow = new ReglementSearchListWindow(searchListVente);
	}
}
