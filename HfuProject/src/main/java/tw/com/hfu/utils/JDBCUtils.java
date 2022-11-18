package tw.com.hfu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

	// getConnection: 連接 數據庫
	public static Connection getConnection() {

		Connection conn = null;

		try {
			// 加載文件 file: jdbc.properties
			ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
			InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			Properties prop = new Properties();
			prop.load(is);

			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
			String url = prop.getProperty("url");
			String driverClass = prop.getProperty("driverClass");

			// load driver
			Class.forName(driverClass);

			// connect to database by using DriverManager
			conn = DriverManager.getConnection(url, user, password);

			System.out.println("----- DB: Successfully connect to database. -----");

		} catch (ClassNotFoundException | IOException | SQLException e) {

			e.printStackTrace();
		}

		return conn;
	}

	// closeConnection: close resources
	public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {

		try {

			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Connection closed");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			if (ps != null && !ps.isClosed()) {
				ps.close();
				System.out.println("PreparedStatement closed");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			if (rs != null && !rs.isClosed()) {
				rs.close();
				System.out.println("ResultSet closed");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
