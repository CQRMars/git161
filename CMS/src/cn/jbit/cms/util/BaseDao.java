package cn.jbit.cms.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class BaseDao {
	//4������
	private static String driver;//����
	private static String url;//�����ַ���
	private static String user;//�û���
	private static String password;//����
	static {
		init();//��ʼ�����������������ļ�
	}
	public static void init() {
		Properties properties=new Properties();
		String configFile="database.properties";
		//��ȡ�����ļ���������
		InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(is);//���������м��������ļ�
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��ȡ��Ӧ����ֵ
		driver=properties.getProperty("driver");
		url=properties.getProperty("url");
		user=properties.getProperty("username");
		password=properties.getProperty("password");
	}
	
	/**
	 * ���Ӷ���
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
	 * ��˳��ر���Դ 
	 * @param conn ���Ӷ���
	 * @param pstmt ִ��sql������
	 * @param rs �����
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
	 * ��ɾ��ͨ�÷���
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
//		System.out.println("���ӳɹ�");
//		bd.closeAll(conn, null, null);
//		System.out.println("����ʧ��");
//	}
}
