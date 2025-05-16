import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUserDAO {
	String name = "";
	String password = "";
public void select(String name,String password) {
	//DBへの接続の準備 mysqlへログインのための文
	DBConnector db = new DBConnector();
	Connection con = db.getConnection();

	//DBのtest_tableに入っている値を呼び出す。？には抽出する機能がある
	String sql = "select * from test_table where user_name=? and password=?";
	try {
	//PreparedStatementはDBまで運ぶ箱
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setString (1,name);
	ps.setString (2,password);
	//executeQueryは実行メゾッド、必ずResultSetが返ってくる
	ResultSet rs=ps.executeQuery();
	//↓データがあれば値を返して次へ、を繰り返してくれるWhileとかforみたいな構文？
	if (rs.next()) {
		System.out.println(rs.getString("user_name"));
		System.out.println(rs.getString("password"));
	}
	} catch (SQLException e) {
		e.printStackTrace();
	}

try {
	//↓データベースとの接続を終了させる構文
	con.close();
} catch (SQLException e) {
	e.printStackTrace();
}
}



public void selectAll() {
	DBConnector db =new DBConnector();
	Connection con = db.getConnection();

	String sql ="select * from test_table";
	try {
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("user_name"));
			System.out.println(rs.getString("password"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

public void selectByName(String name) {
	DBConnector db = new DBConnector();
	Connection con = db.getConnection();

	String sql ="select * from test_table where user_name=?";
	try {
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("user_name"));
			System.out.println(rs.getString("password"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		con.close ();
	} catch (SQLException e) {
		e.printStackTrace();
	}
 }

public void selectByPassword(String password) {
	DBConnector db = new DBConnector();
	Connection con = db.getConnection();

	String sql ="select * from test_table where password=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("user_name"));
				System.out.println(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}

public void updateUserNameByUserName(String oldName,String newName) {
	DBConnector db = new DBConnector();
	Connection con = db.getConnection();

	String sql ="update test_table set user_name=? where user_name=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newName);
			ps.setString(2, oldName);
			int i=ps.executeUpdate();
			if (i>0) {
				System.out.println(i + "更新されました");
			} else {
				System.out.println("該当するデータがありませんでした");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}


} //classの終わり

//DAO データアクセスオブジェクト
//データベースとのやり取りを担当する設計パターン
//アプリケーションとデータベースを分離するために使われる。
//直接 JDBC などを使ってデータベースとやり取りするのではなく
//DAOクラスを通じてデータを操作することで
//コードの再利用性が高まり、保守性も向上します。
//DAOというクラスを作ってSQL文をまとめることで、
//データベースへのアクセスをわかりやすく管理する。
//JDBCだと都度SQL文を打たなくてはいけないうえに重複したり
//どこに打ったかわからなくなる可能性があるので、
//DAOを使うと管理が簡単になり、保守性も高まる。
