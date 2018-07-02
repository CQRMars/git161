package cn.jbit.cms.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class BaseDao {
	//4个属性
	private static String driver;//驱动
	private static String url;//连接字符串
	private static String user;//用户名
	private static String password;//密码
	static {
		init();//初始化方法，加载配置文件
	}
	public static void init() {
		Properties properties=new Properties();
		String configFile="database.properties";
		//获取配置文件的输入流
		InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(is);//从输入流中加载配置文件
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取对应键的值
		driver=properties.getProperty("driver");
		url=properties.getProperty("url");
		user=properties.getProperty("username");
		password=properties.getProperty("password");
	}
	
	/**
	 * 连接对象
	 * @return
	 */
	public Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 按顺序关闭资源 
	 * @param conn 连接对象
	 * @param pstmt 执行sql语句对象
	 * @param rs 结果集
	 */
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 增删改通用方法
	 * @param sql
	 * @param param
	 * @return
	 */
	public int executeUpdate(String sql,Object[] param) {
		int num=0;
		Connection conn=getConnection();
		PreparedStatement pstmt=null;
		try {
			
			pstmt=conn.prepareStatement(sql);
			if(pstmt!=null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i+1, param[i]);
				}
			}
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	
//	public static void main(String[] args) {
//		BaseDao bd=new BaseDao();
//		Connection conn=bd.getConnection();
//		System.out.println("连接成功");
//		bd.closeAll(conn, null, null);
//		System.out.println("连接失败");
//	}
}
