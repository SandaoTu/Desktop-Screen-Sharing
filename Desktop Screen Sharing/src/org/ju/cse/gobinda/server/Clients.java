package org.ju.cse.gobinda.server;

import java.net.Socket;

public class Clients {

	public static int clientAlivePort = 55556;
	private Socket socket = null;
	private String name;

	public Clients(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
	}

	public String getIp() {
		String ip = socket.getInetAddress().toString();
		return ip.substring(1, ip.length());
	}

	public String getName() {
		return name;
	}

}
