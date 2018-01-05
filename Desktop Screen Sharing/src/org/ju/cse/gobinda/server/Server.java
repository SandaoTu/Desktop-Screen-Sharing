package org.ju.cse.gobinda.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable {

	public static ServerSocket serverSocket = null;
	public static int port = 55555;
	public static boolean cholbeKina;

	public Server(int port) {
		Server.port = port;
		cholbeKina = true;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			serverSocket = new ServerSocket(port);
			while (cholbeKina) {
				Socket socket = serverSocket.accept();
				String name = getNameFromClients(socket);
				ClientManager.addClients(new Clients(socket, name));
				MainFrame.addClients(name);
			}
		} catch (Exception e) {

		}
	}

	private String getNameFromClients(Socket socket) {
		// TODO Auto-generated method stub
		try {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			String name = (String) in.readObject();
			return name;
		} catch (Exception e) {
		}
		return null;
	}

	public void setCholbeKina(boolean cholbeKina) {
		Server.cholbeKina = cholbeKina;
	}
	
	public static void stopServer() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
