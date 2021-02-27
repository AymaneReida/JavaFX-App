package daofx;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImpl extends AbstractDAO implements IProduitDAO {

	@Override
	public void add(Produit obj) {
		PreparedStatement pst = null;
		String sql = "insert into Produit(designation, qte, prix_achat, prix_vente, date) values (?, ?, ?, ?, ?)";
		// String sql = "insert into Produit(id, designation, qte, prix, date) values
		// (?, ?, ?, ?, ?)";
		try {
			pst = connection.prepareStatement(sql);
			// pst.setLong(1, obj.getId());
			pst.setString(1, obj.getDesignation());
			pst.setInt(2, obj.getQteStock());
			pst.setDouble(3, obj.getPrixAchat());
			pst.setDouble(4, obj.getPrixVente());
			Date date = Date.valueOf(obj.getDate());
			pst.setDate(5, date);
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
	}

	@Override
	public void delete(long id) {
		PreparedStatement pst = null;
		String sql = "delete from produit where id = ?";
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
	public Produit getOne(long id) {
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select * from produit where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, id);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			if (rs.next()) {
				// System.out.println(rs.getLong("id") + " " + rs.getString("designation"));
				Date date = rs.getDate("date");
				return new Produit(rs.getLong("id"), rs.getString("designation"), rs.getInt("qte"),
						rs.getDouble("prix_achat"), rs.getDouble("prix_vente"), date.toLocalDate());
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return null;
	}

	@Override
	public List<Produit> getAll() {
		List<Produit> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select * from produit";
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getLong("id") + " " + rs.getString("designation"));
				Date date = rs.getDate("date");
				list.add(new Produit(rs.getLong("id"), rs.getString("designation"), rs.getInt("qte"),
						rs.getDouble("prix_achat"), rs.getDouble("prix_vente"), date.toLocalDate()));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	public List<Produit> getLigneCommandeAll() {
		List<Produit> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		String sql = "select * from produit";
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Produit(rs.getLong("id"), rs.getString("designation"), rs.getInt("qte"),
						rs.getDouble("prix_achat"), rs.getDouble("prix_vente")));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	@Override
	public List<Produit> getAll(String des) {
		List<Produit> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select * from produit where designation like ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, des + "%");
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getLong("id") + " " + rs.getString("designation"));
				Date date = rs.getDate("date");
				list.add(new Produit(rs.getLong("id"), rs.getString("designation"), rs.getInt("qte"),
						rs.getDouble("prix_achat"), rs.getDouble("prix_vente"), date.toLocalDate()));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	@Override
	public void update(Produit obj) {
		PreparedStatement pst = null;
		String sql = "update Produit set designation = ?, qte = ?, prix_achat = ?, prix_vente = ?, date = ? where id = ?";
		// String sql = "insert into Produit(id, designation, qte, prix, date) values
		// (?, ?, ?, ?, ?)";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, obj.getDesignation());
			pst.setInt(2, obj.getQteStock());
			pst.setDouble(3, obj.getPrixAchat());
			pst.setDouble(4, obj.getPrixVente());
			Date date = Date.valueOf(obj.getDate());
			pst.setDate(5, date);
			pst.setLong(6, obj.getId());
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}

	}

}
