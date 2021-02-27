package application;

import java.time.LocalDate;

import daofx.Client;
import daofx.IVenteDAO;
import daofx.Paiement;
import daofx.Vente;
import daofx.VenteDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class PaiementsUpdateWindow {
	PaiementUpdateHandler handler = new PaiementUpdateHandler(this);
	long idVente = 0;
	int id = 0;
	HBox boot = new HBox();
	VBox paiementsRoot = new VBox();
	VBox detailPaiementRoot = new VBox();
	VBox detailVenteRoot = new VBox();
	HBox buttonsBox = new HBox();
	HBox clientVenteBox = new HBox();
	HBox idVenteBox = new HBox();
	HBox dateVenteBox = new HBox();
	HBox totalHbox = new HBox();
	HBox totalPayeBox = new HBox();
	HBox resteBox = new HBox();
	HBox carteBox = new HBox();
	VBox carteRoot = new VBox();
	Scene scene = new Scene(boot);
	Stage window = new Stage();
	Label titleLabel = new Label("Détail de vente");
	Label listePaiementsLabel = new Label("Paiements");
	Label detailPaiementLabel = new Label("Détail de paiement");
	Label detailPaiementChequeLabel = new Label("En cas de chèque");
	Label detailPaiementCarteLabel = new Label("En cas de carte bancaire");

	TextField idPaiementTextField = new TextField();

	Label clientVenteLabel = new Label("Client:");
	Label clientVenteValueLabel = new Label("");
	Label idVenteLabel = new Label("Id vente:");
	Label idVenteValueLabel = new Label("");
	Label dateVenteLabel = new Label("Date:");
	Label dateVenteValueLabel = new Label("");
	Label totalLabel = new Label("Total:");
	Label totalValueLabel = new Label("0.0");
	Label totalPayeLabel = new Label("Total payé:");
	Label totalPayeValueLabel = new Label("0.0");
	Label resteLabel = new Label("Reste:");
	Label resteValueLabel = new Label("0.0");

	Label paiementDateLabel = new Label("Date:");
	DatePicker paiementDateSaisieDatePicker = new DatePicker();
	Label paiementMontantLabel = new Label("Montant:");
	TextField paiementMontantTextField = new TextField();
	Label paiementTypeLabel = new Label("Type:");
	ComboBox<String> paiementTypeComboBox = new ComboBox<String>();
	Label paiementNumeroChequeLabel = new Label("N°chèque:");
	TextField paiementNumeroChequeTextField = new TextField();
	Label paiementDateEcheanceLabel = new Label("Date échéance:");
	DatePicker paiementDateEcheanceDatePicker = new DatePicker();
	Label paiementBanqueLabel = new Label("Banque:");
	ComboBox<String> paiementBanqueComboBox = new ComboBox<String>();
	Label paiementProprietaireLabel = new Label("Proprietaire:");
	TextField paiementProprietaireTextField = new TextField();
	Label paiementNumeroCompteBancaireLabel = new Label("N°compte bancaire:");
	TextField paiementNumeroCompteBancaireTextField = new TextField();

	Button paiementDeleteButton = new Button("Supprimer");
	Button paiementAddButton = new Button("Ajouter");
	Button reglementCancelButton = new Button("Annuler");
	Button paiementSaveButton = new Button("Enregistrer");
	Button paiementPayButton = new Button("Payer");

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
		paiementTypeComboBox.getItems().addAll("ESPECE", "CHEQUE", "CARTE BANCAIRE");
		paiementTypeComboBox.setPromptText("Type de paiement");
		paiementBanqueComboBox.getItems().addAll("BP", "ATW", "BMCE", "BMCI");
		paiementBanqueComboBox.setEditable(true);
	}

	private void initWindow() {
		window.setScene(scene);
		window.setWidth(1330);
		window.setHeight(700);
		window.setTitle("Règlement");
		window.getIcons().add(new Image("file:icon.png"));
		window.initModality(Modality.APPLICATION_MODAL);
	}

	private void addNodesToWindow() {
		clientVenteBox.getChildren().addAll(clientVenteLabel, clientVenteValueLabel);
		idVenteBox.getChildren().addAll(idVenteLabel, idVenteValueLabel);
		dateVenteBox.getChildren().addAll(dateVenteLabel, dateVenteValueLabel);
		totalHbox.getChildren().addAll(totalLabel, totalValueLabel);
		totalPayeBox.getChildren().addAll(totalPayeLabel, totalPayeValueLabel);
		resteBox.getChildren().addAll(resteLabel, resteValueLabel);
		detailVenteRoot.getChildren().addAll(titleLabel, clientVenteBox, idVenteBox, dateVenteBox, totalHbox,
				totalPayeBox, resteBox);
		carteRoot.getChildren().addAll(detailPaiementCarteLabel, paiementNumeroCompteBancaireLabel, paiementNumeroCompteBancaireTextField,
				paiementPayButton);
		carteBox.getChildren().addAll(detailVenteRoot, carteRoot);
		buttonsBox.getChildren().addAll(paiementAddButton, paiementDeleteButton, reglementCancelButton);
		paiementsRoot.getChildren().addAll(carteBox, listePaiementsLabel, paiementsTableView, buttonsBox);
		detailPaiementRoot.getChildren().addAll(detailPaiementLabel, paiementDateLabel, paiementDateSaisieDatePicker,
				paiementMontantLabel, paiementMontantTextField, paiementTypeLabel, paiementTypeComboBox,
				detailPaiementChequeLabel, paiementNumeroChequeLabel, paiementNumeroChequeTextField,
				paiementDateEcheanceLabel, paiementDateEcheanceDatePicker, paiementBanqueLabel, paiementBanqueComboBox,
				paiementProprietaireLabel, paiementProprietaireTextField, paiementSaveButton);
		boot.getChildren().addAll(paiementsRoot, detailPaiementRoot);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		titleLabel.getStyleClass().add("labelTitle");
		titleLabel.setMinWidth(1 * window.getWidth() / 4);
		detailPaiementCarteLabel.getStyleClass().add("labelTitle");
		detailPaiementCarteLabel.setMinWidth(1 * window.getWidth() / 2);
		listePaiementsLabel.getStyleClass().add("labelTitle");
		listePaiementsLabel.setMinWidth(3 * window.getWidth() / 4);
		detailPaiementLabel.getStyleClass().add("labelTitle");
		detailPaiementLabel.setMinWidth(window.getWidth() / 4);
		detailPaiementChequeLabel.getStyleClass().add("labelTitle");
		detailPaiementChequeLabel.setMinWidth(window.getWidth() / 4);
		clientVenteLabel.getStyleClass().add("labelForm");
		clientVenteValueLabel.getStyleClass().add("labelForm");
		idVenteLabel.getStyleClass().add("labelForm");
		idVenteValueLabel.getStyleClass().add("labelForm");
		dateVenteLabel.getStyleClass().add("labelForm");
		dateVenteValueLabel.getStyleClass().add("labelForm");
		totalLabel.getStyleClass().add("labelForm");
		totalValueLabel.getStyleClass().add("labelForm");
		totalPayeLabel.getStyleClass().add("labelForm");
		totalPayeValueLabel.getStyleClass().add("labelForm");
		resteLabel.getStyleClass().add("labelForm");
		resteValueLabel.getStyleClass().add("labelForm");
		paiementDateLabel.getStyleClass().add("labelForm");
		paiementMontantLabel.getStyleClass().add("labelForm");
		paiementTypeLabel.getStyleClass().add("labelForm");
		paiementNumeroChequeLabel.getStyleClass().add("labelForm");
		paiementDateEcheanceLabel.getStyleClass().add("labelForm");
		paiementBanqueLabel.getStyleClass().add("labelForm");
		paiementProprietaireLabel.getStyleClass().add("labelForm");
		paiementNumeroCompteBancaireLabel.getStyleClass().add("labelForm");
		paiementsRoot.setSpacing(15);
		detailPaiementRoot.setSpacing(15);
		buttonsBox.setSpacing(15);
		clientVenteBox.setSpacing(15);
		idVenteBox.setSpacing(15);
		dateVenteBox.setSpacing(15);
		totalHbox.setSpacing(15);
		totalPayeBox.setSpacing(15);
		resteBox.setSpacing(15);
		detailVenteRoot.setSpacing(15);
		boot.setSpacing(5);
		carteRoot.setSpacing(15);
		carteBox.setSpacing(5);
		paiementsTableView.getStyleClass().add("table-row-cell");
		paiementsTableView.setOnMouseClicked(event -> {
			idPaiementTextField
					.setText(String.valueOf(paiementsTableView.getSelectionModel().getSelectedItem().getId()));
			id = paiementsTableView.getSelectionModel().getSelectedIndex();
		});
		paiementsTableView.setMinHeight(100);
		paiementsTableView.setMaxHeight(300);
		paiementsTableView.setMinWidth(600);
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

	private void addEvents() {
		reglementCancelButton.setOnAction(event -> {
			window.close();
		});
		paiementAddButton.setOnAction(event -> {
			handler.addClick();
		});
		window.setOnCloseRequest(event -> {
			event.consume();
		});
		paiementDeleteButton.setOnAction(event -> {
			handler.deleteClick(id);
		});

		paiementSaveButton.setOnAction(event -> {
			handler.saveClick(idVente);
			handler.saveClick2(idVente);
		});
		
		paiementPayButton.setOnAction(event -> {
			handler.payClick(idVente);
		});
	}

	public PaiementsUpdateWindow(long idVente, ReglementUpdateWindow updateReglement) {
		this.idVente = idVente;
		handler.setIdVente(this.idVente);
		handler.updateTotalPaye();
		handler.setUpdateReglement(updateReglement);
		initWindow();
		addStylesToNodes();
		updateColumns();
		addColumnsToTableView();
		handler.updatePaiementsListWindows();
		addNodesToWindow();
		addEvents();
		handler.updatClientInformations();
		window.show();
	}
}
