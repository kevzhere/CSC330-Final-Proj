import java.sql.*;
import javax.swing.*;
public class SQLiteConnector {
	static Connection con = null;
	public static Connection Connect(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Kevinz\\Desktop\\Java\\Group proj\\ATMAccounts.sqlite");
			//JOptionPane.showConfirmDialog(null, "Connected??");
			return con;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	public static boolean login(String user, String pass){
		try{
			String login = "select * from UserInfo where UserName = ? Password = ?";
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
	
}
