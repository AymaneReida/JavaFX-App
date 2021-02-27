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

public class ProduitSearchWindow {
	ProduitSearchHandler handler = new ProduitSearchHandler(this);
	VBox root = new VBox();
	HBox buttonsBox = new HBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Rechercher un produit");
	
	Label produitDesignationLabel = new Label("Designation:");
	TextField produitDesignationTextField = new TextField();
	Button produitSearchButton = new Button("Rechercher");
	Button produitCancelButton = new Button("Annuler");
	
	private void initWindow() {
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("Rechercher un produit");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void addNodesToWindow() {
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(produitDesignationLabel, produitDesignationTextField);
		buttonsBox.getChildren().addAll(produitSearchButton, produitCancelButton);
		root.getChildren().addAll(buttonsBox);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		produitDesignationLabel.getStyleClass().add("labelForm");
		root.setSpacing(15);
		buttonsBox.setSpacing(20);
	}
	
	private void addEvents() {
		produitCancelButton.setOnAction(event -> {
			window.close();
		});
		produitSearchButton.setOnAction(event -> {
			//System.out.println("Traitement pour ajouter");
			handler.searchClick();
		});
		window.setOnCloseRequest(event -> {
			event.consume();
		});
	}

	public ProduitSearchWindow() {
		initWindow();
		addStylesToNodes();
		addNodesToWindow();
		addEvents();
		window.show();
	}
}
