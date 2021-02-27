package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.step1.ClientProgram;

import daofx.IPaiementDAO;
import daofx.IVenteDAO;
import daofx.PaiementDaoImpl;
import daofx.Vente;
import daofx.VenteDaoImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import daofx.Paiement;

public class PaiementUpdateHandler {
	IPaiementDAO pdao = new PaiementDaoImpl();
	IVenteDAO pdaoVente = new VenteDaoImpl();
	PaiementsUpdateWindow updatePaiement = null;
	ReglementUpdateWindow updateReglement = null;
	ClientProgram clientProgram = null;
	Vente vente = null;
	double reste = 0;
	double total = 0;
	long idVente = 0;

	public PaiementUpdateHandler() {
	}

	public PaiementUpdateHandler(PaiementsUpdateWindow updatePaiement) {
		this.updatePaiement = updatePaiement;
	}

	public void setUpdateReglement(ReglementUpdateWindow updateReglement) {
		this.updateReglement = updateReglement;
	}

	public void setIdVente(long idVente) {
		this.idVente = idVente;
		Vente vente = pdaoVente.getOne(idVente);
		this.vente = vente;
		
	}

	public ClientProgram getClientProgram() {
		return clientProgram;
	}

	public void setClientProgram(ClientProgram clientProgram) {
		this.clientProgram = clientProgram;
	}

	public void saveClick(long idVente) {
		long id = 0;
		double montant = Double.valueOf(updatePaiement.paiementMontantTextField.getText());
		LocalDate date = updatePaiement.paiementDateSaisieDatePicker.getValue();
		String type = updatePaiement.paiementTypeComboBox.getValue();
		String numeroCheque = updatePaiement.paiementNumeroChequeTextField.getText();
		LocalDate dateEcheance = updatePaiement.paiementDateEcheanceDatePicker.getValue();
		String proprietaire = updatePaiement.paiementProprietaireTextField.getText();
		String banque = updatePaiement.paiementBanqueComboBox.getValue();
		if (date != null && !updatePaiement.paiementMontantTextField.getText().equals("") && type != null
				&& type.equals("ESPECE") && numeroCheque.equals("") && dateEcheance == null && proprietaire.equals("")
				&& banque == null && updatePaiement.paiementNumeroCompteBancaireTextField.getText().equals("")
				&& montant <= Double.valueOf(updatePaiement.resteValueLabel.getText()) && montant > 0) {
			Paiement p = new Paiement(0, montant, date, type, numeroCheque, dateEcheance, proprietaire, banque,
					idVente);
			id = ((PaiementDaoImpl) pdao).addPaiement(p);
			p.setId(id);
			List<Paiement> list = new ArrayList<>();
			list.add(p);
			updatePaiement.paiementsObservableList.addAll(list);
			updateTotalPaye();
			vente.setPaye(Double.valueOf(updatePaiement.totalPayeValueLabel.getText()));
			pdaoVente.update(vente);
			this.reste = Double.valueOf(updatePaiement.totalValueLabel.getText())
					- Double.valueOf(updatePaiement.totalPayeValueLabel.getText());
			updatePaiement.resteValueLabel.setText(String.valueOf(this.reste));
			addClick();
			updateVentesListWindows();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Ajouter un élément");
			alert.setHeaderText(null);
			alert.setContentText("Un nouveau élément a été ajouté");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(updatePaiement.window);

			alert.showAndWait();
		}
	}

