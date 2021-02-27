package application;

import java.time.LocalDate;

import daofx.Client;
import daofx.IVenteDAO;
import daofx.LigneCommande;
import daofx.Produit;
import daofx.Vente;
import daofx.VenteDaoImpl;
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

public class LignesCommandeFormWindow {
	LigneCommandeAddHandler handler = new LigneCommandeAddHandler(this);
	ProduitsListHandler produitsHandler = new ProduitsListHandler(this);
	// VenteAddHandler addHandler = new VenteAddHandler(this);
	Vente vente = null;
	int id = 0;
	HBox boot = new HBox();
	VBox ligneCommandeProduitsRoot = new VBox();
	VBox lignesCommandeRoot = new VBox();
	HBox buttonsBox = new HBox();
	HBox lignesCommandeBox = new HBox();
	HBox totalHbox = new HBox();
	Scene scene = new Scene(boot);
	Stage window = new Stage();
	Label titleLabel = new Label("Nouvelle vente");
	Label lignesCommandeLabel = new Label("Lignes de Commande");
	Label ligneCommandeProduitsLabel = new Label("Liste des produits");

	Label ligneCommandeProduitIdLabel = new Label("Id du produit:");
	TextField ligneCommandeProduitIdTextField = new TextField();
	Label ligneCommandeProduitQteLabel = new Label("Quantité vendue:");
	TextField ligneCommandeProduitQteTextField = new TextField();
	Label ligneCommandeProduitPrixVenteLabel = new Label("Prix vente:");
	TextField ligneCommandeProduitPrixVenteTextField = new TextField();

	TextField ligneCommandeIdTextField = new TextField();
	Button ligneCommandeDeleteButton = new Button("Supprimer");
	Button ligneCommandeAddButton = new Button("Ajouter");
	Button ligneCommandeCancelButton = new Button("Annuler");

	Label totalLabel = new Label("Total:");
	Label totalLabelValue = new Label("0.0");

	TableColumn<Produit, Long> idProduitColumn = new TableColumn<>("Id");
	TableColumn<Produit, String> designationProduitColumn = new TableColumn<>("Designation");
	TableColumn<Produit, Integer> qteStockProduitColumn = new TableColumn<>("Qte");
	TableColumn<Produit, Double> prixProduitAchatColumn = new TableColumn<>("Prix achat");
	TableColumn<Produit, Double> prixProduitVenteColumn = new TableColumn<>("Prix vente");

	TableColumn<LigneCommande, Long> idLigneCommandeColumn = new TableColumn<>("Id");
	TableColumn<LigneCommande, String> designationProduitLigneCommandeColumn = new TableColumn<>("Designation");
	TableColumn<LigneCommande, String> prixProduitLigneCommandeColumn = new TableColumn<>("Prix");
	TableColumn<LigneCommande, Integer> qteVendueColumn = new TableColumn<>("Quantite");
	TableColumn<LigneCommande, Double> sousTotalColumn = new TableColumn<>("Sous total");

	TableView<Produit> produitsTableView = new TableView<>();
	ObservableList<Produit> produitsObservableList = FXCollections.observableArrayList();

	TableView<LigneCommande> lignesCommandeTableView = new TableView<>();
	ObservableList<LigneCommande> lignesCommandeObservableList = FXCollections.observableArrayList();

