package application;

import java.util.List;

import daofx.IPaiementDAO;
import daofx.PaiementDaoImpl;
import daofx.Paiement;

public class PaiementsListHandler {
	IPaiementDAO pdao = new PaiementDaoImpl();
	PaiementsListWindow listWindow = null;
	long idVente = 0;

	public PaiementsListHandler(PaiementsListWindow listWindow) {
		this.listWindow = listWindow;
	}

	public void setIdVente(long idVente) {
		this.idVente = idVente;
	}

	// IHM doit etre independante de la coucheaccess de données
	public void updatePaiementsListWindows() {
		List<Paiement> list = pdao.getAll(idVente);
		listWindow.paiementsObservableList.addAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		if (listWindow.paiementsObservableList.isEmpty()) {
			listWindow.totalLabelValue.setText(total + "");
		} else {
			for (Paiement p : listWindow.paiementsObservableList) {
				total += p.getMontant();
				listWindow.totalLabelValue.setText(total + "");
			}
		}
	}
}
