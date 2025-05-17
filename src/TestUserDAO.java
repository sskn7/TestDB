import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUserDAO {
	String name = "";
	String password = "";

//3 名前とパスワードを表示する
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


//5 情報の全てを表示する
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

//7 名前から呼び出す
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

//9 パスワードから呼び出す
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

//11 既存の情報を更新する
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

//13 新しい情報を登録する
public void insert(int user_id,String name,String password) {
	DBConnector db = new DBConnector();
	Connection con = db.getConnection();

	String sql = "insert into test_table values(?,?,?)";
	try {
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, user_id);
		ps.setString(2, name);
		ps.setString(3, password);
		int i=ps.executeUpdate();
		if (i>0) {
			System.out.println(i + "件登録されました");
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

//15
//public void の後に何をするか(delete insert update)、何を(String name password)
public void delete(String name) {
	//↓2文は定型文
	DBConnector db = new DBConnector();
	Connection con = db.getConnection();

	//↓データベースに接続するための構文
	String sql ="delete from test_table where user_name=?";
	//↓そこで何をするかの指示構文
	try {
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		int i=ps.executeUpdate();
		if (i>0) {
			System.out.println(i + "件削除されました");
		}
	// 定型文
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
