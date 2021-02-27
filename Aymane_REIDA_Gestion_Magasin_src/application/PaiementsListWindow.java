package application;

import java.time.LocalDate;

import daofx.Paiement;
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

public class PaiementsListWindow {
	PaiementsListHandler handler = new PaiementsListHandler(this);
	long idVente = 0;
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Paiements");
	HBox totalHbox = new HBox();

	Label totalLabel = new Label("Total:");
	Label totalLabelValue = new Label("0.0");

	TableColumn<Paiement, Long> idPaiementColumn = new TableColumn<>("Id");
	TableColumn<Paiement, Double> montantPaiementColumn = new TableColumn<>("Montant");
	TableColumn<Paiement, LocalDate> dateColumn = new TableColumn<>("Date");
	TableColumn<Paiement, String> typePaiementColumn = new TableColumn<>("Type");
	TableColumn<Paiement, String> numeroChequePaiementColumn = new TableColumn<>("N°Chèque");
	TableColumn<Paiement, String> proprietairePaiementColumn = new TableColumn<>("Propriétaire");
	TableColumn<Paiement, String> banquePaiementColumn = new TableColumn<>("Banque");

	TableView<Paiement> paiementsTableView = new TableView<>();
	ObservableList<Paiement> paiementsObservableList = FXCollections.observableArrayList();

	private void addColumnsToTableView() {
		paiementsTableView.getColumns().addAll(idPaiementColumn, montantPaiementColumn, dateColumn, typePaiementColumn,
				numeroChequePaiementColumn, proprietairePaiementColumn, banquePaiementColumn);
		paiementsTableView.setItems(paiementsObservableList);
	}
	
	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		totalLabel.getStyleClass().add("labelTotal");
		totalLabelValue.getStyleClass().add("labelTotal");
		totalHbox.getStyleClass().add("boxTotal");
		paiementsTableView.getStyleClass().add("table-row-cell");
		paiementsTableView.setMinHeight(500);
		totalHbox.setSpacing(15);
	}

	private void updateColumns() {
		idPaiementColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idPaiementColumn.setPrefWidth(70);
		montantPaiementColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
		montantPaiementColumn.setPrefWidth(130);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateColumn.setPrefWidth(130);
		typePaiementColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		typePaiementColumn.setPrefWidth(130);
		numeroChequePaiementColumn.setCellValueFactory(new PropertyValueFactory<>("numeroCheque"));
		numeroChequePaiementColumn.setPrefWidth(170);
		proprietairePaiementColumn.setCellValueFactory(new PropertyValueFactory<>("proprietaire"));
		proprietairePaiementColumn.setPrefWidth(190);
		banquePaiementColumn.setCellValueFactory(new PropertyValueFactory<>("banque"));
		banquePaiementColumn.setPrefWidth(190);
	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(650);
		window.setTitle("Paiements");
		window.getIcons().add(new Image("file:icon.png"));
	}

	private void addNodesToPane() {
		totalHbox.getChildren().addAll(totalLabel, totalLabelValue);
		root.getChildren().addAll(titleLabel, paiementsTableView, totalHbox);
	}

	public PaiementsListWindow(long idVente) {
		this.idVente = idVente;
		handler.setIdVente(idVente);
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		handler.updatePaiementsListWindows();
		addNodesToPane();
		window.show();
	}
}

