package org.ju.cse.gobinda.client;

import java.net.ServerSocket;
import java.net.Socket;

public class AliveConnection implements Runnable {

	private int alivePort = 55556;
	private boolean aliveStatus = true;

	@Override
	public void run() {

		try {
			ServerSocket serverSocket = new ServerSocket(alivePort);
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