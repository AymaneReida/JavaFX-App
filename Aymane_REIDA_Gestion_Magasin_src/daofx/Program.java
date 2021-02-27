package daofx;

import java.time.LocalDate;
import java.util.List;

public class Program {
	public static void main(String[] args) {
		IProduitDAO pdao = new ProduitDaoImpl();

		System.out.println("------------------------------------delete(5)");
		pdao.delete(5);
		
		System.out.println("------------------------------------add()");
		Produit prd = new Produit(0, "Galaxy 88", 8, 8000, 8888, LocalDate.now());
		pdao.add(prd);

		System.out.println("------------------------------------getAll()");
		List<Produit> list = pdao.getAll();
		for (Produit p : list) {
			System.out.println(p);
		}

		System.out.println("------------------------------------getOne()");
		Produit p = pdao.getOne(5);
		if (p != null) {
			System.out.println(p);
		} else {
			System.out.println("produit non existant");
		}

		System.out.println("------------------------------------getAll(s)");
		List<Produit> list2 = pdao.getAll("TV");
		for (Produit pr : list2) {
			System.out.println(pr);
		}
	}
}
