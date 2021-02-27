package application;

import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MainWindow extends Application {
	private BorderPane root = new BorderPane();
	private Scene scene = new Scene(root);

	MenuItem nouveauCompteMenuItem = new MenuItem("Nouveau");
	MenuItem detailCompteMenuItem = new MenuItem("Detail");

	MenuItem helpMenuItem = new MenuItem("?");

	private void createMenu() {
		MenuBar menuBar = new MenuBar();

		Menu comptesMenu = new Menu("Comptes");
		Menu InventaireMenu = new Menu("Inventaire");
		Menu helpMenu = new Menu("Help");

		comptesMenu.getItems().addAll(nouveauCompteMenuItem, detailCompteMenuItem);
		helpMenu.getItems().addAll(helpMenuItem);

		menuBar.getMenus().addAll(comptesMenu, InventaireMenu, helpMenu);
		root.setTop(menuBar);
	}

	private void addStylesToNodes() {
		scene.getStylesheets().add("css/styles.css");
		root.getStyleClass().add("mainWindow");
	}

	private void addEvents() {
		nouveauCompteMenuItem.setOnAction(event -> {
			new CompteFormWindow();
		});

		detailCompteMenuItem.setOnAction(event -> {
			new CompteDetailWindow();
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

		TextField username = new TextField();
		username.setPromptText("Login");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username:"), 0, 0);
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

		result.ifPresent(usernamePassword -> {
			System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
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
		window.setTitle("Gestion des comptes");
		window.getIcons().add(new Image("file:icon.png"));
		creatLoginWindow(window);
		window.show();
	}

}
