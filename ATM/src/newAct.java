import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class newAct extends JFrame {

	private JPanel contentPane;
	private JTextField first;
	private JTextField last;
	private JTextField age;
	private JTextField ss;
	private JTextField address;
	private JTextField phone;
	private JTextField user;
	private JTextField password;
	private JTextField balance;
	private Connection con;
	private JTextField pin;
	private static newAct newActFrame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newAct newActFrame = new newAct();
					newActFrame.setVisible(true);
					newActFrame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public newAct() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 424, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 11, 61, 14);
		contentPane.add(lblFirstName);
		
		first = new JTextField();
		first.setBounds(10, 27, 86, 20);
		contentPane.add(first);
		first.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(114, 11, 61, 14);
		contentPane.add(lblLastName);
		
		last = new JTextField();
		last.setBounds(111, 27, 86, 20);
		contentPane.add(last);
		last.setColumns(10);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(213, 11, 46, 14);
		contentPane.add(lblAge);
		
		age = new JTextField();
		age.setBounds(212, 27, 86, 20);
		contentPane.add(age);
		age.setColumns(10);
		
		JLabel lblSocialSecurity = new JLabel("Social Security");
		lblSocialSecurity.setBounds(311, 11, 134, 14);
		contentPane.add(lblSocialSecurity);
		
		ss = new JTextField();
		ss.setBounds(308, 27, 86, 20);
		contentPane.add(ss);
		ss.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 67, 135, 14);
		contentPane.add(lblAddress);
		
		address = new JTextField();
		address.setBounds(10, 84, 288, 20);
		contentPane.add(address);
		address.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(311, 67, 88, 14);
		contentPane.add(lblPhoneNumber);
		
		phone = new JTextField();
		phone.setBounds(311, 84, 83, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(10, 127, 75, 14);
		contentPane.add(lblUserName);
		
		user = new JTextField();
		user.setBounds(10, 143, 86, 20);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(111, 127, 80, 14);
		contentPane.add(lblPassword);
		
		password = new JTextField();
		password.setBounds(111, 143, 86, 20);
		contentPane.add(password);
		password.setColumns(10);
		
		JLabel lblBalance = new JLabel("Balance");
		lblBalance.setBounds(311, 127, 61, 14);
		contentPane.add(lblBalance);
		
		balance = new JTextField();
		balance.setBounds(308, 143, 86, 20);
		contentPane.add(balance);
		balance.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					con = SQLiteConnector.Connect();
					String eid = "select * from userinfo where Userid >= 0";
					PreparedStatement pst = con.prepareStatement(eid);
					ResultSet rs = pst.executeQuery();
					int id = 0;
					while(rs.next()){
						id++;
					}
					String query = "insert into UserInfo (UserID, Firstname, lastname, username, password," +
									" age, SS, pin, address, Phone, Balance) values (?,?,?,?,?,?,?,?,?,?,?)";
					pst = con.prepareStatement(query);
					pst.setString(1, Integer.toString(id));
					if (first.getText().equals("") || last.equals("")|| user.getText().equals("")|| password.equals("") || 
							age.equals("")|| ss.equals("")|| pin.equals("")|| address.equals("")|| phone.equals("")|| 
							balance.equals("")){
						JOptionPane.showMessageDialog(null, "Please fill out all fields.");
					}
					else{
						try{
							//check if input is numeric
							Float.parseFloat(age.getText());
							Float.parseFloat(pin.getText());
							Float.parseFloat(ss.getText());
							Float.parseFloat(phone.getText());
							Float.parseFloat(balance.getText());
							//setting values for saving into SQL database
							pst.setString(2, first.getText());
							pst.setString(3, last.getText());
							pst.setString(4, user.getText());
							pst.setString(5, password.getText());
							pst.setString(6, age.getText());
							pst.setString(7, ss.getText());
							pst.setString(8, pin.getText());
							pst.setString(9, address.getText());
							pst.setString(10, phone.getText());
							pst.setString(11, balance.getText());
							pst.execute();
							JOptionPane.showMessageDialog(null, "New Account Added");
							newActFrame.dispose();
						}
						catch (Exception d){
						JOptionPane.showMessageDialog(null,"Please only enter numbers for age, "
								+ "social security, pin, phone number, and balance.");
						}
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		btnSave.setBounds(155, 204, 89, 23);
		contentPane.add(btnSave);
		
		JLabel lblPin = new JLabel("Pin");
		lblPin.setBounds(213, 127, 46, 14);
		contentPane.add(lblPin);
		
		pin = new JTextField();
		pin.setBounds(213, 143, 86, 20);
		contentPane.add(pin);
		pin.setColumns(10);
	}

}
