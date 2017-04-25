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

public class AdminTable2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	//Connection con = null;
	private JScrollPane scrollPane;
	private JLabel lblSearch;
	private JTextField searchBox;
	private JButton btnEditInfo_1;
	private Connect data = new Connect();
	private AdminTable2 frame;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*try {
					AdminTable2 frame = new AdminTable2();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setUndecorated(true);
					frame.setLocationRelativeTo(null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public AdminTable2() {
		try {
			frame = new AdminTable2();
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setUndecorated(true);
			frame.setLocationRelativeTo(null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(237, 45, 494, 379);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		
		JButton btnShowTable = new JButton("REFRESH");
		btnShowTable.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnShowTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					table.setModel(DbUtils.resultSetToTableModel(data.select()));
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		btnShowTable.setBounds(394, 11, 104, 23);
		contentPane.add(btnShowTable);
		
		JButton newUser = new JButton("New Account");
		newUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newAct open = new newAct();
				open.setVisible(true);
			}
		});
		newUser.setBounds(10, 118, 99, 23);
		contentPane.add(newUser);
		
		lblSearch = new JLabel("Search By");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSearch.setBounds(10, 11, 177, 23);
		contentPane.add(lblSearch);
		
		JComboBox search = new JComboBox();
		search.setModel(new DefaultComboBoxModel(new String[] {"UserID", "UserName", "FirstName", "LastName", "SS"}));
		search.setBounds(10, 39, 217, 29);
		contentPane.add(search);
		
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
		searchBox.setBounds(10, 68, 217, 29);
		contentPane.add(searchBox);
		searchBox.setColumns(10);
		
		btnEditInfo_1 = new JButton("Edit Info");
		btnEditInfo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditInfo info = new EditInfo();
				info.setVisible(true);
				
			}
		});
		btnEditInfo_1.setBounds(119, 118, 108, 23);
		contentPane.add(btnEditInfo_1);
		
		
	}
}
