import java.sql.*;
import javax.swing.*;

//import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
public class Connect {

	private Connection con;
	Connect(){
		init();
		
	}
	public static void main(String[] args) {
		Connection con; 
	}
	public void init(){
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:ATMAccounts.sqlite");
			//JOptionPane.showMessageDialog(null, "Connectd to the database");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "database not found");
		}
	}
	public boolean admin(String user, String pass){
		try{
			String login = "select * from AdminInfo where UserName =? and Password =?";
			PreparedStatement state = con.prepareStatement(login);
			state.setString(1, user);
			state.setString(2, pass);
			return true;
		}catch (Exception e){
			return false;
		}
		
		
	}
	public boolean login(String user, String pass){
		try{
			String login = "select * from UserInfo where UserName = ? and Password = ?";
			PreparedStatement state = con.prepareStatement(login);
			state.setString(1, user);
			state.setString(2, pass);
			ResultSet rs = state.executeQuery();
			if (rs.next()){
				JOptionPane.showMessageDialog(null, "Welcome!");
				return true;
			}
			else{
				JOptionPane.showMessageDialog(null, "Wrong Username/password");
				return false;
			}
		}catch (Exception r){
			JOptionPane.showMessageDialog(null, r);
			return false;
		}		
	}
	public ResultSet search(JTextField text, JComboBox j, String detail){
		try{
			String selection = (String) j.getSelectedItem();
			String selected = text.getText();
			String query = "Select "+ detail + " from Userinfo where " + selection + " like '%" + selected + "%'";
			PreparedStatement pst = con.prepareStatement(query);
			//pst.setString(1, text.getText());
			ResultSet rs = pst.executeQuery();
			return rs;
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null, e);	
			return null;
		}	
	}
	public ResultSet select(){
		try{
			String query = "Select * from UserInfo";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			return rs;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Database Error select");
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	public ResultSet select(String sele, String dependant){
		try{
		String query = "Select " + sele + " from UserInfo where " + dependant;
		PreparedStatement pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
		return rs;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	public boolean update (String query){
		System.out.println("tryin to update");
		try{
			
				String quer = "UPDATE UserInfo Set " + query;
				System.out.println(quer);
				PreparedStatement pst = con.prepareStatement(quer);
				pst.execute();		
				JOptionPane.showConfirmDialog(null, "Info Updated");
				return true;
		}catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
				return false;
		}
	}
}
