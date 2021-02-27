package application;

import java.sql.Date;
import java.time.LocalDate;

import daofx.IClientDAO;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import daofx.ClientDaoImpl;
import daofx.Client;

public class ClientAddHandler {
	IClientDAO pdao = new ClientDaoImpl();
	ClientFormWindow formClient = null;

	public ClientAddHandler(ClientFormWindow formClient) {
		this.formClient = formClient;
	}

	public void addClick() {
		String nom = formClient.clientNomTextField.getText();
		String prenom = formClient.clientPrenomTextField.getText();
		String telephone = formClient.clientTelephoneTextField.getText();
		String email = formClient.clientEmailTextField.getText();
		String adresse = formClient.clientAdresseTextField.getText();
		LocalDate date = formClient.clientDateSaisieDatePicker.getValue();
		// Date datesql = Date.valueOf(date);
		if (!nom.equals("") && !prenom.equals("") && !telephone.equals("") && !email.equals("") && !adresse.equals("")
				&& date != null) {
			Client c = new Client(0, nom, prenom, telephone, email, adresse, date);
			pdao.add(c);
			if (!((ClientDaoImpl) pdao).getNotConnected()
					.equals("Error vous n'êtes pas connecté à la base de donnée")) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ajouter un élément");
				alert.setHeaderText(null);
				alert.setContentText("Un nouveau élément a été ajouté");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.initOwner(formClient.window);

				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Connectez vous à la base de donnée");
				alert.setContentText("Error vous n'êtes pas connecté à la base de donnée");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.initOwner(formClient.window);

				alert.showAndWait();
			}
		}
	}
}
