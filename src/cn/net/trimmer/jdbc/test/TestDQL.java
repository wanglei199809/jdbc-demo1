package cn.net.trimmer.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * jdbc连接Oracle数据库并测试DQL操作
 * 
 * @author wl
 *
 */
public class TestDQL {
	public static void main(String[] args) throws Exception {
		// 1 加载驱动
		Class.forName("oracle.jdbc.OracleDriver");
		// 2 创建连接
		String url = "jdbc:oracle:thin:@192.168.230.10:1521:orcl";
		Connection conn = DriverManager.getConnection(url, "baizhi", "123456");
		// 3 创建statment(声明对象)
		Statement statement = conn.createStatement();
		// 4 发送sql并接收执行结果
		String sql = "select accountno,accountname,password,balance from accounts";
		ResultSet rs = statement.executeQuery(sql);
		// 5 处理sql执行结果
		while (rs.next()) {
			String accountNo = rs.getString("accountno");
			String accountName = rs.getString(2);
			String password = rs.getString("password");
			Double balance = rs.getDouble("balance");
			System.out.println(accountNo + "," + accountName + "," + password + "," + balance);
		}
		// 6关闭资源
		if (rs != null) rs.close();
		if (statement != null) statement.close();
		if (conn != null) conn.close();
	}
}
