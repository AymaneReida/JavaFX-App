package application;

import java.time.LocalDate;

import daofx.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ClientDeleteWindow {
	ClientDeleteHandler handler = new ClientDeleteHandler(this);
	VBox root = new VBox();
	HBox buttonsBox = new HBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Supprimer un client");
	int id = 0;
	HBox nombreHbox = new HBox();

	Label nombreLabel = new Label("Nombre:");
	Label nombreLabelValue = new Label("0");

	TextField cleintIdTextField = new TextField();
	Button cleintDeleteButton = new Button("Supprimer");
	Button cleintCancelButton = new Button("Annuler");
	
	TableColumn<Client, Long> idColumn = new TableColumn<>("Id");
	TableColumn<Client, String> nomColumn = new TableColumn<>("Nom");
	TableColumn<Client, String> prenomColumn = new TableColumn<>("Prenom");
	TableColumn<Client, String> telephoneColumn = new TableColumn<>("Telephone");
	TableColumn<Client, String> emailColumn = new TableColumn<>("Email");
	TableColumn<Client, String> adresseColumn = new TableColumn<>("Adresse");
	TableColumn<Client, LocalDate> dateColumn = new TableColumn<>("Date");

	TableView<Client> clientsTableView = new TableView<>();
	ObservableList<Client> clientsObservableList = FXCollections.observableArrayList();

	private void addColumnsToTableView() {
		clientsTableView.getColumns().addAll(idColumn, nomColumn, prenomColumn, telephoneColumn, emailColumn,
				adresseColumn, dateColumn);
		clientsTableView.setItems(clientsObservableList);
	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(650);
		window.setTitle("Supprimer un client");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}

	private void addNodesToWindow() {
		root.getChildren().add(titleLabel);
		nombreHbox.getChildren().addAll(nombreLabel, nombreLabelValue);
		root.getChildren().addAll(clientsTableView, nombreHbox);
		buttonsBox.getChildren().addAll(cleintDeleteButton, cleintCancelButton);
		root.getChildren().addAll(buttonsBox);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		root.setSpacing(15);
		buttonsBox.setSpacing(20);
		nombreLabel.getStyleClass().add("labelTotal");
		nombreLabelValue.getStyleClass().add("labelTotal");
		nombreHbox.getStyleClass().add("boxTotal");
		clientsTableView.getStyleClass().add("table-row-cell");
		clientsTableView.setMinHeight(430);
		clientsTableView.setOnMouseClicked(event -> {
			cleintIdTextField.setText(String.valueOf(clientsTableView.getSelectionModel().getSelectedItem().getId()));
			id = clientsTableView.getSelectionModel().getSelectedIndex();
		});
		nombreHbox.setSpacing(15);
	}
	
	private void updateColumns() {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setPrefWidth(100);
		nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		nomColumn.setPrefWidth(100);
		prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		prenomColumn.setPrefWidth(100);
		telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		telephoneColumn.setPrefWidth(150);
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailColumn.setPrefWidth(150);
		adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		adresseColumn.setPrefWidth(285);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateColumn.setPrefWidth(100);
	}

	private void addEvents() {
		cleintCancelButton.setOnAction(event -> {
			window.close();
		});
		cleintDeleteButton.setOnAction(event -> {
			// System.out.println("Traitement pour ajouter");
			handler.deleteClick(id);
		});
		window.setOnCloseRequest(event -> {
			event.consume();
		});
	}

	public ClientDeleteWindow() {
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		handler.updateClientsListWindows();
		addNodesToWindow();
		addEvents();
		window.show();
	}
}
