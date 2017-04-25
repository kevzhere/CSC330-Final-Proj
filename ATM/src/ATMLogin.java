import java.awt.*;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class ATMLogin {

	private JFrame frame;
	private JTextField TextUse;
	private JPasswordField TextPas;
	//private Connection connected;
	private JTextField searchBox;
	private JTable table;
	private JPanel AdminTable;
	private Connect data = new Connect();
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMLogin window = new ATMLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ATMLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 420, 301);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel Front = new JPanel();
		frame.getContentPane().add(Front, "name_1068403775840704");
		Front.setLayout(null);
		
		JLabel lblAtm = new JLabel("ATM");
		lblAtm.setBounds(164, 62, 46, 14);
		Front.add(lblAtm);
		lblAtm.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnAdmin = new JButton("Admin Login");
		btnAdmin.setBounds(60, 119, 111, 23);
		Front.add(btnAdmin);
		
		JButton btnUserlogin = new JButton("UserLogin");
		btnUserlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUserlogin.setBounds(212, 119, 100, 23);
		Front.add(btnUserlogin);
		
		JPanel Login = new JPanel();
		frame.getContentPane().add(Login, "name_1068462898070545");
		Login.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(90, 84, 85, 14);
		Login.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(90, 117, 85, 14);
		Login.add(lblPassword);
		
		TextUse = new JTextField();
		TextUse.setBounds(184, 83, 148, 20);
		Login.add(TextUse);
		TextUse.setColumns(10);
		
		TextPas = new JPasswordField();
		TextPas.setBounds(185, 116, 148, 15);
		Login.add(TextPas);
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if (data.admin(TextUse.getText(), TextPas.getText())){
						JOptionPane.showMessageDialog(null, "Welcome!");
						Login.setVisible(false);
						frame.setBounds(100, 100, 766, 488);
						AdminTable.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
					else
						JOptionPane.showMessageDialog(null, "Wrong Username/password");
			}
		});
		btnLogin.setBounds(203, 160, 89, 23);
		Login.add(btnLogin);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(237, 59, 492, 377);
		AdminTable.add(scrollPane);
		searchBox.setColumns(10);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(new Dimension(0, 0));
		table.setFillsViewportHeight(true);
		
		AdminTable = new JPanel();
		frame.getContentPane().add(AdminTable, "name_1068683473772532");
		AdminTable.setLayout(null);
		
		label = new JLabel("Search By");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(10, 25, 177, 23);
		AdminTable.add(label);
		
		JComboBox search = new JComboBox();
		search.setModel(new DefaultComboBoxModel(new String[] {"UserID", "UserName", "FirstName", "LastName", "SS"}));
		search.setBounds(10, 39, 217, 29);
		AdminTable.add(search);
		
		searchBox = new JTextField();
		searchBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try{
					table.setModel(DbUtils.resultSetToTableModel(data.search(searchBox, search, "*")));
				}catch (Exception ex){
					ex.printStackTrace();
				}
			}
		});
		searchBox.setBounds(10, 88, 217, 29);
		AdminTable.add(searchBox);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					table.setModel(DbUtils.resultSetToTableModel(data.select()));
				}catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnRefresh.setBounds(433, 25, 104, 23);
		AdminTable.add(btnRefresh);
		
		JButton button_1 = new JButton("New Account");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newAct open = new newAct();
				open.setVisible(true);
				open.setLocationRelativeTo(null);
			}
		});
		button_1.setBounds(10, 129, 99, 23);
		AdminTable.add(button_1);
		
		JButton button_2 = new JButton("Edit Info");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditInfo info = new EditInfo();
				info.setVisible(true);
				info.setLocationRelativeTo(null);
			}
		});
		button_2.setBounds(119, 129, 108, 23);
		AdminTable.add(button_2);
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Front.setVisible(false);
				Login.setVisible(true);
				
			}
		});
	}
}
