package application;

import java.util.List;

import daofx.ILigneCommandeDAO;
import daofx.LigneCommandeDaoImpl;
import daofx.LigneCommande;

public class LignesCommandeListHandler {
	ILigneCommandeDAO pdao = new LigneCommandeDaoImpl();
	LignesCommandeListWindow listWindow = null;
	long idVente = 0;

	public LignesCommandeListHandler(LignesCommandeListWindow listWindow) {
		this.listWindow = listWindow;
	}

	public void setIdVente(long idVente) {
		this.idVente = idVente;
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updateLignesCommandeListWindows() {
		List<LigneCommande> list = pdao.getAll(idVente);
		listWindow.lignesCommandeObservableList.addAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		if (listWindow.lignesCommandeObservableList.isEmpty()) {
			listWindow.totalLabelValue.setText(total + "");
		} else {
			for (LigneCommande l : listWindow.lignesCommandeObservableList) {
				total += l.getSousTotal();
				listWindow.totalLabelValue.setText(total + "");
			}
		}
	}
}
