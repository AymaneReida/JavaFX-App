package com.step1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

import daofx.Client;
import daofx.ClientDaoImpl;
import daofx.Compte;
import daofx.CompteDaoImpl;
import daofx.IClientDAO;
import daofx.ICompteDAO;
import daofx.IOperationDAO;
import daofx.Operation;
import daofx.OperationDaoImpl;

public class ServerProgram {
	private static final int port = 3333;
	private ServerSocket serverSocket = null;
	private Socket socketEnd1 = null;

	public ServerProgram() {
		try {
			System.out.println("server .....");
			serverSocket = new ServerSocket(port);
			System.out.println("server started.....");
		} catch (Exception exp) {
		}
	}

	public void accepterConnexion() {
		try {
			System.out.println("attente de demande.....");
			socketEnd1 = serverSocket.accept();
			ServerThread st=new ServerThread(socketEnd1);
			st.start();
			System.out.println("connexion établie.....");
		} catch (Exception exp) {
		}
	}

	public static void main(String[] args) {
		/*
		 * try { InetAddress adresse = InetAddress.getLocalHost();
		 * System.out.println("nom : " + adresse.getHostName());
		 * System.out.println("adresse ip : " + adresse.getHostAddress());
		 * 
		 * InetAddress adresse2 = InetAddress.getByName("www.enset-media.ac.ma");
		 * System.out.println("nom : " + adresse2.getHostName());
		 * System.out.println("adresse ip : " + adresse2.getHostAddress()); } catch
		 * (Exception exp) { }
		 */

		ServerProgram server = new ServerProgram();
		while(true) {
			server.accepterConnexion();
		}
	}
}
