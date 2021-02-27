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

public class PaiementsAddHandler {
	IPaiementDAO pdao = new PaiementDaoImpl();
	IVenteDAO pdaoVente = new VenteDaoImpl();
	PaiementsFormWindow formPaiement = null;
	ReglementFormWindow formReglement = null;
	ClientProgram clientProgram = null;
	Vente vente = null;
	double reste = 0;
	double total = 0;
	long idVente = 0;

	public PaiementsAddHandler() {
	}

	public PaiementsAddHandler(PaiementsFormWindow formPaiement) {
		this.formPaiement = formPaiement;
	}

	public void setFormReglement(ReglementFormWindow formReglement) {
		this.formReglement = formReglement;
	}

	public void setId_vente(long idVente) {
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
		double montant = Double.valueOf(formPaiement.paiementMontantTextField.getText());
		LocalDate date = formPaiement.paiementDateSaisieDatePicker.getValue();
		String type = formPaiement.paiementTypeComboBox.getValue();
		String numeroCheque = formPaiement.paiementNumeroChequeTextField.getText();
		LocalDate dateEcheance = formPaiement.paiementDateEcheanceDatePicker.getValue();
		String proprietaire = formPaiement.paiementProprietaireTextField.getText();
		String banque = formPaiement.paiementBanqueComboBox.getValue();
		if (date != null && !formPaiement.paiementMontantTextField.getText().equals("") && type != null
				&& type.equals("ESPECE") && numeroCheque.equals("") && dateEcheance == null && proprietaire.equals("")
				&& banque == null && formPaiement.paiementNumeroCompteBancaireTextField.getText().equals("")
				&& montant <= Double.valueOf(formPaiement.resteValueLabel.getText()) && montant > 0) {
			Paiement p = new Paiement(0, montant, date, type, numeroCheque, dateEcheance, proprietaire, banque,
					idVente);
			id = ((PaiementDaoImpl) pdao).addPaiement(p);
			p.setId(id);
			List<Paiement> list = new ArrayList<>();
			list.add(p);
			formPaiement.paiementsObservableList.addAll(list);
			updateTotalPaye();
			vente.setPaye(Double.valueOf(formPaiement.totalPayeValueLabel.getText()));
			pdaoVente.update(vente);
			this.reste = Double.valueOf(formPaiement.totalValueLabel.getText())
					- Double.valueOf(formPaiement.totalPayeValueLabel.getText());
			formPaiement.resteValueLabel.setText(String.valueOf(this.reste));
			addClick();
			updateVentesListWindows();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Ajouter un élément");
			alert.setHeaderText(null);
			alert.setContentText("Un nouveau élément a été ajouté");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(formPaiement.window);

			alert.showAndWait();
		}
	}

	public void saveClick2(long idVente) {
		long id = 0;
		double montant = Double.valueOf(formPaiement.paiementMontantTextField.getText());
		LocalDate date = formPaiement.paiementDateSaisieDatePicker.getValue();
		String type = formPaiement.paiementTypeComboBox.getValue();
		String numeroCheque = formPaiement.paiementNumeroChequeTextField.getText();
		LocalDate dateEcheance = formPaiement.paiementDateEcheanceDatePicker.getValue();
		String proprietaire = formPaiement.paiementProprietaireTextField.getText();
		String banque = formPaiement.paiementBanqueComboBox.getValue();
		if (date != null && !formPaiement.paiementMontantTextField.getText().equals("") && type != null
				&& type.equals("CHEQUE") && !numeroCheque.equals("") && dateEcheance != null && !proprietaire.equals("")
				&& banque != null && formPaiement.paiementNumeroCompteBancaireTextField.getText().equals("")
				&& montant <= Double.valueOf(formPaiement.resteValueLabel.getText()) && montant > 0) {
			Paiement p = new Paiement(0, montant, date, type, numeroCheque, dateEcheance, proprietaire, banque,
					idVente);
			id = ((PaiementDaoImpl) pdao).addPaiement2(p);
			p.setId(id);
			List<Paiement> list = new ArrayList<>();
			list.add(p);
			formPaiement.paiementsObservableList.addAll(list);
			updateTotalPaye();
			vente.setPaye(Double.valueOf(formPaiement.totalPayeValueLabel.getText()));
			pdaoVente.update(vente);
			this.reste = Double.valueOf(formPaiement.totalValueLabel.getText())
					- Double.valueOf(formPaiement.totalPayeValueLabel.getText());
			formPaiement.resteValueLabel.setText(String.valueOf(this.reste));
			updateVentesListWindows();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Ajouter un élément");
			alert.setHeaderText(null);
			alert.setContentText("Un nouveau élément a été ajouté");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(formPaiement.window);

			alert.showAndWait();
		}
		addClick();
	}

