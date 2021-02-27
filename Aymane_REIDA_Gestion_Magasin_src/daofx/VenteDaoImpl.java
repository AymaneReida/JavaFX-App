package daofx;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenteDaoImpl extends AbstractDAO implements IVenteDAO {

	@Override
	public void add(Vente obj) {
		PreparedStatement pst = null;
		String sql = "insert into vente(id_client, total, paye, reste, date) values (?, ?, ?, ?, ?)";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, obj.getClient().getId());
			pst.setDouble(2, obj.getTotal());
			pst.setDouble(3, obj.getPaye());
			pst.setDouble(4, obj.getReste());
			Date date = Date.valueOf(obj.getDate());
			pst.setDate(5, date);
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
	}

	public long addVente(Vente obj) {
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet rs;
		long id = 0;
		String sql = "insert into vente(id_client, total, paye, reste, date) values (?, ?, ?, ?, ?)";
		String sql2 = "SELECT LAST_INSERT_ID()";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, obj.getClient().getId());
			pst.setDouble(2, obj.getTotal());
			pst.setDouble(3, obj.getPaye());
			pst.setDouble(4, obj.getReste());
			Date date = Date.valueOf(obj.getDate());
			pst.setDate(5, date);
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
			pst2 = connection.prepareStatement(sql2);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst2.executeQuery();
			if (rs.next()) {
				id = rs.getLong("LAST_INSERT_ID()");
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		return id;
	}

	@Override
	public void delete(long id) {
		PreparedStatement pst = null;
		String sql = "delete from ligne_commande where id_vente = ?";
		String sql2 = "delete from paiement where id_vente = ?";
		String sql3 = "delete from vente where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, id);
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
			pst = connection.prepareStatement(sql2);
			pst.setLong(1, id);
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
			pst = connection.prepareStatement(sql3);
			pst.setLong(1, id);
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}

	}

	@Override
	public Vente getOne(long id) {
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select vente.id, vente.id_client, vente.total, vente.paye, vente.reste, vente.date, client.nom, client.prenom from vente, client where vente.id = ? and client.id = vente.id_client";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, id);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			if (rs.next()) {
				// System.out.println(rs.getLong("id") + " " + rs.getString("designation"));
				Date date = rs.getDate("date");
				return new Vente(rs.getLong("vente.id"), date.toLocalDate(), rs.getDouble("vente.total"),
						rs.getDouble("vente.paye"), rs.getDouble("vente.reste"), rs.getLong("vente.id_client"),
						rs.getString("client.nom"), rs.getString("client.prenom"));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return null;
	}

	@Override
	public List<Vente> getAll() {
		List<Vente> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select vente.id, vente.id_client, vente.total, vente.paye, vente.reste, vente.date, client.nom, client.prenom from vente, client where client.id = vente.id_client";
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate("date");
				list.add(new Vente(rs.getLong("vente.id"), date.toLocalDate(), rs.getDouble("vente.total"),
						rs.getDouble("vente.paye"), rs.getDouble("vente.reste"), rs.getLong("vente.id_client"),
						rs.getString("client.nom"), rs.getString("client.prenom")));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	@Override
	public void update(Vente obj) {
		PreparedStatement pst = null;
		String sql = "update vente set id_client = ?, total = ?, paye = ?, reste = ?, date = ? where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, obj.getClient().getId());
			pst.setDouble(2, obj.getTotal());
			pst.setDouble(3, obj.getPaye());
			pst.setDouble(4, obj.getReste());
			Date date = Date.valueOf(obj.getDate());
			pst.setDate(5, date);
			pst.setLong(6, obj.getId());
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
	}

	@Override
	public List<Vente> getAll(long id) {
		List<Vente> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select vente.id, vente.id_client, vente.total, vente.paye, vente.reste, vente.date, client.nom, client.prenom from vente, client where vente.id = ? and client.id = vente.id_client";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, id);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate("date");
				list.add(new Vente(rs.getLong("vente.id"), date.toLocalDate(), rs.getDouble("vente.total"),
						rs.getDouble("vente.paye"), rs.getDouble("vente.reste"), rs.getLong("vente.id_client"),
						rs.getString("client.nom"), rs.getString("client.prenom")));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}
}
