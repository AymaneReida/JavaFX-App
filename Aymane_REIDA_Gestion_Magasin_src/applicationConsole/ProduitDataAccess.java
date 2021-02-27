package applicationConsole;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProduitDataAccess {
//1. se connecter à la base de données
	/*
	 * chaine de connexion 
	 * nom de base de données n
	 * om d'utilisateur (user) 
	 * mot de passe du user
	 */
	String db = "magasin_db";
	String user = "root";
	String pwd = "";
	String url = "jdbc:mysql://localhost:3306/" + db;
	Connection connection = null;

	public ProduitDataAccess() {
		try {
			connection = DriverManager.getConnection(url, user, pwd);
			System.out.println("connected.....");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	List<Produit> getAll() {
		List<Produit> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		//ecrire la requete
		//executer la requete
		//remplir list
		String sql = "select * from produit";
		try {
		pst = connection.prepareStatement(sql);
		System.out.println("Succes d'execution de la requete!!");
		rs = pst.executeQuery();
		while(rs.next()) {
			//System.out.println(rs.getLong("id") + " " + rs.getString("designation"));
			Date date = rs.getDate("date");
			list.add(new Produit(rs.getLong("id"), rs.getString("designation"), rs.getInt("qte"), rs.getDouble("prix"), date.toLocalDate()));
		}
		}catch(SQLException exp) {
			System.out.println(exp.getMessage());
		}
		//pst.ex
		return list;
	}
	
	List<Produit> getProduitsByKeyWord(String des) {
		List<Produit> list = new ArrayList<>();
		PreparedStatement pst = null;
		ResultSet rs;
		//ecrire la requete
		//executer la requete
		//remplir list
		String sql = "select * from produit where designation like ?";
		try {
		pst = connection.prepareStatement(sql);
		pst.setString(1, des + "%");
		System.out.println("Succes d'execution de la requete!!");
		rs = pst.executeQuery();
		while(rs.next()) {
			//System.out.println(rs.getLong("id") + " " + rs.getString("designation"));
			Date date = rs.getDate("date");
			list.add(new Produit(rs.getLong("id"), rs.getString("designation"), rs.getInt("qte"), rs.getDouble("prix"), date.toLocalDate()));
		}
		}catch(SQLException exp) {
			System.out.println(exp.getMessage());
		}
		//pst.ex
		return list;
	}
}
