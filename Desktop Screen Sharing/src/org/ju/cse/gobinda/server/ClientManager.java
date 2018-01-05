package org.ju.cse.gobinda.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

public class ClientManager {

	private static Vector<Clients> clients = null;

	public ClientManager() {
		clients = new Vector<Clients>();
	}

	public static void addClients(Clients client) {
		clients.add(client);
	}

	public static void refreshClientsList() {

		int len = clients.size();
		MainFrame.clearClientList();
		for (int i = 0; i < len; i++) {
			try {
				Socket socket = new Socket(clients.get(i).getIp(), Clients.clientAlivePort);
				MainFrame.addClients(clients.get(i).getName());
				socket.close();
			} catch (UnknownHostException e) {
				clients.remove(i);
				e.printStackTrace();
			} catch (IOException e) {
				clients.remove(i);
				e.printStackTrace();
			}
			len = clients.size();
		}

	}

	public static String getClientsIpFromClientsList(String name) {
		// TODO Auto-generated method stub
		for (Clients cc : clients) {
			if (cc.getName().equals(name)) {
				return cc.getIp();
			}
		}
		return null;
	}

}
