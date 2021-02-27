package application;

import java.util.List;

import daofx.IClientDAO;
import daofx.ClientDaoImpl;
import daofx.Client;

public class ClientsListHandler {
	ClientsListWindow listWindow = null;
	VenteFormWindow venteListWindow = null;

	public ClientsListHandler(VenteFormWindow venteListWindow) {
		this.venteListWindow = venteListWindow;
	}

	public ClientsListHandler(ClientsListWindow listWindow) {
		this.listWindow = listWindow;
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updateClientsListWindows() {
		IClientDAO pdao = new ClientDaoImpl();
		List<Client> list = pdao.getAll();
		listWindow.clientsObservableList.addAll(list);
		calculerNombre();
	}

	public void updateVenteClientsListWindows() {
		IClientDAO pdao = new ClientDaoImpl();
		List<Client> list = pdao.getAll();
		venteListWindow.clientsObservableList.addAll(list);
	}

	private void calculerNombre() {
		int nombre = 0;
		if (listWindow.clientsObservableList.isEmpty()) {
			listWindow.nombreLabelValue.setText(nombre + "");
		} else {
			for (Client c : listWindow.clientsObservableList) {
				nombre++;
				listWindow.nombreLabelValue.setText(nombre + "");
			}
		}
	}

}
