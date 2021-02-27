package daofx;


public interface IUserDAO extends IDAO<User> {
	public User getOne(String login, String password);
}
