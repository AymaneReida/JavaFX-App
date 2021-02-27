package daofx;

import java.util.List;

public interface IClientDAO extends IDAO<Client> {
	public List<Client> getAll(String nom, String prenom);
}
