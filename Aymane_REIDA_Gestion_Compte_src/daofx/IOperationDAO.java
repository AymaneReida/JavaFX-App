package daofx;

import java.util.List;

public interface IOperationDAO extends IDAO<Operation> {
	public List<Operation> getAll(String numero_compte);
}
