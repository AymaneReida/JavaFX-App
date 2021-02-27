package application;

import java.time.LocalDate;

import daofx.IClientDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import daofx.ClientDaoImpl;
import daofx.Client;

public class ClientUpdateHandler {
	IClientDAO pdao = new ClientDaoImpl();
	ClientUpdateWindow updateClient = null;

	public ClientUpdateHandler(ClientUpdateWindow updateClient) {
		this.updateClient = updateClient;
	}

	public void updateClick() {
		Long id = Long.valueOf(updateClient.clientIdTextField.getText());
		String nom = updateClient.clientNomTextField.getText();
		String prenom = updateClient.clientPrenomTextField.getText();
		String telephone = updateClient.clientTelephoneTextField.getText();
		String email = updateClient.clientEmailTextField.getText();
		String adresse = updateClient.clientAdresseTextField.getText();
		LocalDate date = updateClient.clientDateSaisieDatePicker.getValue();
		// Date datesql = Date.valueOf(date);
		if (!nom.equals("") && !prenom.equals("") && !telephone.equals("") && !email.equals("") && !adresse.equals("")
				&& date != null) {
			Client c = new Client(id, nom, prenom, telephone, email, adresse, date);
			pdao.update(c);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Modifier un élément");
			alert.setHeaderText(null);
			alert.setContentText("Un élément a été modifié");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(updateClient.window);

			alert.showAndWait();
		}
	}
}
