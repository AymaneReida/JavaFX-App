package application;

import java.util.List;

import daofx.Compte;
import daofx.CompteDaoImpl;
import daofx.ICompteDAO;
import daofx.IOperationDAO;
import daofx.OperationDaoImpl;
import daofx.Operation;

public class CompteDetailHandler {
	IOperationDAO pdao = new OperationDaoImpl();
	ICompteDAO pdaoCompte = new CompteDaoImpl();
	CompteDetailWindow listWindow = null;

	public CompteDetailHandler(CompteDetailWindow listWindow) {
		this.listWindow = listWindow;
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updateOperationsListWindows() {
		double solde = 0;
		List<Operation> list = pdao.getAll(listWindow.compteNumeroTextField.getText());
		listWindow.operationsObservableList.clear();
		listWindow.operationsObservableList.addAll(list);
		Compte compte = ((CompteDaoImpl) pdaoCompte).getOne(listWindow.compteNumeroTextField.getText());
		listWindow.compteNomPrenomValueLabel.setText(compte.getPrenom() + " " + compte.getNom());
		solde = compte.getSolde();
		listWindow.compteSoldeValueLabel.setText(solde + "");
	}
}
