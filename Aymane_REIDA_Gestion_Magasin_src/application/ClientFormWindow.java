package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientFormWindow {
	ClientAddHandler handler = new ClientAddHandler(this);
	VBox root = new VBox();
	HBox buttonsBox = new HBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Nouveau client");

	Label clientNomLabel = new Label("Nom:");
	TextField clientNomTextField = new TextField();
	Label clientPrenomLabel = new Label("Prenom:");
	TextField clientPrenomTextField = new TextField();
	Label clientTelephoneLabel = new Label("Telephone:");
	TextField clientTelephoneTextField = new TextField();
	Label clientEmailLabel = new Label("Email:");
	TextField clientEmailTextField = new TextField();
	Label clientAdresseLabel = new Label("Adresse:");
	TextField clientAdresseTextField = new TextField();
	Label clientDateLabel = new Label("Date saisie:");
	DatePicker clientDateSaisieDatePicker = new DatePicker();
	Button clientAddButton = new Button("Ajouter");
	Button clientCancelButton = new Button("Annuler");

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("Nouveau client");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}

	private void addNodesToWindow() {
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(clientNomLabel, clientNomTextField, clientPrenomLabel, clientPrenomTextField,
				clientTelephoneLabel, clientTelephoneTextField, clientEmailLabel, clientEmailTextField,
				clientAdresseLabel, clientAdresseTextField, clientDateLabel, clientDateSaisieDatePicker);
		buttonsBox.getChildren().addAll(clientAddButton, clientCancelButton);
		root.getChildren().addAll(buttonsBox);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		clientNomLabel.getStyleClass().add("labelForm");
		clientPrenomLabel.getStyleClass().add("labelForm");
		clientTelephoneLabel.getStyleClass().add("labelForm");
		clientEmailLabel.getStyleClass().add("labelForm");
		clientAdresseLabel.getStyleClass().add("labelForm");
		clientDateLabel.getStyleClass().add("labelForm");
		root.setSpacing(15);
		buttonsBox.setSpacing(20);
	}

	private void addEvents() {
		clientCancelButton.setOnAction(event -> {
			window.close();
		});
		clientAddButton.setOnAction(event -> {
			// System.out.println("Traitement pour ajouter");
			handler.addClick();
		});
		window.setOnCloseRequest(event -> {
			event.consume();
		});
	}

	public ClientFormWindow() {
		initWindow();
		addStylesToNodes();
		addNodesToWindow();
		addEvents();
		window.show();
	}
}