	private void addColumnsToTableView() {
		produitsTableView.getColumns().addAll(idProduitColumn, designationProduitColumn, qteStockProduitColumn,
				prixProduitAchatColumn, prixProduitVenteColumn);
		produitsTableView.setItems(produitsObservableList);
		lignesCommandeTableView.getColumns().addAll(idLigneCommandeColumn, designationProduitLigneCommandeColumn,
				prixProduitLigneCommandeColumn, qteVendueColumn, sousTotalColumn);
		lignesCommandeTableView.setItems(lignesCommandeObservableList);
	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1330);
		window.setHeight(650);
		window.setTitle("Nouvelle Ligne de commande");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}

	private void addNodesToWindow() {
		totalHbox.getChildren().addAll(totalLabel, totalLabelValue);
		buttonsBox.getChildren().addAll(ligneCommandeAddButton, ligneCommandeCancelButton);
		ligneCommandeProduitsRoot.getChildren().addAll(titleLabel, ligneCommandeProduitIdLabel,
				ligneCommandeProduitIdTextField, ligneCommandeProduitQteLabel, ligneCommandeProduitQteTextField,
				ligneCommandeProduitPrixVenteLabel, ligneCommandeProduitPrixVenteTextField, buttonsBox,
				ligneCommandeProduitsLabel, produitsTableView);
		lignesCommandeBox.getChildren().addAll(ligneCommandeDeleteButton);
		lignesCommandeRoot.getChildren().addAll(lignesCommandeLabel, lignesCommandeBox, lignesCommandeTableView,
				totalHbox);
		boot.getChildren().addAll(ligneCommandeProduitsRoot, lignesCommandeRoot);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth() / 2);
		lignesCommandeLabel.getStyleClass().add("labelTitle");
		lignesCommandeLabel.setMinWidth(window.getWidth() / 2);
		ligneCommandeProduitsLabel.getStyleClass().add("labelTitle");
		ligneCommandeProduitsLabel.setMinWidth(window.getWidth() / 2);
		ligneCommandeProduitIdLabel.getStyleClass().add("labelForm");
		ligneCommandeProduitQteLabel.getStyleClass().add("labelForm");
		ligneCommandeProduitPrixVenteLabel.getStyleClass().add("labelForm");
		ligneCommandeProduitsRoot.setSpacing(15);
		lignesCommandeRoot.setSpacing(15);
		buttonsBox.setSpacing(15);
		lignesCommandeBox.setSpacing(15);
		boot.setSpacing(5);
		produitsTableView.getStyleClass().add("table-row-cell");
		produitsTableView.setOnMouseClicked(event -> {
			ligneCommandeProduitIdTextField
					.setText(String.valueOf(produitsTableView.getSelectionModel().getSelectedItem().getId()));
			ligneCommandeProduitPrixVenteTextField
					.setText(String.valueOf(produitsTableView.getSelectionModel().getSelectedItem().getPrixVente()));
		});
		lignesCommandeTableView.getStyleClass().add("table-row-cell");
		lignesCommandeTableView.setOnMouseClicked(event -> {
			ligneCommandeIdTextField
					.setText(String.valueOf(lignesCommandeTableView.getSelectionModel().getSelectedItem().getId()));
			id = lignesCommandeTableView.getSelectionModel().getSelectedIndex();
		});
		produitsTableView.setMinHeight(100);
		produitsTableView.setMaxHeight(300);
		produitsTableView.setMinWidth(500);
		lignesCommandeTableView.setMinHeight(100);
		lignesCommandeTableView.setMinWidth(600);
		totalLabel.getStyleClass().add("labelTotal");
		totalLabelValue.getStyleClass().add("labelTotal");
		totalHbox.getStyleClass().add("boxTotal");
		totalHbox.setSpacing(15);
	}

	private void updateColumns() {
		idProduitColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idProduitColumn.setPrefWidth(70);
		designationProduitColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
		designationProduitColumn.setPrefWidth(230);
		qteStockProduitColumn.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
		qteStockProduitColumn.setPrefWidth(70);
		prixProduitAchatColumn.setCellValueFactory(new PropertyValueFactory<>("prixAchat"));
		prixProduitAchatColumn.setPrefWidth(140);
		prixProduitVenteColumn.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
		prixProduitVenteColumn.setPrefWidth(140);

		idLigneCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idLigneCommandeColumn.setPrefWidth(100);
		designationProduitLigneCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
		designationProduitLigneCommandeColumn.setPrefWidth(250);
		prixProduitLigneCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("prixVente"));
		prixProduitLigneCommandeColumn.setPrefWidth(100);
		qteVendueColumn.setCellValueFactory(new PropertyValueFactory<>("qte"));
		qteVendueColumn.setPrefWidth(80);
		sousTotalColumn.setCellValueFactory(new PropertyValueFactory<>("sousTotal"));
		sousTotalColumn.setPrefWidth(130);
	}

	private void addEvents() {
		ligneCommandeCancelButton.setOnAction(event -> {
			window.close();
		});
		ligneCommandeAddButton.setOnAction(event -> {
			handler.addClick(vente.getId());
		});
		window.setOnCloseRequest(event -> {
			event.consume();
		});
		ligneCommandeDeleteButton.setOnAction(event -> {
			handler.deleteClick(id);
		});
	}

	public LignesCommandeFormWindow(Vente vente) {
		this.vente = vente;
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		produitsHandler.updateLigneCommandeProduitsListWindows();
		addNodesToWindow();
		addEvents();
		window.show();
	}
}
