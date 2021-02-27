package application;

import java.util.List;

import daofx.IClientDAO;
import daofx.ClientDaoImpl;
import daofx.Client;

public class ClientSearchListHandler {
	ClientSearchListWindow searchListWindow = null;
	IClientDAO pdao = new ClientDaoImpl();
	String nom;
	String prenom;

	public void setSearchListWindow(ClientSearchListWindow searchListWindow) {
		this.searchListWindow = searchListWindow;
	}

	public ClientSearchListHandler(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updateClientsSearchListWindows() {
		List<Client> list = pdao.getAll(nom, prenom);
		searchListWindow.clientsObservableList.addAll(list);
		calculerNombre();
	}

	private void calculerNombre() {
		int nombre = 0;
		if (searchListWindow.clientsObservableList.isEmpty()) {
			searchListWindow.nombreLabelValue.setText(nombre + "");
		} else {
			for (Client c : searchListWindow.clientsObservableList) {
				nombre++;
				searchListWindow.nombreLabelValue.setText(nombre + "");
			}
		}
	}
}
