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
		window.show();
	}

}
