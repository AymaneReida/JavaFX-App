package daofx;

public class User {
	private long id;
	private String login;
	private String passsword;
	
	public String getLogin() {
		return login;
	}
	public String getPasssword() {
		return passsword;
	}
	
	public long getId() {
		return id;
	}
	
	public User(long id, String login, String passsword) {
		this.id = id;
		this.login = login;
		this.passsword = passsword;
	}
}
