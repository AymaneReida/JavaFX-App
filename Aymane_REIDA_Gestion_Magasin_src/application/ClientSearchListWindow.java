package application;

import java.time.LocalDate;

import daofx.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientSearchListWindow {
	ClientSearchListHandler handler = null;
	//SearchProduitHandler handlerShow = new SearchProduitHandler(this);
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Liste des clients recherchés");
	HBox nombreHbox = new HBox();

	Label nombreLabel = new Label("Nombre:");
	Label nombreLabelValue = new Label("0");

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
	
	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		nombreLabel.getStyleClass().add("labelTotal");
		nombreLabelValue.getStyleClass().add("labelTotal");
		nombreHbox.getStyleClass().add("boxTotal");
		clientsTableView.getStyleClass().add("table-row-cell");
		clientsTableView.setMinHeight(500);
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

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(650);
		window.setTitle("Liste des clients recherchés");
		window.getIcons().add(new Image("file:icon.png"));
	}

	private void addNodesToPane() {
		nombreHbox.getChildren().addAll(nombreLabel, nombreLabelValue);
		root.getChildren().addAll(titleLabel, clientsTableView, nombreHbox);
	}

	public ClientSearchListWindow(ClientSearchListHandler handler) {
		//handler.setListWindow(this);
		this.handler = handler;
		this.handler.setSearchListWindow(this);
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		this.handler.updateClientsSearchListWindows();
		addNodesToPane();
		window.show();
	}
}
