package application;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import daofx.IClientDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import daofx.ClientDaoImpl;
import daofx.Client;

public class ClientDeleteHandler {
	IClientDAO pdao = new ClientDaoImpl();
	ClientDeleteWindow deleteClient = null;

	public ClientDeleteHandler(ClientDeleteWindow deleteClient) {
		this.deleteClient = deleteClient;
	}

	public void deleteClick(int id) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Voulez vous vraiment supprimer cet élément?");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(deleteClient.window);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			pdao.delete(Long.valueOf(deleteClient.cleintIdTextField.getText()));
			deleteClient.clientsObservableList.remove(id);
			calculerNombre();
		} else {
		    alert.close();
		}
	}

	public void updateClientsListWindows() {
		IClientDAO pdao = new ClientDaoImpl();
		List<Client> list = pdao.getAll();
		deleteClient.clientsObservableList.addAll(list);
		calculerNombre();
	}

	private void calculerNombre() {
		int nombre = 0;
		if (deleteClient.clientsObservableList.isEmpty()) {
			deleteClient.nombreLabelValue.setText(nombre + "");
		} else {
			for (Client c : deleteClient.clientsObservableList) {
				nombre++;
				deleteClient.nombreLabelValue.setText(nombre + "");
			}
		}
	}
}
