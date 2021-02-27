package daofx;

import java.util.List;

public interface IPaiementDAO extends IDAO<Paiement> {
	public List<Paiement> getAll(long idVente);
}
