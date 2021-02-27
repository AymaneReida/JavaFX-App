package daofx;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl extends AbstractDAO implements IClientDAO {

	@Override
	public void add(Client obj) {
		PreparedStatement pst = null;
		String sql = "insert into client(nom, prenom, telephone, email, adresse, date) values (?, ?, ?, ?, ?, ?)";
		try {
			pst = connection.prepareStatement(sql);
			// pst.setLong(1, obj.getId());
			pst.setString(1, obj.getNom());
			pst.setString(2, obj.getPrenom());
			pst.setString(3, obj.getTelephone());
			pst.setString(4, obj.getEmail());
			pst.setString(5, obj.getAdresse());
			Date date = Date.valueOf(obj.getDate());
			pst.setDate(6, date);
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
	}

	@Override
	public void delete(long id) {
		PreparedStatement pst = null;
		String sql = "delete from client where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, id);
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}

	}

	@Override
	public Client getOne(long id) {
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select * from client where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, id);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			if (rs.next()) {
				Date date = rs.getDate("date");
				return new Client(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("telephone"), rs.getString("email"), rs.getString("adresse"), date.toLocalDate());
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return null;
	}

	public Client getOne(String numero) {
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select * from client where numero_compte = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, numero);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			if (rs.next()) {
				Date date = rs.getDate("date");
				return new Client(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("telephone"), rs.getString("email"), rs.getString("adresse"), date.toLocalDate());
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		return null;
	}

	@Override
	public List<Client> getAll() {
		List<Client> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select * from client";
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate("date");
				list.add(new Client(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("telephone"), rs.getString("email"), rs.getString("adresse"), date.toLocalDate()));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	public List<Client> getVenteAll() {
		List<Client> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select * from client";
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate("date");
				list.add(new Client(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("telephone")));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	@Override
	public List<Client> getAll(String nom, String prenom) {
		List<Client> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select * from client where nom like ? and prenom like ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, nom + "%");
			pst.setString(2, prenom + "%");
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate("date");
				list.add(new Client(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("telephone"), rs.getString("email"), rs.getString("adresse"), date.toLocalDate()));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	@Override
	public void update(Client obj) {
		PreparedStatement pst = null;
		String sql = "update client set nom = ?, prenom = ?, telephone = ?, email = ?, adresse = ? where id = ?";
		// String sql = "insert into Produit(id, designation, qte, prix, date) values
		// (?, ?, ?, ?, ?)";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, obj.getNom());
			pst.setString(2, obj.getPrenom());
			pst.setString(3, obj.getTelephone());
			pst.setString(4, obj.getEmail());
			pst.setString(5, obj.getAdresse());
			pst.setLong(6, obj.getId());
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}

	}

}
