package application;

import java.util.Optional;

import daofx.AbstractDAO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MainWindow extends Application {
	MainHandler handler = new MainHandler(this);
	private BorderPane root = new BorderPane();
	private Scene scene = new Scene(root);

	MenuItem nouveauProduitMenuItem = new MenuItem("Nouveau");
	MenuItem listeProduitsMenuItem = new MenuItem("Liste");
	MenuItem rechercherProduitsMenuItem = new MenuItem("Rechercher");
	MenuItem supprimerProduitMenuItem = new MenuItem("Supprimer");
	MenuItem modifierProduitMenuItem = new MenuItem("Modifier");

	MenuItem nouveauClientMenuItem = new MenuItem("Nouveau");
	MenuItem listeClientsMenuItem = new MenuItem("Liste");
	MenuItem rechercherClientsMenuItem = new MenuItem("Rechercher");
	MenuItem supprimerClientMenuItem = new MenuItem("Supprimer");
	MenuItem modifierClientMenuItem = new MenuItem("Modifier");

	MenuItem nouvelleVenteMenuItem = new MenuItem("Nouveau");
	MenuItem listeVentesMenuItem = new MenuItem("Liste");
	MenuItem rechercherVentesMenuItem = new MenuItem("Rechercher");
	MenuItem supprimerVenteMenuItem = new MenuItem("Supprimer");
	MenuItem modifierVenteMenuItem = new MenuItem("Modifier");

	MenuItem nouveauReglementMenuItem = new MenuItem("Nouveau");
	MenuItem listeReglementsMenuItem = new MenuItem("Liste");
	MenuItem rechercherReglementMenuItem = new MenuItem("Rechercher");
	MenuItem supprimerReglementMenuItem = new MenuItem("Supprimer");
	MenuItem modifierReglementMenuItem = new MenuItem("Modifier");

	MenuItem helpMenuItem = new MenuItem("?");

	private void createMenu() {
		MenuBar menuBar = new MenuBar();

		Menu produitsMenu = new Menu("Produits");
		Menu clientsMenu = new Menu("Clients");
		Menu ventesMenu = new Menu("Ventes");
		Menu paimentsMenu = new Menu("Paiments");
		Menu InventaireMenu = new Menu("Inventaire");
		Menu helpMenu = new Menu("Help");

		produitsMenu.getItems().addAll(nouveauProduitMenuItem, listeProduitsMenuItem, rechercherProduitsMenuItem,
				supprimerProduitMenuItem, modifierProduitMenuItem);
		clientsMenu.getItems().addAll(nouveauClientMenuItem, listeClientsMenuItem, rechercherClientsMenuItem,
				supprimerClientMenuItem, modifierClientMenuItem);
		ventesMenu.getItems().addAll(nouvelleVenteMenuItem, listeVentesMenuItem, rechercherVentesMenuItem,
				supprimerVenteMenuItem, modifierVenteMenuItem);
		paimentsMenu.getItems().addAll(nouveauReglementMenuItem, listeReglementsMenuItem, rechercherReglementMenuItem,
				supprimerReglementMenuItem, modifierReglementMenuItem);
		helpMenu.getItems().addAll(helpMenuItem);

		menuBar.getMenus().addAll(produitsMenu, clientsMenu, ventesMenu, paimentsMenu, InventaireMenu, helpMenu);
		root.setTop(menuBar);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		root.getStyleClass().add("mainWindow");
	}

	private void addEvents() {
		nouveauProduitMenuItem.setOnAction(event -> {
			new ProduitFormWindow();
		});

		listeProduitsMenuItem.setOnAction(event -> {
			new ProduitsListWindow();
		});

		rechercherProduitsMenuItem.setOnAction(event -> {
			new ProduitSearchWindow();
		});

		supprimerProduitMenuItem.setOnAction(event -> {
			new ProduitDeleteWindow();
		});

		modifierProduitMenuItem.setOnAction(event -> {
			new ProduitUpdateWindow();
		});

		nouveauClientMenuItem.setOnAction(event -> {
			new ClientFormWindow();
		});

		listeClientsMenuItem.setOnAction(event -> {
			new ClientsListWindow();
		});

		rechercherClientsMenuItem.setOnAction(event -> {
			new ClientSearchWindow();
		});

		supprimerClientMenuItem.setOnAction(event -> {
			new ClientDeleteWindow();
		});

		modifierClientMenuItem.setOnAction(event -> {
			new ClientUpdateWindow();
		});

		nouvelleVenteMenuItem.setOnAction(event -> {
			new VenteFormWindow();
		});

		listeVentesMenuItem.setOnAction(event -> {
			new VentesListWindow();
		});

		rechercherVentesMenuItem.setOnAction(event -> {
			new VenteSearchWindow();
		});

		supprimerVenteMenuItem.setOnAction(event -> {
			new VenteDeleteWindow();
		});

		modifierVenteMenuItem.setOnAction(event -> {
			new VenteUpdateWindow();
		});

		nouveauReglementMenuItem.setOnAction(event -> {
			new ReglementFormWindow();
		});

		listeReglementsMenuItem.setOnAction(event -> {
			new ReglementsListWindow();
		});

		rechercherReglementMenuItem.setOnAction(event -> {
			new ReglementSearchWindow();
		});

		supprimerReglementMenuItem.setOnAction(event -> {
			new ReglementDeleteWindow();
		});

		modifierReglementMenuItem.setOnAction(event -> {
			new ReglementUpdateWindow();
		});
	}

	public void creatLoginWindow(Stage window) {
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText("Entrez votre login et votre mot de passe");
		dialog.initOwner(window);

		// Set the icon (must be included in the project).
		ImageView image = new ImageView(this.getClass().getResource("login.png").toString());
		image.setFitWidth(100);
		image.setFitHeight(100);
		image.setPreserveRatio(true);
		image.setSmooth(true);
		image.setCache(true);
		dialog.setGraphic(image);
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        dialog.setX((screenBounds.getWidth() - 450f) / 2);
        dialog.setY((screenBounds.getHeight() - 400f) / 2);

		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		// grid.setAlignment(Pos.CENTER);

		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Login:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair when the login button is
		// clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), password.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(loginPassword -> {
			handler.connectClick(loginPassword.getKey(), loginPassword.getValue());
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage window) throws Exception {
		createMenu();
		addEvents();
		addStylesToNodes();
		window.setScene(scene);
		window.setWidth(1500);
		window.setHeight(650);
		window.setTitle("Gestion de magasin");
		window.getIcons().add(new Image("file:icon.png"));
		creatLoginWindow(window);
		if (handler.user != null) {
			window.show();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Verifiez les informations entrées");
			alert.setContentText("Error le login ou le mot de passe est incorrect");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(window);

			alert.showAndWait();
		}
	}

}
