package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import daofx.ILigneCommandeDAO;
import daofx.IProduitDAO;
import daofx.IVenteDAO;
import daofx.LigneCommandeDaoImpl;
import daofx.Produit;
import daofx.ProduitDaoImpl;
import daofx.VenteDaoImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import daofx.LigneCommande;

public class LigneCommandeUpdateHandler {
	ILigneCommandeDAO pdao = new LigneCommandeDaoImpl();
	IVenteDAO pdaoVente = new VenteDaoImpl();
	IProduitDAO pdaoProduit = new ProduitDaoImpl();
	LignesCommandeUpdateFormWindow formUpdateLigneCommande = null;
	long idVente = 0;

	public LigneCommandeUpdateHandler(LignesCommandeUpdateFormWindow formUpdateLigneCommande) {
		this.formUpdateLigneCommande = formUpdateLigneCommande;
	}

	public void setIdVente(long idVente) {
		this.idVente = idVente;
	}

	public void addClick(long id_vente) {
		long id = 0;
		long id_produit = Long.valueOf(formUpdateLigneCommande.ligneCommandeProduitIdTextField.getText());
		Produit produit = pdaoProduit.getOne(id_produit);
		int qte = Integer.valueOf(formUpdateLigneCommande.ligneCommandeProduitQteTextField.getText());
		String designation = produit.getDesignation();
		double prixVente = Double.valueOf(formUpdateLigneCommande.ligneCommandeProduitPrixVenteTextField.getText());
		int qteStock = produit.getQteStock();
		if (qte != 0) {
			if (qteStock > qte) {
				LigneCommande l = new LigneCommande(0, qte, id_vente, id_produit, designation, prixVente);
				id = ((LigneCommandeDaoImpl) pdao).addLignecommande(l);
				l.setId(id);
				List<LigneCommande> list = new ArrayList<>();
				list.add(l);
				formUpdateLigneCommande.lignesCommandeObservableList.addAll(list);
				calculerTotal();
				formUpdateLigneCommande.vente
						.setTotal(Double.valueOf(formUpdateLigneCommande.totalLabelValue.getText()));
				pdaoVente.update(formUpdateLigneCommande.vente);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Ajouter un élément");
				alert.setHeaderText(null);
				alert.setContentText("Un nouveau élément a été ajouté");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.initOwner(formUpdateLigneCommande.window);

				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erreur");
				alert.setHeaderText("Verifier la quantité saisie");
				alert.setContentText("Error la quantité vendue est supérieur à la quantité en stock");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.initOwner(formUpdateLigneCommande.window);

				alert.showAndWait();
			}
		}
	}

	public void deleteClick(int id) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Voulez vous vraiment supprimer cet élément?");
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner(formUpdateLigneCommande.window);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			pdao.delete(Long.valueOf(formUpdateLigneCommande.ligneCommandeIdTextField.getText()));
			formUpdateLigneCommande.lignesCommandeObservableList.remove(id);
			calculerTotal();
			formUpdateLigneCommande.vente.setTotal(Double.valueOf(formUpdateLigneCommande.totalLabelValue.getText()));
			pdaoVente.update(formUpdateLigneCommande.vente);
		} else {
			alert.close();
		}
	}

	public void updateLignesCommandeListWindows() {
		List<LigneCommande> list = pdao.getAll(idVente);
		formUpdateLigneCommande.lignesCommandeObservableList.addAll(list);
		calculerTotal();
	}

	private void calculerTotal() {
		double total = 0;
		if (formUpdateLigneCommande.lignesCommandeObservableList.isEmpty()) {
			formUpdateLigneCommande.totalLabelValue.setText(total + "");
		} else {
			for (LigneCommande l : formUpdateLigneCommande.lignesCommandeObservableList) {
				total += l.getSousTotal();
				formUpdateLigneCommande.totalLabelValue.setText(total + "");
			}
		}
	}
}