	public void payClick(long idVente) {
		long id = 0;
		double montant = Double.valueOf(formPaiement.paiementMontantTextField.getText());
		LocalDate date = formPaiement.paiementDateSaisieDatePicker.getValue();
		String type = formPaiement.paiementTypeComboBox.getValue();
		String numeroCheque = formPaiement.paiementNumeroChequeTextField.getText();
		LocalDate dateEcheance = formPaiement.paiementDateEcheanceDatePicker.getValue();
		String proprietaire = formPaiement.paiementProprietaireTextField.getText();
		String banque = formPaiement.paiementBanqueComboBox.getValue();
		String numeroCompte = formPaiement.paiementNumeroCompteBancaireTextField.getText();
		if (date != null && !formPaiement.paiementMontantTextField.getText().equals("") && type != null
				&& type.equals("CARTE BANCAIRE") && numeroCheque.equals("") && dateEcheance == null
				&& proprietaire.equals("") && banque == null && !numeroCompte.equals("")
				&& montant <= Double.valueOf(formPaiement.resteValueLabel.getText()) && montant > 0) {
			String msg = sendClassToString(this);
			String tab[] = { numeroCompte, formPaiement.paiementMontantTextField.getText(), msg, "add" };
			ClientProgram.main(tab);
			if (!clientProgram.getMessage().equals("Error le compte n'existe pas")
					&& !clientProgram.getMessage().equals("Error le montant est supérieur au solde")
					&& !clientProgram.getMessage().equals("Vous n'êtes pas connécté au serveur")) {
				Paiement p = new Paiement(0, montant, date, type, numeroCheque, dateEcheance, proprietaire, banque,
						idVente);
				id = ((PaiementDaoImpl) pdao).addPaiement(p);
				p.setId(id);
				List<Paiement> list = new ArrayList<>();
				list.add(p);
				formPaiement.paiementsObservableList.addAll(list);
				updateTotalPaye();
				vente.setPaye(Double.valueOf(formPaiement.totalPayeValueLabel.getText()));
				pdaoVente.update(vente);
				this.reste = Double.valueOf(formPaiement.totalValueLabel.getText())
						- Double.valueOf(formPaiement.totalPayeValueLabel.getText());
				formPaiement.resteValueLabel.setText(String.valueOf(this.reste));
				updateVentesListWindows();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Effectuer un paiement");
				alert.setHeaderText(null);
				alert.setContentText("Le paiement a été effectué avec succès");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.initOwner(formPaiement.window);

				alert.showAndWait();
			} else {
				if (!clientProgram.getMessage().equals("Vous n'êtes pas connécté au serveur")) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setHeaderText("Vérifiez les informations entrées");
					alert.setContentText(clientProgram.getMessage());
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(formPaiement.window);

					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Erreur");
					alert.setHeaderText("Connectez vous au serveur");
					alert.setContentText(clientProgram.getMessage());
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.initOwner(formPaiement.window);

					alert.showAndWait();
				}
			}
		}
		addClick();
	}

	public void deleteClick(int id) {
		if (!formPaiement.paiementsObservableList.get(id).getType().equals("CARTE BANCAIRE")) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Supprimer un élément");
			alert.setHeaderText(null);
			alert.setContentText("Voulez vous vraiment supprimer cet élément?");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(formPaiement.window);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				pdao.delete(Long.valueOf(formPaiement.idPaiementTextField.getText()));
				formPaiement.paiementsObservableList.remove(id);
				updateTotalPaye();
				vente.setPaye(Double.valueOf(formPaiement.totalPayeValueLabel.getText()));
				pdaoVente.update(vente);
				this.reste = Double.valueOf(formPaiement.totalValueLabel.getText())
						- Double.valueOf(formPaiement.totalPayeValueLabel.getText());
				formPaiement.resteValueLabel.setText(String.valueOf(this.reste));
				addClick();
				updateVentesListWindows();
			} else {
				alert.close();
			}
		}
	}

	public void addClick() {
		formPaiement.paiementMontantTextField.setText(formPaiement.resteValueLabel.getText());
		formPaiement.paiementDateSaisieDatePicker.setValue(null);
		formPaiement.paiementTypeComboBox.setValue(null);
		formPaiement.paiementTypeComboBox.setPromptText("Type de paiement");
		formPaiement.paiementNumeroChequeTextField.setText("");
		formPaiement.paiementDateEcheanceDatePicker.setValue(null);
		formPaiement.paiementBanqueComboBox.setValue(null);
		formPaiement.paiementProprietaireTextField.setText("");
		formPaiement.paiementNumeroCompteBancaireTextField.setText("");
	}

	private void calculerTotal() {
		double total = 0;
		if (formReglement.ventesObservableList.isEmpty()) {
			formReglement.totalLabelValue.setText(total + "");
		} else {
			for (Vente v : formReglement.ventesObservableList) {
				total += v.getTotal();
				formReglement.totalLabelValue.setText(total + "");
			}
		}
	}

	public void updateTotalPaye() {
		this.total = 0;
		List<Paiement> list = pdao.getAll(this.idVente);
		if (list.isEmpty()) {
			formPaiement.totalPayeValueLabel.setText(this.total + "");
		} else {
			for (Paiement p : list) {
				this.total += p.getMontant();
			}
			formPaiement.totalPayeValueLabel.setText(this.total + "");
		}
	}

	public void updateVentesListWindows() {
		List<Vente> list = new ArrayList<>();
		list = pdaoVente.getAll();
		formReglement.ventesObservableList.setAll(list);
		calculerTotal();
	}

	public void updatClientInformations() {
		formPaiement.clientVenteValueLabel.setText(String.valueOf(vente.getNom() + " " + vente.getPrenom()));
		formPaiement.idVenteValueLabel.setText(String.valueOf(vente.getId()));
		formPaiement.dateVenteValueLabel.setText(String.valueOf(vente.getDate()));
		formPaiement.totalValueLabel.setText(String.valueOf(vente.getTotal()));
		updateTotalPaye();
		formPaiement.resteValueLabel.setText(String.valueOf(Double.valueOf(vente.getTotal()) - this.total));
		formPaiement.paiementMontantTextField.setText(formPaiement.resteValueLabel.getText());
	}

	public String sendClassToString(PaiementsAddHandler obj) {
		final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String jsonString = gson.toJson(obj);
		return jsonString;
	}
}
