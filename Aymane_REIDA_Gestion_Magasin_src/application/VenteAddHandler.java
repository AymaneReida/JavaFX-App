package application;

import java.sql.Date;
import java.time.LocalDate;

import daofx.IVenteDAO;
import daofx.VenteDaoImpl;
import daofx.Vente;

public class VenteAddHandler {
	IVenteDAO pdao = new VenteDaoImpl();
	VenteFormWindow formVente = null;

	public VenteAddHandler(VenteFormWindow formVente) {
		this.formVente = formVente;
	}

	public void addClick() {
		long id = 0;
		long id_client = Long.valueOf(formVente.venteClientsIdTextField.getText());
		LocalDate date = formVente.venteDateSaisieDatePicker.getValue();
		if (date != null) {
			Vente v = new Vente(0, date, id_client);
			id = ((VenteDaoImpl) pdao).addVente(v);
			v.setId(id);
			new LignesCommandeFormWindow(v);
		}
	}
}
