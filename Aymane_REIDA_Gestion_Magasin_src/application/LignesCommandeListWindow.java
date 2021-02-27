package application;

import java.time.LocalDate;

import daofx.LigneCommande;
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

public class LignesCommandeListWindow {
	LignesCommandeListHandler handler = new LignesCommandeListHandler(this);
	long idVente = 0;
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Lignes de commande");
	HBox totalHbox = new HBox();

	Label totalLabel = new Label("Total:");
	Label totalLabelValue = new Label("0.0");

	TableColumn<LigneCommande, Long> idLigneCommandeColumn = new TableColumn<>("Id");
	TableColumn<LigneCommande, String> designationProduitLigneCommandeColumn = new TableColumn<>("Designation");
	TableColumn<LigneCommande, String> prixProduitLigneCommandeColumn = new TableColumn<>("Prix");
	TableColumn<LigneCommande, Integer> qteVendueColumn = new TableColumn<>("Quantite");
	TableColumn<LigneCommande, Double> sousTotalColumn = new TableColumn<>("Sous total");

	TableView<LigneCommande> lignesCommandeTableView = new TableView<>();
	ObservableList<LigneCommande> lignesCommandeObservableList = FXCollections.observableArrayList();

	private void addColumnsToTableView() {
		lignesCommandeTableView.getColumns().addAll(idLigneCommandeColumn, designationProduitLigneCommandeColumn,
				prixProduitLigneCommandeColumn, qteVendueColumn, sousTotalColumn);
		lignesCommandeTableView.setItems(lignesCommandeObservableList);
	}
	
	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		totalLabel.getStyleClass().add("labelTotal");
		totalLabelValue.getStyleClass().add("labelTotal");
		totalHbox.getStyleClass().add("boxTotal");
		lignesCommandeTableView.getStyleClass().add("table-row-cell");
		lignesCommandeTableView.setMinHeight(500);
		totalHbox.setSpacing(15);
	}

	private void updateColumns() {
		idLigneCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idLigneCommandeColumn.setPrefWidth(122);
		designationProduitLigneCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
		designationProduitLigneCommandeColumn.setPrefWidth(330);
		prixProduitLigneCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
		prixProduitLigneCommandeColumn.setPrefWidth(161);
		qteVendueColumn.setCellValueFactory(new PropertyValueFactory<>("qte"));
		qteVendueColumn.setPrefWidth(163);
		sousTotalColumn.setCellValueFactory(new PropertyValueFactory<>("sousTotal"));
		sousTotalColumn.setPrefWidth(214);
	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(650);
		window.setTitle("Lignes de commande");
		window.getIcons().add(new Image("file:icon.png"));
	}

	private void addNodesToPane() {
		totalHbox.getChildren().addAll(totalLabel, totalLabelValue);
		root.getChildren().addAll(titleLabel, lignesCommandeTableView, totalHbox);
	}

	public LignesCommandeListWindow(long idVente) {
		this.idVente = idVente;
		handler.setIdVente(idVente);
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		handler.updateLignesCommandeListWindows();
		addNodesToPane();
		window.show();
	}
}

