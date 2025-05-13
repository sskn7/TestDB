import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private static String driverName = "com.mysql.jdbc.Driver";

	private static String url =
			"jdbc:mysql://localhost/testdb?autoReconnecter=true&useSSL=false";

	private static String user = "root";
	private static String password = "";

	public Connection getConnection() {
		Connection con = null;

	//↓例外処理の構文
	try {
		Class.forName(driverName);
		//	↓ドライバーがロードされてツ使えるようにな状態にする
		con = DriverManager.getConnection(url,user,password);
	//↓エラーが発生したら受け取る
	} catch (ClassNotFoundException e) {
	e.printStackTrace();
	} catch (SQLException e) {
	e.printStackTrace();
	}
	return con;
	}
}

