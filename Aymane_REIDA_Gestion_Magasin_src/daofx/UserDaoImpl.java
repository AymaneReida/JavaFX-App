package daofx;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends AbstractDAO implements IUserDAO {

	@Override
	public void add(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getOne(String login, String password) {
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select * from user where login = ? and password = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, login);
			pst.setString(2, password);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			if (rs.next()) {
				return new User(rs.getLong("id"), rs.getString("login"), rs.getString("password"));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return null;
	}
}
