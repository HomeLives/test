package Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Database {

	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	private static String sno = "";
	private static String sname = "";
	private static String sage = "";
	private static String major = "";

	// Êý¾Ý¿âÁ¬½Ó
	public static Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql:///aaa",
					"root", "root");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	// Êý¾Ý¿â-ÕËºÅµÇÂ¼
	public static void login(String a, String b, JFrame jf) {
		Boolean flag = false;
		Connection connection = Database.connect();
		try {
			statement = connection.prepareStatement("select * from login");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String account = resultSet.getString("account");
				String password = resultSet.getString("password");
				if (a.equals(account) && b.equals(password)) {
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (flag == true) {
				// µÇÂ¼³É¹¦
				ContentInterface.Login(jf);
			} else {
				// µÇÂ¼Ê§°Ü
				ContentInterface.LoginError(jf);
			}
		}
	}

	public static void register(String a, String b, JFrame jf) {
		int updateNumber = 0;
		Connection connection = Database.connect();
		try {
			statement = connection
					.prepareStatement("insert into login values(?,?)");
			statement.setString(1, a);
			statement.setString(2, b);
			updateNumber = statement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (updateNumber == 1) {
				// ×¢²á³É¹¦
				ContentInterface.Register(jf);
			} else {
				// ×¢²áÊ§°Ü
				ContentInterface.RegisterError(jf);
			}
		}
	}

}