	public void saveClick2(long id_vente) {
		long id = 0;
		double montant = Double.valueOf(updatePaiement.paiementMontantTextField.getText());
		LocalDate date = updatePaiement.paiementDateSaisieDatePicker.getValue();
		String type = updatePaiement.paiementTypeComboBox.getValue();
		String numeroCheque = updatePaiement.paiementNumeroChequeTextField.getText();
		LocalDate dateEcheance = updatePaiement.paiementDateEcheanceDatePicker.getValue();
		String proprietaire = updatePaiement.paiementProprietaireTextField.getText();
		String banque = updatePaiement.paiementBanqueComboBox.getValue();
		if (date != null && !updatePaiement.paiementMontantTextField.getText().equals("") && type != null
				&& type.equals("CHEQUE") && !numeroCheque.equals("") && dateEcheance != null && !proprietaire.equals("")
				&& banque != null && updatePaiement.paiementNumeroCompteBancaireTextField.getText().equals("")
				&& montant <= Double.valueOf(updatePaiement.resteValueLabel.getText()) && montant > 0) {
			Paiement p = new Paiement(0, montant, date, type, numeroCheque, dateEcheance, proprietaire, banque,
					id_vente);
			id = ((PaiementDaoImpl) pdao).addPaiement2(p);
			p.setId(id);
			List<Paiement> list = new ArrayList<>();
			list.add(p);
			updatePaiement.paiementsObservableList.addAll(list);
			updateTotalPaye();
			vente.setPaye(Double.valueOf(updatePaiement.totalPayeValueLabel.getText()));
			pdaoVente.update(vente);
			this.reste = Double.valueOf(updatePaiement.totalValueLabel.getText())
					- Double.valueOf(updatePaiement.totalPayeValueLabel.getText());
			updatePaiement.resteValueLabel.setText(String.valueOf(this.reste));
			updateVentesListWindows();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Ajouter un élément");
			alert.setHeaderText(null);
			alert.setContentText("Un nouveau élément a été ajouté");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(updatePaiement.window);

			alert.showAndWait();
		}
		addClick();
	}

