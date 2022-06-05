package com.myStudentManager.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
/**
 * @��������ģ��
 * @author ����־
 * @version 1.2
 * @data 03/24/2010
 * @last update 06/02/2011
 * @Email 646749375@qq.com
 */
public class DBConnection {
	private static Connection conn ;
	private static Statement stmt ;
    private static final String drivername="com.mysql.jdbc.Driver";
    private static final String url="jdbc:mysql://localhost:3307/coursechoose?characterEncoding=utf8";
    private static final String username="chatmanager";
    private static final String password="123456";
    
    public DBConnection(){
    	
    }
    
    static {

			try {
				Class.forName(drivername);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "�������ݿ����������������ԭ��");
			}
			try {
				conn = DriverManager.getConnection(url,username,password);
				stmt = conn.createStatement();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "���ݿ������������û������������");
			}

    }    	
    /**
     * @����rs�αꣻ���ô˷��������������õ�ϰ�ߵ���rs.close()�����ͷ�����õ�rs
     * @param sql
     * @return ÿ�ε��ô˷������Է��ز�ͬ��rs��¼�������ⲻ��Ƕ�ײ�ѯ������
     */
	public static ResultSet query(String sql) {
		    ResultSet rs = null;
			try {
			rs = conn.createStatement().executeQuery(sql);
			} catch (SQLException e) {
			}
		return rs;
	}
	/**
	 * ���ݿ�ִ�С����¡�ɾ��ģ��
	 */
	public static boolean update(String sql){
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			return false;
			
		}
		return true;
	}
	/**
	 * @�ر�stmt��conn
	 */
	public static void close() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ac) {
			ac.printStackTrace();
		}
	}
	/**
	 * @ ���� ������ȡ���ݿ��¼��
	 */
	public static int getTotalRow(String tableName){//��ȡ��¼������
		ResultSet rsline = query("select count(*) as counts from "+tableName);
		
		int counts=0;
		try{
		if(rsline.next())
		{
			counts = Integer.parseInt(rsline.getString("counts"));
		}
		}catch(Exception er){
		}
		return counts;
	}
}

