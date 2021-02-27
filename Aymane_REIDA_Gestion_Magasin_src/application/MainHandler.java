package application;

import daofx.UserDaoImpl;
import daofx.IUserDAO;
import daofx.User;

public class MainHandler {
	IUserDAO pdao = new UserDaoImpl();
	MainWindow mainWindow = null;
	User user = null;
	
	public MainHandler(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public void connectClick(String login, String password) {
		User user = pdao.getOne(login, password);
		this.user = user;
	}
}
