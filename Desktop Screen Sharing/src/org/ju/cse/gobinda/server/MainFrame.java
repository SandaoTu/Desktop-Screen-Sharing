package org.ju.cse.gobinda.server;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tIpField;
	private JTextField tPortSettingField;
	private static JTextArea clientList = null;
	private JTextField tClientsNameField;

	public static PhotoViewer photoViewer = null;
	public static JLabel countingPhotos = null;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 307);
		// setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("IP_ADDRESS", null, panel, null);
		panel.setLayout(null);

		tIpField = new JTextField();
		tIpField.setEditable(false);
		tIpField.setHorizontalAlignment(SwingConstants.CENTER);
		tIpField.setText("127.0.0.1");
		tIpField.setFont(new Font("Tahoma", Font.BOLD, 24));
		tIpField.setBounds(10, 49, 402, 35);
		panel.add(tIpField);
		tIpField.setColumns(10);

		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tIpField.setText(IpAddress.getIpAddress());
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRefresh.setBounds(10, 95, 402, 35);
		panel.add(btnRefresh);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("SERVER", null, panel_1, null);
		panel_1.setLayout(null);

		tPortSettingField = new JTextField();
		tPortSettingField.setFont(new Font("Tahoma", Font.BOLD, 20));
		tPortSettingField.setText("55555");
		tPortSettingField.setHorizontalAlignment(SwingConstants.CENTER);
		tPortSettingField.setBounds(10, 80, 402, 40);
		panel_1.add(tPortSettingField);
		tPortSettingField.setColumns(10);

		JButton btnNewButton_1 = new JButton("SET PORT");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBounds(10, 29, 402, 40);
		panel_1.add(btnNewButton_1);

		JButton btnStart = new JButton("START SERVER");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Server(Integer.parseInt(tPortSettingField.getText()))).start();
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStart.setBounds(10, 131, 194, 51);
		panel_1.add(btnStart);

		JButton btnStop = new JButton("STOP SERVER");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Server.stopServer();
			}
		});
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnStop.setBounds(218, 131, 194, 51);
		panel_1.add(btnStop);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("CLIENTS", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		clientList = new JTextArea();
		scrollPane.setViewportView(clientList);

		JButton btnNewButton_2 = new JButton("REFRESH");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientManager.refreshClientsList();
			}
		});
		panel_2.add(btnNewButton_2, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Output", null, panel_3, null);
		panel_3.setLayout(null);

		JButton btnNewButton = new JButton("Full Screen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				photoViewer = new PhotoViewer(tClientsNameField.getText());
				photoViewer.setVisible(true);

				new Thread(new PhotoViewerControler()).start();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(118, 110, 187, 35);
		panel_3.add(btnNewButton);

		tClientsNameField = new JTextField();
		tClientsNameField.setHorizontalAlignment(SwingConstants.CENTER);
		tClientsNameField.setFont(new Font("Tahoma", Font.BOLD, 18));
		tClientsNameField.setBounds(10, 60, 412, 35);
		panel_3.add(tClientsNameField);
		tClientsNameField.setColumns(10);

		JButton btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhotoViewerControler.cholbeKina = false;
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnClose.setBounds(235, 194, 187, 35);
		panel_3.add(btnClose);

		countingPhotos = new JLabel("0");
		countingPhotos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		countingPhotos.setBounds(10, 194, 187, 35);
		panel_3.add(countingPhotos);

		setLocationRelativeTo(null);
	}

	public static void addClients(String name) {
		clientList.append(name + "\n");
	}

	public static void clearClientList() {
		clientList.setText("");
	}
}
