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

public class CompteFormWindow {
	CompteAddHandler handler = new CompteAddHandler(this);
	VBox root = new VBox();
	HBox buttonsBox = new HBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Nouveau compte");

	Label compteNomLabel = new Label("Nom:");
	TextField compteNomTextField = new TextField();
	Label comptePrenomLabel = new Label("Prenom:");
	TextField comptePrenomTextField = new TextField();
	Label compteNumeroLabel = new Label("Compte:");
	TextField compteNumeroTextField = new TextField();
	Label compteSoldeLabel = new Label("Solde:");
	TextField compteSoldeTextField = new TextField();
	Button compteAddButton = new Button("Créer le compte");

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("Nouveau compte");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}

	private void addNodesToWindow() {
		root.getChildren().add(titleLabel);
		root.getChildren().addAll(compteNomLabel, compteNomTextField, comptePrenomLabel, comptePrenomTextField,
				compteNumeroLabel, compteNumeroTextField, compteSoldeLabel, compteSoldeTextField);
		buttonsBox.getChildren().addAll(compteAddButton);
		root.getChildren().addAll(buttonsBox);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		compteNomLabel.getStyleClass().add("labelForm");
		comptePrenomLabel.getStyleClass().add("labelForm");
		compteNumeroLabel.getStyleClass().add("labelForm");
		compteSoldeLabel.getStyleClass().add("labelForm");
		root.setSpacing(15);
		buttonsBox.setSpacing(20);
	}

	private void addEvents() {
		compteAddButton.setOnAction(event -> {
			handler.addClick();
		});
	}

	public CompteFormWindow() {
		initWindow();
		addStylesToNodes();
		addNodesToWindow();
		addEvents();
		window.show();
	}
}
