package org.ju.cse.gobinda.client;

import java.net.ServerSocket;
import java.net.Socket;

public class AliveConnection implements Runnable {

	private boolean aliveStatus = true;

	@Override
	public void run() {

		try {
			ServerSocket serverSocket = new ServerSocket(Main.CLIENT_ALIVE_PORT);
			while (aliveStatus) {
				Socket sock = serverSocket.accept();
				sock.close();
			}
			serverSocket.close();
		} catch (Exception e) {
		}
	}

	public void setAlive(boolean aliveStatus) {
		this.aliveStatus = aliveStatus;
	}

}