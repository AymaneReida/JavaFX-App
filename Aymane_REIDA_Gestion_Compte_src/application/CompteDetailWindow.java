package application;

import java.time.LocalDate;

import daofx.Compte;
import daofx.Operation;
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
import javafx.stage.Stage;

public class CompteDetailWindow {
	CompteDetailHandler handler = new CompteDetailHandler(this);
	Stage window = new Stage();
	VBox root = new VBox();
	Scene scene = new Scene(root);
	Label titleLabel = new Label("Detail du compte");
	HBox boot = new HBox();
	HBox boot2 = new HBox();
	HBox boot3 = new HBox();
	
	Label compteNumeroLabel = new Label("Compte:");
	TextField compteNumeroTextField = new TextField();
	Button compteDetailButton = new Button("Afficher le détail du compte");
	Label compteNomPrenomLabel = new Label("Client:");
	Label compteNomPrenomValueLabel = new Label("");
	Label compteSoldeLabel = new Label("Solde:");
	Label compteSoldeValueLabel = new Label("0.0");

	TableColumn<Operation, LocalDate> dateColumn = new TableColumn<>("Date");
	TableColumn<Operation, String> operationColumn = new TableColumn<>("Operation");
	TableColumn<Operation, Double> montantColumn = new TableColumn<>("Montant");

	TableView<Operation> operationsTableView = new TableView<>();
	ObservableList<Operation> operationsObservableList = FXCollections.observableArrayList();

	private void addColumnsToTableView() {
		operationsTableView.getColumns().addAll(dateColumn, operationColumn, montantColumn);
		operationsTableView.setItems(operationsObservableList);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(window.getWidth());
		operationsTableView.getStyleClass().add("table-row-cell");
		operationsTableView.setMinHeight(300);
		compteNumeroLabel.getStyleClass().add("labelForm");
		compteNomPrenomLabel.getStyleClass().add("labelForm");
		compteNomPrenomValueLabel.getStyleClass().add("labelForm");
		compteSoldeLabel.getStyleClass().add("labelForm");
		compteSoldeValueLabel.getStyleClass().add("labelForm");
		root.setSpacing(15);
		boot.setSpacing(15);
		boot2.setSpacing(15);
		boot3.setSpacing(15);
	}

	private void updateColumns() {
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		dateColumn.setPrefWidth(200);
		operationColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		operationColumn.setPrefWidth(100);
		montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
		montantColumn.setPrefWidth(300);
	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(700);
		window.setHeight(600);
		window.setTitle("Detail du compte");
		window.getIcons().add(new Image("file:icon.png"));
	}

	private void addNodesToPane() {
		boot.getChildren().addAll(compteNumeroLabel, compteNumeroTextField, compteDetailButton);
		boot2.getChildren().addAll(compteNomPrenomLabel, compteNomPrenomValueLabel);
		boot3.getChildren().addAll(compteSoldeLabel, compteSoldeValueLabel);
		root.getChildren().addAll(boot, boot2, boot3, titleLabel, operationsTableView);
	}
	
	private void addEvents() {
		compteDetailButton.setOnAction(event -> {
			handler.updateOperationsListWindows();
		});
	}

	public CompteDetailWindow() {
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		addNodesToPane();
		addEvents();
		window.show();
	}
}
