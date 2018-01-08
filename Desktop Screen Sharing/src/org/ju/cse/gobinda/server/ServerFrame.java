package org.ju.cse.gobinda.server;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;

public class ServerFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField tIpField;
	private JList<String> clientList = null;
	public ClientDisplay clientDisplay = null;
	private Server server;
	public JLabel countingPhotos = null;

	public ServerFrame() {

		super();
		server = new Server();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel optionPanel = new JPanel();
		optionPanel.setBounds(5, 5, 144, 694);
		contentPane.add(optionPanel);
		optionPanel.setLayout(null);

		tIpField = new JTextField();
		tIpField.setBounds(10, 11, 124, 35);
		optionPanel.add(tIpField);
		tIpField.setEditable(false);
		tIpField.setHorizontalAlignment(SwingConstants.CENTER);
		tIpField.setText("127.0.0.1");
		tIpField.setFont(new Font("David", Font.BOLD, 16));
		tIpField.setColumns(10);

		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.setBounds(10, 49, 124, 35);
		optionPanel.add(btnRefresh);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tIpField.setText(Server.getServerIpAddress());
			}
		});
		btnRefresh.setFont(new Font("David", Font.BOLD, 16));

		JButton btnNewButton_1 = new JButton("ON-OFF");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(10, 126, 124, 40);
		optionPanel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("David", Font.BOLD, 16));

		JButton btnStart = new JButton("START SERVER");
		btnStart.setForeground(Color.GREEN);
		btnStart.setBounds(10, 169, 60, 36);
		optionPanel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server.startServer();
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 18));

		JButton btnStop = new JButton("STOP SERVER");
		btnStop.setForeground(Color.RED);
		btnStop.setBounds(74, 169, 60, 36);
		optionPanel.add(btnStop);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.stopServer();
			}
		});
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 18));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(8, 246, 128, 391);
		optionPanel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		clientList = new JList<>();
		clientList.setModel(new DefaultListModel<String>());
		scrollPane.setViewportView(clientList);

		JButton btnNewButton_2 = new JButton("REFRESH");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshClientsList();
			}
		});
		panel_2.add(btnNewButton_2, BorderLayout.NORTH);

		JButton button = new JButton("START SERVER");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientDisplay.startDisplay(server.getClientFromClientsList(clientList.getSelectedValue()));
			}
		});
		button.setForeground(Color.GREEN);
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.setBounds(10, 648, 60, 36);
		optionPanel.add(button);

		JButton button_1 = new JButton("STOP SERVER");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientDisplay.stopDisplay();
			}
		});
		button_1.setForeground(Color.RED);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		button_1.setBounds(74, 648, 60, 36);
		optionPanel.add(button_1);

		clientDisplay = new ClientDisplay();
		clientDisplay.setBounds(155, 5, 1200, 694);
		contentPane.add(clientDisplay);

		setLocationRelativeTo(null);
	}

	public void addClients(String name) {
		DefaultListModel<String> dlm = (DefaultListModel<String>) clientList.getModel();
		dlm.addElement(name);
		clientList.setModel(dlm);
	}

	public void refreshClientsList() {
		try {
			clientList.setModel(new DefaultListModel<String>());
			DefaultListModel<String> dml = new DefaultListModel<>();
			ArrayList<Client> cc = server.getAliveClientsOnly();
			for (Client c : cc) {
				dml.addElement(c.getName());
			}
			clientList.setModel(dml);
		} catch (Exception e) {
		}

	}
}
