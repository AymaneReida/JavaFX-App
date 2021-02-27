package daofx;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationDaoImpl extends AbstractDAO implements IOperationDAO {

	@Override
	public void add(Operation obj) {
		PreparedStatement pst = null;
		String sql = "insert into operation(id_compte, type, montant, date) values (?, ?, ?, ?)";
		try {
			pst = connection.prepareStatement(sql);
			// pst.setLong(1, obj.getId());
			pst.setLong(1, obj.getCompte().getId());
			pst.setString(2, obj.getType());
			pst.setDouble(3, obj.getMontant());
			Date date = Date.valueOf(obj.getDate());
			pst.setDate(4, date);
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
	public Operation getOne(long id) {
		return null;
	}

	@Override
	public List<Operation> getAll() {
		return null;
	}

	@Override
	public List<Operation> getAll(String numero_compte) {
		List<Operation> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		String sql = "select * from operation, compte where operation.id_compte = compte.id and compte.numero = ? ";
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, numero_compte);
			System.out.println("Succes d'execution de la requete!!");
			rs = pst.executeQuery();
			while (rs.next()) {
				Date date = rs.getDate("operation.date");
				list.add(new Operation(rs.getLong("operation.id"), rs.getLong("operation.id_compte"),
						rs.getString("operation.type"), rs.getDouble("operation.montant"), date.toLocalDate(),
						rs.getString("compte.nom"), rs.getString("compte.prenom")));
			}
		} catch (SQLException exp) {
			System.out.println(exp.getMessage());
		}
		// pst.ex
		return list;
	}

	@Override
	public void update(Operation obj) {
	}

}
