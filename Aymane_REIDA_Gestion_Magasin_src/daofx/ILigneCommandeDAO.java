package daofx;

import java.util.List;

public interface ILigneCommandeDAO extends IDAO<LigneCommande> {
	public List<LigneCommande> getAll(long id_vente);
}
