package application;

import daofx.IVenteDAO;
import daofx.VenteDaoImpl;

public class VenteSearchHandler {
	IVenteDAO pdao = new VenteDaoImpl();
	VenteSearchWindow searchVente = null;

	public VenteSearchHandler(VenteSearchWindow searchVente) {
		this.searchVente = searchVente;
	}
	
	public void searchClick() {
		long id = Long.valueOf(searchVente.venteIdTextField.getText());
		VenteSearchListHandler searchListVente = new VenteSearchListHandler(id);
		VenteSearchListWindow searchListWindow = new VenteSearchListWindow(searchListVente);
	}
}
