package daofx;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaiementDaoImpl extends AbstractDAO implements IPaiementDAO {

	@Override
	public void add(Paiement obj) {
		PreparedStatement pst = null;
		String sql = "insert into paiement (id_vente, montant, date, type, numero_cheque, date_echeance, proprietaire, banque) values (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, obj.getVente().getId());
			pst.setDouble(2, obj.getMontant());
			Date date = Date.valueOf(obj.getDate());
			pst.setDate(3, date);
			pst.setString(4, obj.getType());
			pst.setString(5, obj.getNumeroCheque());
			Date dateEcheance = Date.valueOf(obj.getDateEcheance());
			pst.setDate(6, dateEcheance);
			pst.setString(7, obj.getProprietaire());
			pst.setString(8, obj.getBanque());
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
	}

	public long addPaiement(Paiement obj) {
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet rs;
		long id = 0;
		String sql = "insert into paiement (id_vente, montant, date, type, numero_cheque, date_echeance, proprietaire, banque) values (?, ?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "SELECT LAST_INSERT_ID()";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, obj.getVente().getId());
			pst.setDouble(2, obj.getMontant());
			Date date = Date.valueOf(obj.getDate());
			pst.setDate(3, date);
			pst.setString(4, obj.getType());
			pst.setString(5, "");
			// Dans phpMyAdmin il faut cocher la valeur NULL par défaut pour la colonne
			// date_echeance
			pst.setDate(6, null);
			pst.setString(7, "");
			pst.setString(8, "");
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
		return id;
	}

	public long addPaiement2(Paiement obj) {
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet rs;
		long id = 0;
		String sql = "insert into paiement (id_vente, montant, date, type, numero_cheque, date_echeance, proprietaire, banque) values (?, ?, ?, ?, ?, ?, ?, ?)";
		String sql2 = "SELECT LAST_INSERT_ID()";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, obj.getVente().getId());
			pst.setDouble(2, obj.getMontant());
			Date date = Date.valueOf(obj.getDate());
			pst.setDate(3, date);
			pst.setString(4, obj.getType());
			pst.setString(5, obj.getNumeroCheque());
			Date dateEcheance = Date.valueOf(obj.getDateEcheance());
			pst.setDate(6, dateEcheance);
			pst.setString(7, obj.getProprietaire());
			pst.setString(8, obj.getBanque());
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
		return id;
	}

	@Override
	public void delete(long id) {
		PreparedStatement pst = null;
		String sql = "delete from paiement where id = ?";
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
	public Paiement getOne(long id) {
		PreparedStatement pst = null;
		ResultSet rs;
		String sql = "select * from paiement where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, id);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			if (rs.next()) {
				Date date = rs.getDate("date");
				Date dateEchance = rs.getDate("date_echeance");
				LocalDate dateLocalEchance = null;
				if (dateEchance != null) {
					dateLocalEchance = dateEchance.toLocalDate();
				}
				return new Paiement(rs.getLong("id"), rs.getDouble("montant"), dateLocalEchance, rs.getString("type"),
						rs.getString("numero_cheque"), dateEchance.toLocalDate(), rs.getString("proprietaire"),
						rs.getString("banque"), rs.getLong("id_vente"));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return null;
	}

	@Override
	public List<Paiement> getAll() {
		List<Paiement> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		String sql = "select * from paiement";
		try {
			pst = connection.prepareStatement(sql);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate("date");
				Date dateEchance = rs.getDate("date_echeance");
				LocalDate dateLocalEchance = null;
				if (dateEchance != null) {
					dateLocalEchance = dateEchance.toLocalDate();
				}
				list.add(new Paiement(rs.getLong("id"), rs.getDouble("montant"), date.toLocalDate(),
						rs.getString("type"), rs.getString("numero_cheque"), dateLocalEchance,
						rs.getString("proprietaire"), rs.getString("banque"), rs.getLong("id_vente")));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	@Override
	public List<Paiement> getAll(long id_vente) {
		List<Paiement> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		String sql = "select paiement.id, paiement.id_vente, paiement.montant, paiement.date, paiement.type, paiement.numero_cheque, paiement.date_echeance, paiement.proprietaire, paiement.banque from paiement where id_vente = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setLong(1, id_vente);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate("date");
				Date dateEchance = rs.getDate("date_echeance");
				LocalDate dateLocalEchance = null;
				if (dateEchance != null) {
					dateLocalEchance = dateEchance.toLocalDate();
				}
				list.add(new Paiement(rs.getLong("id"), rs.getDouble("montant"), date.toLocalDate(),
						rs.getString("type"), rs.getString("numero_cheque"), dateLocalEchance,
						rs.getString("proprietaire"), rs.getString("banque"), rs.getLong("id_vente")));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		return list;
	}

	@Override
	public void update(Paiement obj) {
		PreparedStatement pst = null;
		String sql = "update paiement set montant = ?, date = ?, type = ?, numero_cheque = ?, date_echeance = ?, proprietaire = ?, banque = ? where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setDouble(1, obj.getMontant());
			Date date = Date.valueOf(obj.getDate());
			pst.setDate(2, date);
			pst.setString(3, obj.getType());
			pst.setString(4, obj.getNumeroCheque());
			Date dateEcheance = Date.valueOf(obj.getDateEcheance());
			pst.setDate(5, dateEcheance);
			pst.setString(6, obj.getProprietaire());
			pst.setString(7, obj.getBanque());
			pst.setLong(8, obj.getId());
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
	}
}
