package application;

import java.time.LocalDate;

import daofx.Produit;
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

public class ProduitDeleteWindow {
	ProduitDeleteHandler handler = new ProduitDeleteHandler(this);
	VBox root = new VBox();
	HBox buttonsBox = new HBox();
	Scene scene = new Scene(root);
	Stage window = new Stage();
	Label titleLabel = new Label("Supprimer un produit");
	int id = 0;
	HBox totalHbox = new HBox();

	Label nombreLabel = new Label("Nombre:");
	Label nombreLabelValue = new Label("0");

	TextField produitIdTextField = new TextField();
	Button produitDeleteButton = new Button("Supprimer");
	Button produitCancelButton = new Button("Annuler");
	
	TableColumn<Produit, Long> idColumn = new TableColumn<>("Id");
	TableColumn<Produit, String> designationColumn = new TableColumn<>("Designation");
	TableColumn<Produit, Integer> qteColumn = new TableColumn<>("Quantité en stock");
	TableColumn<Produit, Double> prixAchatColumn = new TableColumn<>("Prix achat");
	TableColumn<Produit, Integer> prixVenteColumn = new TableColumn<>("Prix vente");
	TableColumn<Produit, LocalDate> dateColumn = new TableColumn<>("Date");

	TableView<Produit> produitsTableView = new TableView<>();
	ObservableList<Produit> produitsObservableList = FXCollections.observableArrayList();

	private void addColumnsToTableView() {
		produitsTableView.getColumns().addAll(idColumn, designationColumn, qteColumn, prixAchatColumn, prixVenteColumn, dateColumn);
		produitsTableView.setItems(produitsObservableList);
	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(650);
		window.setTitle("Supprimer un produit");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}

	private void addNodesToWindow() {
		root.getChildren().add(titleLabel);
		totalHbox.getChildren().addAll(nombreLabel, nombreLabelValue);
		root.getChildren().addAll(produitsTableView, totalHbox);
		buttonsBox.getChildren().addAll(produitDeleteButton, produitCancelButton);
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
		totalHbox.getStyleClass().add("boxTotal");
		produitsTableView.getStyleClass().add("table-row-cell");
		produitsTableView.setMinHeight(430);
		produitsTableView.setOnMouseClicked(event -> {
			produitIdTextField.setText(String.valueOf(produitsTableView.getSelectionModel().getSelectedItem().getId()));
			id = produitsTableView.getSelectionModel().getSelectedIndex();
		});
		totalHbox.setSpacing(15);
	}
	
	private void updateColumns() {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setPrefWidth(100);
		designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
		designationColumn.setPrefWidth(300);
		qteColumn.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
		qteColumn.setPrefWidth(160);
		prixAchatColumn.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
		prixAchatColumn.setPrefWidth(170);
		prixVenteColumn.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
		prixVenteColumn.setPrefWidth(170);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateColumn.setPrefWidth(100);
	}

	private void addEvents() {
		produitCancelButton.setOnAction(event -> {
			window.close();
		});
		produitDeleteButton.setOnAction(event -> {
			// System.out.println("Traitement pour ajouter");
			handler.deleteClick(id);
		});
		window.setOnCloseRequest(event -> {
			event.consume();
		});
	}

	public ProduitDeleteWindow() {
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		handler.updateProduitsListWindows();
		addNodesToWindow();
		addEvents();
		window.show();
	}
}
