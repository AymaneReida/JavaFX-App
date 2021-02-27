package application;

import daofx.IClientDAO;
import daofx.ClientDaoImpl;

public class ClientSearchHandler {
	IClientDAO pdao = new ClientDaoImpl();
	ClientSearchWindow searchClient = null;

	public ClientSearchHandler(ClientSearchWindow searchClient) {
		this.searchClient = searchClient;
	}
	
	public void searchClick() {
		String nom = searchClient.clientNomTextField.getText();
		String prenom = searchClient.clientPrenomTextField.getText();
		ClientSearchListHandler searchListProduit = new ClientSearchListHandler(nom, prenom);
		ClientSearchListWindow searchListWindow = new ClientSearchListWindow(searchListProduit);
	}
}
