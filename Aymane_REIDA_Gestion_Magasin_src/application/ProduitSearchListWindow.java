package application;

import java.time.LocalDate;

import daofx.Produit;
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

public class ProduitSearchListWindow {
	ProduitSearchListHandler handler = null;
	// SearchProduitHandler handlerShow = new SearchProduitHandler(this);
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Liste des produits recherchés");
	HBox totalHbox = new HBox();

	Label nombreLabel = new Label("Nombre:");
	Label nombreLabelValue = new Label("0");

	TableColumn<Produit, Long> idColumn = new TableColumn<>("Id");
	TableColumn<Produit, String> designationColumn = new TableColumn<>("Designation");
	TableColumn<Produit, Integer> qteColumn = new TableColumn<>("Quantité en stock");
	TableColumn<Produit, Double> prixAchatColumn = new TableColumn<>("Prix achat");
	TableColumn<Produit, Integer> prixVenteColumn = new TableColumn<>("Prix vente");
	TableColumn<Produit, LocalDate> dateColumn = new TableColumn<>("Date");

	TableView<Produit> produitsTableView = new TableView<>();
	ObservableList<Produit> produitsObservableList = FXCollections.observableArrayList();

	private void addColumnsToTableView() {
		produitsTableView.getColumns().addAll(idColumn, designationColumn, qteColumn, prixAchatColumn, prixVenteColumn,
				dateColumn);
		produitsTableView.setItems(produitsObservableList);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		nombreLabel.getStyleClass().add("labelTotal");
		nombreLabelValue.getStyleClass().add("labelTotal");
		totalHbox.getStyleClass().add("boxTotal");
		produitsTableView.getStyleClass().add("table-row-cell");
		produitsTableView.setMinHeight(500);
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

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(650);
		window.setTitle("Liste des produits recherchés");
		window.getIcons().add(new Image("file:icon.png"));
	}

	private void addNodesToPane() {
		totalHbox.getChildren().addAll(nombreLabel, nombreLabelValue);
		root.getChildren().addAll(titleLabel, produitsTableView, totalHbox);
	}

	public ProduitSearchListWindow(ProduitSearchListHandler handler) {
		// handler.setListWindow(this);
		this.handler = handler;
		this.handler.setSearchListWindow(this);
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		this.handler.updateProduitsSearchListWindows();
		addNodesToPane();
		window.show();
	}
}
