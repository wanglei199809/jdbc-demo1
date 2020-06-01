package cn.net.trimmer.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

/**
 * 测试使用prepareStatement 解决sql执行过程中的sql注入问题
 * 
 * @author wl
 *
 */
public class TestDMLParam {

	public static void main(String[] args) throws Exception {
		// 模拟参数 金毛'狮王有注入问题,但是在后续处理后可以直接插入到数据库
		String accountName = "金毛'狮王";
		String password = "123456";
		Double balance = 1234.0;
		Date date = new java.util.Date();
		java.sql.Date account_date = new java.sql.Date(date.getTime());

		// 1.加载驱动
		Class.forName("oracle.jdbc.OracleDriver");
		// 2.创建连接
		String url = "jdbc:oracle:thin:@192.168.230.10:1521:orcl";
		Connection conn = DriverManager.getConnection(url, "baizhi", "123456");
		// 3.创建pstm,占位符形式的sql操作对象,可避免sql注入问题
		String sql = "insert into accounts values(seq_account.nextval,?,?,?,?)";
		PreparedStatement pstm = conn.prepareStatement(sql);
		// 4.先给占位符赋值,再发送sql并接收执行结果
		pstm.setString(1, accountName);
		pstm.setString(2, password);
		pstm.setDouble(3, balance);
		pstm.setDate(4, account_date);
		int row = pstm.executeUpdate();
		// 5.处理操作结果
		System.out.println(row);
		// 6.关闭资源
		if (pstm != null) { pstm.close();}
		if (conn != null) {conn.close();}
	}
}
