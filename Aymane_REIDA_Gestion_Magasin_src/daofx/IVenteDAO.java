package daofx;

import java.util.List;

public interface IVenteDAO extends IDAO<Vente> {
	public List<Vente> getAll(long id);
}
