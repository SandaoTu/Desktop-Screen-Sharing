package org.ju.cse.gobinda.server;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Photos extends JPanel {

	private static final long serialVersionUID = 1L;
	public static String ip = null;
	public static int photoPort = 55557;

	public Photos(String name) {
		ip = ClientManager.getClientsIpFromClientsList(name);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(getRemotePhoto(), -1, -1, null);
	}

	public static BufferedImage getRemotePhoto() {
		try {
			Socket socket = new Socket(ip, photoPort);
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			BufferedImage screenshot = ImageIO.read(in);
			socket.close();
			return screenshot;
		} catch (Exception ex) {
			MainFrame.photoViewer.setVisible(false);
			PhotoViewerControler.cholbeKina = false;
		}
		return null;
	}

}