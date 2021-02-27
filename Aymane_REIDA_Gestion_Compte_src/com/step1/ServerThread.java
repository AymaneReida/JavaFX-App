package com.step1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;

import daofx.ClientDaoImpl;
import daofx.Compte;
import daofx.CompteDaoImpl;
import daofx.IClientDAO;
import daofx.ICompteDAO;
import daofx.IOperationDAO;
import daofx.Operation;
import daofx.OperationDaoImpl;

public class ServerThread extends Thread {
	private Socket soc = null;
	private InputStream input = null;
	private OutputStream output = null;
	IClientDAO cdao = new ClientDaoImpl();
	ICompteDAO cptdao = new CompteDaoImpl();
	IOperationDAO odao = new OperationDaoImpl();
	BufferedReader br = null;
	PrintWriter pw = null;
	String numCompte = null;
	double total = 0.0;

	public ServerThread(Socket soc) throws IOException {
		this.soc = soc;
		input = soc.getInputStream();
		output = soc.getOutputStream();
		br = new BufferedReader(new InputStreamReader(input));
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output)), true);
	}

	@Override
	public void run() {
		String msg = sendInfosCompte();
		if (!msg.equals("Error le compte n'existe pas")) {
			debiter();
		}
		/*try {
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	public String sendInfosCompte() {
		try {
			// int num = input.read();

			numCompte = br.readLine();
			System.out.println("numéro de compte : " + numCompte);

			Compte compte = ((CompteDaoImpl) cptdao).getOne(numCompte);
			if (compte == null) {
				String msg = "Error le compte n'existe pas";
				pw.println(msg);
				System.out.println("Error le compte n'existe pas.....");
				close();
				return msg;
			} else {
				String msg = "Le compte existe";
				pw.println(msg);
				System.out.println("Le compte existe.....");
				return msg;
			}

			// String bienvenu = "Bienvenu " + cdao.getOne(num).getPrenom() + " " +
			// cdao.getOne(num).getNom();
			// Client client = cdao.getOne(num);
			// PrintWriter pw = new PrintWriter(output);
			// pw.write(bienvenu);
			// pw.flush();
			// pw.close();

			// pw.println(bienvenu);

			// ObjectOutputStream os = new ObjectOutputStream(output);
			// os.writeObject(client);
			// os.flush();
			// os.close();
			// oi.close();
		} catch (Exception exp) {
			System.out.println(exp);
		}
		return "";
	}
	
	public void debiter() {
		try {
			// ObjectInputStream oi = new ObjectInputStream(input);
			// double total = (Double) oi.readObject();

			total = Double.valueOf(br.readLine());
			System.out.println("total : " + total);
			double solde = ((CompteDaoImpl) cptdao).getOne(numCompte).getSolde();

			System.out.println("solde avant : " + solde);
			if (solde >= total) {
				((CompteDaoImpl) cptdao).debiter(numCompte, total);

				System.out.println("solde apres : " + ((CompteDaoImpl) cptdao).getOne(numCompte).getSolde());
				Operation operation = new Operation(0, ((CompteDaoImpl) cptdao).getOne(numCompte).getId(), "PDIST",
						total, LocalDate.now(), ((CompteDaoImpl) cptdao).getOne(numCompte).getNom(),
						((CompteDaoImpl) cptdao).getOne(numCompte).getPrenom());
				odao.add(operation);
				
				String msg = "Le compte a été débité";
				pw.println(msg);
				System.out.println("Le compte a été débité.....");
				close();
			} else {
				String msg = "Error le montant est supérieur au solde";
				pw.println(msg);
				System.out.println("Error le montant est supérieur au solde.....");
				close();
			}
		} catch (Exception exp) {
			System.out.println(exp);
		}
	}

	/*private void echangerMessages() throws IOException {
		String msg = "";
		while (!msg.equals("bye!")) {
			msg = br.readLine();
			System.out.println("client: " + msg);
		}
		System.out.println("communication terminée");
		close();
		}*/

	private void close() throws IOException {
		br.close();
		pw.close();
		soc.close();
		System.out.println("END");
	}
}