	public void payClick(long id_vente) {
		long id = 0;
		double montant = Double.valueOf(updatePaiement.paiementMontantTextField.getText());
		LocalDate date = updatePaiement.paiementDateSaisieDatePicker.getValue();
		String type = updatePaiement.paiementTypeComboBox.getValue();
		String numeroCheque = updatePaiement.paiementNumeroChequeTextField.getText();
		LocalDate dateEcheance = updatePaiement.paiementDateEcheanceDatePicker.getValue();
		String proprietaire = updatePaiement.paiementProprietaireTextField.getText();
		String banque = updatePaiement.paiementBanqueComboBox.getValue();
		String numeroCompte = updatePaiement.paiementNumeroCompteBancaireTextField.getText();
		if (date != null && !updatePaiement.paiementMontantTextField.getText().equals("") && type != null
				&& type.equals("CARTE BANCAIRE") && numeroCheque.equals("") && dateEcheance == null
				&& proprietaire.equals("") && banque == null && !numeroCompte.equals("")
				&& montant <= Double.valueOf(updatePaiement.resteValueLabel.getText()) && montant > 0) {
			String msg = sendClassToString(this);
			String tab[] = { numeroCompte, updatePaiement.paiementMontantTextField.getText(), msg, "update" };
			ClientProgram.main(tab);
			if (!clientProgram.getMessage().equals("Error le compte n'existe pas")
					&& !clientProgram.getMessage().equals("Error le montant est supérieur au solde")
					&& !clientProgram.getMessage().equals("Vous n'êtes pas connécté au serveur")) {
				Paiement p = new Paiement(0, montant, date, type, numeroCheque, dateEcheance, proprietaire, banque,
						id_vente);
				id = ((PaiementDaoImpl) pdao).addPaiement(p);
				p.setId(id);
				List<Paiement> list = new ArrayList<>();
				list.add(p);
				updatePaiement.paiementsObservableList.addAll(list);
				updateTotalPaye();
				vente.setPaye(Double.valueOf(updatePaiement.totalPayeValueLabel.getText()));
				pdaoVente.update(vente);
				this.reste = Double.valueOf(updatePaiement.totalValueLabel.getText())
						- Double.valueOf(updatePaiement.totalPayeValueLabel.getText());
				updatePaiement.resteValueLabel.setText(String.valueOf(this.reste));
				updateVentesListWindows();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Effectuer un paiement");
				alert.setHeaderText(null);
				alert.setContentText("Le paiement a été effectué avec succès");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.initOwner(updatePaiement.window);

				alert.showAndWait();
			} else {
				if (!clientProgram.getMessage().equals("Vous n'êtes pas connécté au serveur")) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setHeaderText("Vérifiez les informations entrées");
					alert.setContentText(clientProgram.getMessage());
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(updatePaiement.window);

					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setHeaderText("Connectez vous au serveur");
					alert.setContentText(clientProgram.getMessage());
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(updatePaiement.window);

					alert.showAndWait();
				}
			}
		}
		addClick();
	}

	public void deleteClick(int id) {
		if (!updatePaiement.paiementsObservableList.get(id).getType().equals("CARTE BANCAIRE")) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Voulez vous vraiment supprimer cet élément?");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(updatePaiement.window);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				pdao.delete(Long.valueOf(updatePaiement.idPaiementTextField.getText()));
				updatePaiement.paiementsObservableList.remove(id);
				updateTotalPaye();
				vente.setPaye(Double.valueOf(updatePaiement.totalPayeValueLabel.getText()));
				pdaoVente.update(vente);
				this.reste = Double.valueOf(updatePaiement.totalValueLabel.getText())
						- Double.valueOf(updatePaiement.totalPayeValueLabel.getText());
				updatePaiement.resteValueLabel.setText(String.valueOf(this.reste));
				addClick();
				updateVentesListWindows();
			} else {
				alert.close();
			}
		}
	}

	public void addClick() {
		updatePaiement.paiementMontantTextField.setText(updatePaiement.resteValueLabel.getText());
		updatePaiement.paiementDateSaisieDatePicker.setValue(null);
		updatePaiement.paiementTypeComboBox.setValue(null);
		updatePaiement.paiementTypeComboBox.setPromptText("Type de paiement");
		updatePaiement.paiementNumeroChequeTextField.setText("");
		updatePaiement.paiementDateEcheanceDatePicker.setValue(null);
		updatePaiement.paiementBanqueComboBox.setValue(null);
		updatePaiement.paiementProprietaireTextField.setText("");
		updatePaiement.paiementNumeroCompteBancaireTextField.setText("");
	}

	private void calculerTotal() {
		double total = 0;
		if (updateReglement.ventesObservableList.isEmpty()) {
			updateReglement.totalLabelValue.setText(total + "");
		} else {
			for (Vente v : updateReglement.ventesObservableList) {
				total += v.getTotal();
				updateReglement.totalLabelValue.setText(total + "");
			}
		}
	}

	public void updateTotalPaye() {
		this.total = 0;
		List<Paiement> list = pdao.getAll(this.idVente);
		if (list.isEmpty()) {
			updatePaiement.totalPayeValueLabel.setText(this.total + "");
		} else {
			for (Paiement p : list) {
				this.total += p.getMontant();
			}
			updatePaiement.totalPayeValueLabel.setText(this.total + "");
		}
	}

	public void updatePaiementsListWindows() {
		this.total = 0;
		List<Paiement> list = pdao.getAll(this.idVente);
		if (list.isEmpty()) {
			updatePaiement.totalPayeValueLabel.setText(this.total + "");
		} else {
			for (Paiement p : list) {
				this.total += p.getMontant();
			}
			updatePaiement.totalPayeValueLabel.setText(this.total + "");
			updatePaiement.paiementsObservableList.addAll(list);
		}
	}

	public void updateVentesListWindows() {
		List<Vente> list = new ArrayList<>();
		list = pdaoVente.getAll();
		updateReglement.ventesObservableList.setAll(list);
		calculerTotal();
	}
	
	public void updatClientInformations() {
		updatePaiement.clientVenteValueLabel.setText(String.valueOf(vente.getNom() + " " + vente.getPrenom()));
		updatePaiement.idVenteValueLabel.setText(String.valueOf(vente.getId()));
		updatePaiement.dateVenteValueLabel.setText(String.valueOf(vente.getDate()));
		updatePaiement.totalValueLabel.setText(String.valueOf(vente.getTotal()));
		updateTotalPaye();
		updatePaiement.resteValueLabel.setText(String.valueOf(Double.valueOf(vente.getTotal()) - this.total));
		updatePaiement.paiementMontantTextField.setText(updatePaiement.resteValueLabel.getText());
	}

	public String sendClassToString(PaiementUpdateHandler obj) {
		final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String jsonString = gson.toJson(obj);
		return jsonString;
	}
}
