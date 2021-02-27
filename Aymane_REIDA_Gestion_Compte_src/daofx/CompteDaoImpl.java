package daofx;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompteDaoImpl extends AbstractDAO implements ICompteDAO {

	@Override
	public void add(Compte obj) {
		PreparedStatement pst = null;
		String sql = "insert into compte(numero, nom, prenom, solde) values (?, ?, ?, ?)";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, obj.getNumero());
			pst.setString(2, obj.getNom());
			pst.setString(3, obj.getPrenom());
			pst.setDouble(4, obj.getSolde());
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
	}

	@Override
	public void delete(long id) {
	}

	@Override
	public Compte getOne(long id) {
		return null;
	}
	
	public Compte getOne(String numero) {
		PreparedStatement pst = null;
		ResultSet rs;
		// ecrire la requete
		// executer la requete
		// remplir list
		String sql = "select * from compte where numero = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, numero);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			if (rs.next()) {
				return new Compte(rs.getLong("id"), rs.getString("numero"), rs.getString("nom"),
						rs.getString("prenom"), rs.getDouble("solde"));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		return null;
	}

	@Override
	public List<Compte> getAll() {
		return null;
	}

	@Override
	public void update(Compte obj) {
		PreparedStatement pst = null;
		String sql = "update compte set numero = ?, nom = ?, prenom = ?, solde = ? where id = ?";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, obj.getNumero());
			pst.setString(2, obj.getNom());
			pst.setString(3, obj.getPrenom());
			pst.setDouble(4, obj.getSolde());
			pst.setLong(5, obj.getId());
			System.out.println("Succes d'execution de la requete!!");
			pst.executeUpdate();
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
	}
	
	public void debiter(String numero, double montant) {
		Compte compte = this.getOne(numero);
		double solde = compte.getSolde() - montant;
		compte.setSolde(solde);
		this.update(compte);
	}
	
	public void verser() {
	}
}

