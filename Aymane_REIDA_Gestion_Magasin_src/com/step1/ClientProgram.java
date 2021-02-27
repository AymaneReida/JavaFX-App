package com.step1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import application.PaiementUpdateHandler;
import application.PaiementsAddHandler;
import daofx.Client;

public class ClientProgram {
	private Socket socketEnd2 = null;
	private static final int port = 3333;
	private InputStream input = null;
	private OutputStream output = null;
	PrintWriter pw = null;
	BufferedReader br = null;
	private static String message = "";
	private static PaiementsAddHandler myObject = null;
	private static PaiementUpdateHandler object = null;

	public ClientProgram() {
		try {
			// 127.0.0.1 adresse du serveur
			socketEnd2 = new Socket("127.0.0.1", port);
			input = socketEnd2.getInputStream();
			output = socketEnd2.getOutputStream();
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output)), true);
			br = new BufferedReader(new InputStreamReader(input));
		} catch (Exception exp) {
			System.out.println(exp);
		}
	}

	public static String getMessage() {
		return message;
	}

	public void getInfosCompte(String numCompte) {
		// int num = 2;
		// num = "256B606060";
		try {
			// output.write(num);
			// Un PrintWriter possède toutes les opérations print classiques.
			// En mode auto-flush, le tampon est vidé (flush) à l'appel de println.
			pw.println(numCompte);

			String msg = br.readLine();
			if (msg.equals("Error le compte n'existe pas")) {
				this.message = msg;
				System.out.println(msg);
				pw.close();
				br.close();
				socketEnd2.close();
				System.out.println("END");
			} else {
				this.message = msg;
			}

			// ObjectInputStream oi = new ObjectInputStream(input);
			// Client client = (Client) oi.readObject();
			// System.out.println(client.getPrenom() + " " + client.getNom());
			// oi.close();
		} catch (Exception exp) {
			this.message = "Vous n'êtes pas connécté au serveur";
			System.out.println(exp);
		}
	}

	public void regler(String total) {

		try {

			pw.println(total);

			String msg = br.readLine();
			if (msg.equals("Error le montant est supérieur au solde")) {
				this.message = msg;
				System.out.println(msg);
			} else {
				this.message = msg;
			}
			pw.close();
			br.close();
			socketEnd2.close();
			System.out.println("END");

		} catch (Exception exp) {
			this.message = "Vous n'êtes pas connécté au serveur";
			System.out.println(exp);
		}
	}
	
	public PaiementsAddHandler updateStringToAddObject(String decodedString) {
		final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		myObject = gson.fromJson(decodedString , PaiementsAddHandler.class);
		return myObject;
	}
	
	public PaiementUpdateHandler updateStringToUpdateObject(String decodedString) {
		final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		object = gson.fromJson(decodedString , PaiementUpdateHandler.class);
		return object;
	}

	public static void main(String[] args) {
		ClientProgram client = new ClientProgram();
		if(args[3].equals("add")) {
			myObject = client.updateStringToAddObject(args[2]);
			myObject.setClientProgram(client);
		}
		if(args[3].equals("update")) {
			object = client.updateStringToUpdateObject(args[2]);
			object.setClientProgram(client);
		}
		client.getInfosCompte(args[0]);
		if (!message.equals("Error le compte n'existe pas")) {
			client.regler(args[1]);
		}
	}
}
