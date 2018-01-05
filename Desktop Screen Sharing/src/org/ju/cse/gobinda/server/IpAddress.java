package org.ju.cse.gobinda.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpAddress {

	public static String getIpAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "NOT FOUND";
		}
	}
}
