package org.ju.cse.gobinda.server;


public class PhotoViewerControler implements Runnable {

	public static boolean cholbeKina = true;

	public PhotoViewerControler() {
		cholbeKina = true;
	}

	@Override
	public void run() {

		int counter = 0;
		while (cholbeKina) {
			try {
				MainFrame.photoViewer.panel.repaint();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MainFrame.countingPhotos.setText(String.valueOf(counter));
			counter++;
		}

	}

}
