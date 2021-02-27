package daofx;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LigneCommandeDaoImpl extends AbstractDAO implements ILigneCommandeDAO {
	String notConnected = null;

	public String getNotConnected() {
		return notConnected;
	}

	@Override
	public void add(LigneCommande obj) {
		PreparedStatement pst = null;
		String sql = "insert into ligne_commande (id_produit, id_vente, prix, qte, sousTotal) values (?, ?, ?, ?, ?)";
		if (connection != null) {
			try {
				pst = connection.prepareStatement(sql);
				pst.setLong(1, obj.getProduit().getId());
				pst.setLong(2, obj.getVente().getId());
				pst.setDouble(4, obj.getPrixVente());
				pst.setInt(4, obj.getQte());
				pst.setDouble(5, obj.getSousTotal());
				System.out.println("Succes d'execution de la requete!!");
				pst.executeUpdate();
			} catch (SQLException exp) {
				System.out.println(exp.getMessage());
			}
		} else {
			notConnected = "Error vous n'êtes pas connecté à la base de donnée";
		}
	}

	public long addLignecommande(LigneCommande obj) {
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet rs;
		long id = 0;
		String sql = "insert into ligne_commande (id_produit, id_vente, prix, qte, sousTotal) values (?, ?, ?, ?, ?)";
		String sql2 = "SELECT LAST_INSERT_ID()";
		if (connection != null) {
			try {
				pst = connection.prepareStatement(sql);
				pst.setLong(1, obj.getProduit().getId());
				pst.setLong(2, obj.getVente().getId());
				pst.setDouble(3, obj.getPrixVente());
				pst.setInt(4, obj.getQte());
				pst.setDouble(5, obj.getSousTotal());
				System.out.println("Succes d'execution de la requete!!");
				pst.executeUpdate();
				pst2 = connection.prepareStatement(sql2);
				rs = pst2.executeQuery();
				if (rs.next()) {
					id = rs.getLong("LAST_INSERT_ID()");
				}
			} catch (SQLException exp) {
				System.out.println(exp.getMessage());
			}
		} else {
			notConnected = "Error vous n'êtes pas connecté à la base de donnée";
		}
		return id;
	}

	@Override
	public void delete(long id) {
		PreparedStatement pst = null;
		String sql = "delete from ligne_commande where id = ?";
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
	public LigneCommande getOne(long id) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql = "select * from ligne_commande where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, id);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			if (rs.next()) {
				return new LigneCommande(rs.getLong("id"), rs.getInt("qte"), rs.getLong("id_vente"),
						rs.getLong("id_produit"), rs.getDouble("prix"));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return null;
	}

	@Override
	public List<LigneCommande> getAll() {
		List<LigneCommande> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		String sql = "select * from ligne_commande";
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate("date");
				list.add(new LigneCommande(rs.getLong("id"), rs.getInt("qte"), rs.getLong("id_vente"),
						rs.getLong("id_produit"), rs.getDouble("prix")));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	/*
	 * public List<LigneCommande> getLigneCommandeAll() { List<Produit> list = new
	 * ArrayList<>(); PreparedStatement pst = null; ResultSet rs; String sql =
	 * "select * from produit"; try { pst = connection.prepareStatement(sql);
	 * System.out.println("Succes d'execution de la requete!!"); rs =
	 * pst.executeQuery(); while (rs.next()) { list.add(new
	 * Produit(rs.getLong("id"), rs.getString("designation"),
	 * rs.getDouble("prix"))); } } catch (SQLException exp) {
	 * System.out.println(exp.getMessage()); } // pst.ex return list; }
	 */
	@Override
	public List<LigneCommande> getAll(long id_vente) {
		List<LigneCommande> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		String sql = "select * from ligne_commande, produit where id_vente = ? and produit.id = ligne_commande.id_produit";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, id_vente);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new LigneCommande(rs.getLong("ligne_commande.id"), rs.getInt("ligne_commande.qte"), id_vente,
						rs.getLong("ligne_commande.id_produit"), rs.getString("produit.designation"),
						rs.getDouble("ligne_commande.prix")));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	@Override
	public void update(LigneCommande obj) {
		PreparedStatement pst = null;
		String sql = "update ligne_commande set id_produit = ?, qte = ?, sousTotal = ? where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, obj.getProduit().getId());
			pst.setDouble(2, obj.getPrixVente());
			pst.setInt(3, obj.getQte());
			pst.setDouble(4, obj.getSousTotal());
			pst.setLong(5, obj.getId());
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}

	}

}
