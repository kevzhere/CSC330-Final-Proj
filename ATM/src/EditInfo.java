import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.text.NumberFormatter;

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
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;

public class EditInfo extends JFrame {

	private JPanel contentPane;
	private JComboBox nameList;
	private JTextField textFirst;
	private JTextField textLast;
	private JTextField textSS;
	private JTextField textAdd;
	private JTextField textPho;
	private JLabel label_2;
	private JTextField textUse;
	private JTextField textPas;
	private JLabel label_3;
	private JTextField textPin;
	private JLabel label_4;
	private JButton btnUpdate;
	private Connect data = new Connect();
	private EditInfo frame;
	private JTextField textID;
	private JScrollPane scrollPane;
	private JFormattedTextField textAge;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*try {
					frame = new EditInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}*/
			}
		});
	}
	private void populate(){
		try{
			String first = nameList.getSelectedItem().toString().split(" ")[0];
			String last = nameList.getSelectedItem().toString().split(" ")[1];
			String sele = "*";
			String depend = "FirstName = '" + first + "' and LastName = '" + last + "'";
			ResultSet rs = data.select(sele, depend);
			textFirst.setText(rs.getString("FirstName"));
			textLast.setText(rs.getString("LastName"));
			textAge.setText(rs.getString("Age"));
			textSS.setText(rs.getString("SS"));
			textSS.disable();
			textAdd.setText(rs.getString("Address"));
			textPho.setText(rs.getString("Phone"));
			textUse.setText(rs.getString("UserName"));
			textPas.setText(rs.getString("Password"));
			textPin.setText(rs.getString("Pin"));
			textID.setText(rs.getString("UserID"));
			textID.disable();
			
			
		}catch (Exception e){
		}
	}
	public void resetNames(){
		nameList.removeAllItems();
		try{
			ResultSet rs = data.select();
			
			while(rs.next()){
				nameList.addItem(rs.getString("FirstName") + " " + rs.getString("LastName"));
			}
			System.out.println(rs.getString("FirstName"));
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	/**
	 * Create the frame.
	 */
	public EditInfo() {
		try {
			frame = new EditInfo();
			frame.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 205, 29);
		contentPane.add(scrollPane);
		
		nameList = new JComboBox();
		scrollPane.setViewportView(nameList);
		nameList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				populate();
			}
		});
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 64, 71, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(115, 61, 71, 14);
		contentPane.add(lblLastName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(222, 64, 46, 14);
		contentPane.add(lblAge);
		
		JLabel lblSocialSecurity = new JLabel("Social Security");
		lblSocialSecurity.setBounds(322, 64, 82, 14);
		contentPane.add(lblSocialSecurity);
		
		textFirst = new JTextField();
		textFirst.setBounds(10, 82, 86, 20);
		contentPane.add(textFirst);
		textFirst.setColumns(10);
		
		textLast = new JTextField();
		textLast.setBounds(115, 82, 86, 20);
		contentPane.add(textLast);
		textLast.setColumns(10);
		
		textSS = new JTextField();
		textSS.setBounds(322, 82, 86, 20);
		contentPane.add(textSS);
		textSS.setColumns(10);
		
		JLabel label = new JLabel("Address");
		label.setBounds(10, 113, 135, 14);
		contentPane.add(label);
		
		textAdd = new JTextField();
		textAdd.setColumns(10);
		textAdd.setBounds(10, 133, 293, 20);
		contentPane.add(textAdd);
		
		JLabel label_1 = new JLabel("Phone Number");
		label_1.setBounds(322, 113, 88, 14);
		contentPane.add(label_1);
		
		textPho = new JTextField();
		textPho.setColumns(10);
		textPho.setBounds(321, 133, 83, 20);
		contentPane.add(textPho);
		
		label_2 = new JLabel("User Name");
		label_2.setBounds(10, 164, 75, 14);
		contentPane.add(label_2);
		
		textUse = new JTextField();
		textUse.setColumns(10);
		textUse.setBounds(10, 189, 86, 20);
		contentPane.add(textUse);
		
		textPas = new JTextField();
		textPas.setColumns(10);
		textPas.setBounds(115, 189, 86, 20);
		contentPane.add(textPas);
		
		label_3 = new JLabel("Password");
		label_3.setBounds(115, 164, 80, 14);
		contentPane.add(label_3);
		
		textPin = new JTextField();
		textPin.setColumns(10);
		textPin.setBounds(222, 189, 86, 20);
		contentPane.add(textPin);
		
		label_4 = new JLabel("Pin");
		label_4.setBounds(222, 164, 46, 14);
		contentPane.add(label_4);
		
		//btnUpdate = new JButton("UPDATE");
		//btnUpdate.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent arg0) {
				/*System.out.println("update clicked");
				String first = nameList.getSelectedItem().toString().split(" ")[0];
				String last = nameList.getSelectedItem().toString().split(" ")[1];
				System.out.println(first + " " + last);
				String id = textID.getText();
				String query = "FirstName = '" + textFirst.getText() + "', LastName = '"+ textLast.getText() +
						"' WHERE UserID = " + id;
				System.out.println(query);
				if(data.update(query))
					resetNames();	*/
				//System.out.println("testing");
			//	}
		//});
		btnUpdate.setBounds(158, 220, 89, 23);
		contentPane.add(btnUpdate);
		
		JLabel userid = new JLabel("UserID");
		userid.setBounds(225, 28, 46, 14);
		contentPane.add(userid);
		
		textID = new JTextField();
		textID.setBounds(275, 25, 24, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    
	    formatter.setCommitsOnValidEdit(true);
		textAge = new JFormattedTextField(formatter);
		textAge.setBounds(222, 82, 82, 20);
		contentPane.add(textAge);
	}
}
