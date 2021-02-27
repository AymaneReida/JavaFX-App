package application;

import java.time.LocalDate;

import daofx.Client;
import daofx.LigneCommande;
import daofx.Produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VenteFormWindow {
	ClientsListHandler handler = new ClientsListHandler(this);
	VenteAddHandler addHandler = new VenteAddHandler(this);
	VBox root = new VBox();
	HBox boot = new HBox();
	HBox buttonsBox = new HBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Nouvelle vente");
	Label venteClientsLabel = new Label("Liste des clients");

	Label venteClientsIdLabel = new Label("Id du client:");
	TextField venteClientsIdTextField = new TextField();
	Label venteDateLabel = new Label("Date saisie:");
	DatePicker venteDateSaisieDatePicker = new DatePicker();
	Button venteAddButton = new Button("Ajouter");
	Button venteCancelButton = new Button("Annuler");

	TableColumn<Client, Long> idClientColumn = new TableColumn<>("Id");
	TableColumn<Client, String> nomClientColumn = new TableColumn<>("Nom");
	TableColumn<Client, String> prenomClientColumn = new TableColumn<>("Prenom");
	TableColumn<Client, String> telephoneClientColumn = new TableColumn<>("Telephone");
	TableColumn<Client, String> emailClientColumn = new TableColumn<>("Email");
	TableColumn<Client, String> adresseClientColumn = new TableColumn<>("Adresse");
	TableColumn<Client, LocalDate> dateClientColumn = new TableColumn<>("Date");

	TableView<Client> clientsTableView = new TableView<>();
	ObservableList<Client> clientsObservableList = FXCollections.observableArrayList();

	private void addColumnsToTableView() {
		clientsTableView.getColumns().addAll(idClientColumn, nomClientColumn, prenomClientColumn, telephoneClientColumn,
				emailClientColumn, adresseClientColumn, dateClientColumn);
		clientsTableView.setItems(clientsObservableList);
	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(650);
		window.setTitle("Nouvelle vente");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}

	private void addNodesToWindow() {
		boot.getChildren().addAll(venteClientsIdLabel, venteClientsIdTextField, venteDateLabel,
				venteDateSaisieDatePicker);
		root.getChildren().addAll(titleLabel, boot, venteClientsLabel, clientsTableView);
		buttonsBox.getChildren().addAll(venteAddButton, venteCancelButton);
		root.getChildren().addAll(buttonsBox);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		venteClientsLabel.getStyleClass().add("labelTitle");
		venteClientsLabel.setMinWidth(window.getWidth());
		venteClientsIdLabel.getStyleClass().add("labelForm");
		venteDateLabel.getStyleClass().add("labelForm");
		root.setSpacing(15);
		boot.setSpacing(15);
		buttonsBox.setSpacing(20);
		clientsTableView.getStyleClass().add("table-row-cellt");
		clientsTableView.setOnMouseClicked(event -> venteClientsIdTextField
				.setText(String.valueOf(clientsTableView.getSelectionModel().getSelectedItem().getId())));
		clientsTableView.setMinHeight(100);
	}

	private void updateColumns() {
		idClientColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idClientColumn.setPrefWidth(100);
		nomClientColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		nomClientColumn.setPrefWidth(100);
		prenomClientColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		prenomClientColumn.setPrefWidth(100);
		telephoneClientColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		telephoneClientColumn.setPrefWidth(150);
		emailClientColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailClientColumn.setPrefWidth(150);
		adresseClientColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		adresseClientColumn.setPrefWidth(285);
		dateClientColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateClientColumn.setPrefWidth(100);
	}

	private void addEvents() {
		venteCancelButton.setOnAction(event -> {
			window.close();
		});
		venteAddButton.setOnAction(event -> {
			addHandler.addClick();
		});
		window.setOnCloseRequest(event -> {
			event.consume();
		});
	}

	public VenteFormWindow() {
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		handler.updateVenteClientsListWindows();
		addNodesToWindow();
		addEvents();
		window.show();
	}
}
