package cn.net.trimmer.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * jdbc连接Oracle数据库并测试DML操作
 * 
 * @author wl
 *
 */
public class TestDML {
	public static void main(String[] args) throws Exception {
		// 1 加载驱动
		Class.forName("oracle.jdbc.OracleDriver");
		// 2 创建连接
		String url = "jdbc:oracle:thin:@192.168.230.10:1521:orcl";
		Connection conn = DriverManager.getConnection(url, "baizhi", "123456");
		// 3 创建statment(声明对象)
		Statement statement = conn.createStatement();
		// 4 发送sql并接收执行结果
		String sql = "insert into accounts values(seq_account.nextval,'易小川','123456',80000)";
		int row = statement.executeUpdate(sql);
		// 5 处理sql执行结果
		System.out.println("共插入成功" + row + "行!");
		// 6关闭资源
		if (statement != null) statement.close();
		if (conn != null) conn.close();
	}
}
