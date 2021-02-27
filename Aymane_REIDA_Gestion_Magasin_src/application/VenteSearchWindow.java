package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VenteSearchWindow {
	VenteSearchHandler handler = new VenteSearchHandler(this);
	VBox root = new VBox();
	HBox buttonsBox = new HBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Rechercher une vente");
	
	Label venteIdLabel = new Label("Id:");
	TextField venteIdTextField = new TextField();
	Button venteSearchButton = new Button("Rechercher");
	Button venteCancelButton = new Button("Annuler");
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("Rechercher une vente");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void addNodesToWindow() {
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(venteIdLabel, venteIdTextField);
		buttonsBox.getChildren().addAll(venteSearchButton, venteCancelButton);
		root.getChildren().addAll(buttonsBox);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		venteIdLabel.getStyleClass().add("labelForm");
		root.setSpacing(15);
		buttonsBox.setSpacing(20);
	}
	
	private void addEvents() {
		venteCancelButton.setOnAction(event -> {
			window.close();
		});
		venteSearchButton.setOnAction(event -> {
			//System.out.println("Traitement pour ajouter");
			handler.searchClick();
		});
		window.setOnCloseRequest(event -> {
			event.consume();
		});
	}

	public VenteSearchWindow() {
		initWindow();
		addStylesToNodes();
		addNodesToWindow();
		addEvents();
		window.show();
	}
}
