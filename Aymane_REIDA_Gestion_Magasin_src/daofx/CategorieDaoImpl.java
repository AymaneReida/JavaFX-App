package daofx;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieDaoImpl extends AbstractDAO implements ICategorieDAO {

	@Override
	public void add(Categorie obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Categorie getOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Categorie> getAll() {
		List<Categorie> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select * from categorie";
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Categorie(rs.getLong("id"), rs.getString("intitule")));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	@Override
	public void update(Categorie obj) {
		// TODO Auto-generated method stub
		
	}

}
