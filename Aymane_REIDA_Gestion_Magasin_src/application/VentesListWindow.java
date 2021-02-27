package application;

import java.time.LocalDate;

import daofx.Vente;
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

public class VentesListWindow {
	VentesListHandler handler = new VentesListHandler(this);
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Liste des ventes");
	HBox totalHbox = new HBox();

	Label totalLabel = new Label("Total:");
	Label totalLabelValue = new Label("0.0");
	Label idVenteValue = new Label("");

	TableColumn<Vente, Long> idColumn = new TableColumn<>("Id");
	TableColumn<Vente, String> nomColumn = new TableColumn<>("Nom");
	TableColumn<Vente, String> prenomColumn = new TableColumn<>("Prenom");
	TableColumn<Vente, Double> totalColumn = new TableColumn<>("Total");
	TableColumn<Vente, Double> payeColumn = new TableColumn<>("Payé");
	TableColumn<Vente, Double> resteColumn = new TableColumn<>("Reste");
	TableColumn<Vente, LocalDate> dateColumn = new TableColumn<>("Date");

	TableView<Vente> ventesTableView = new TableView<>();
	ObservableList<Vente> ventesObservableList = FXCollections.observableArrayList();

	private void addColumnsToTableView() {
		ventesTableView.getColumns().addAll(idColumn, nomColumn, prenomColumn, totalColumn, payeColumn, resteColumn,
				dateColumn);
		ventesTableView.setItems(ventesObservableList);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		totalLabel.getStyleClass().add("labelTotal");
		totalLabelValue.getStyleClass().add("labelTotal");
		totalHbox.getStyleClass().add("boxTotal");
		ventesTableView.getStyleClass().add("table-row-cell");
		ventesTableView.setOnMouseClicked(event -> {
			idVenteValue.setText(String.valueOf(ventesTableView.getSelectionModel().getSelectedItem().getId()));
			new LignesCommandeListWindow(Long.valueOf(idVenteValue.getText()));
		});
		ventesTableView.setMinHeight(500);
		totalHbox.setSpacing(15);
	}

	private void updateColumns() {
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColumn.setPrefWidth(100);
		nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
		nomColumn.setPrefWidth(170);
		prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		prenomColumn.setPrefWidth(170);
		totalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
		totalColumn.setPrefWidth(150);
		payeColumn.setCellValueFactory(new PropertyValueFactory<>("paye"));
		payeColumn.setPrefWidth(150);
		resteColumn.setCellValueFactory(new PropertyValueFactory<>("reste"));
		resteColumn.setPrefWidth(150);
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateColumn.setPrefWidth(100);
	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1000);
		window.setHeight(650);
		window.setTitle("Liste des ventes");
		window.getIcons().add(new Image("file:icon.png"));
	}

	private void addNodesToPane() {
		totalHbox.getChildren().addAll(totalLabel, totalLabelValue);
		root.getChildren().addAll(titleLabel, ventesTableView, totalHbox);
	}

	public VentesListWindow() {
		// handler.setListWindow(this);
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		handler.updateVentesListWindows();
		addNodesToPane();
		window.show();
	}
}
