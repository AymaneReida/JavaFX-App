package applicationConsole;

import java.util.List;


public class Program {
	public static void main(String[] args) {
		ProduitDataAccess produitDataAccess = new ProduitDataAccess();
		
		//List<Produit> list = produitDataAccess.getAll();
		List<Produit> list = produitDataAccess.getProduitsByKeyWord("TV");
		
		for(Produit p:list) {
			System.out.println(p);
		}
	}
}
