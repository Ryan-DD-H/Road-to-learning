package com.nm.util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class JdbcUtiles {
/**
 * jdbc_url=jdbc:mysql://localhost:3306/storehousesmanager?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
	jdbc_user=root
	jdbc_password=zifeng28/
	jdbc_driver=com.mysql.cj.jdbc.Driver
 * 
 */

 private  static String jdbc_url;
 private  static String jdbc_user;
 private  static String jdbc_password;
 private  static String jdbc_driver;
	

   static {

	   Properties pro=  new Properties();

	   ClassLoader classLoader=   JdbcUtiles.class.getClassLoader();
	   URL  url=  classLoader.getResource("jdbc.properties");
	   String  path=  	url.getPath();

	   try {
		   pro.load(new FileReader(path));	
		
		   jdbc_url=pro.getProperty("jdbc_url");
		   jdbc_user=pro.getProperty("jdbc_user");
		   jdbc_password=pro.getProperty("jdbc_password");
		   jdbc_driver=pro.getProperty("jdbc_driver");

		   Class.forName(jdbc_driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   
	/**

	 * @throws SQLException 
	 */
	   public static Connection  getConnection() throws SQLException{
		   return DriverManager.getConnection(jdbc_url,jdbc_user,jdbc_password);
		   
	   }
	   
	  /**
	 * @throws SQLException 
	   */
	   public static void getclose(Connection conn,Statement stat,ResultSet  set) throws SQLException {
		   if(set !=null) {
			   set.close();
		   }
		   if(stat !=null) {
			   stat.close();
		   }
		   
		   if(conn !=null) {
			   conn.close();
		   }
		   
	   }
 
		  /**
		 * @throws SQLException 
		   */
		   public static void getclose01(Connection conn,PreparedStatement stat,ResultSet  set) throws SQLException {
			   if(set !=null) {
				   set.close();
			   }
			   if(stat !=null) {
				   stat.close();
			   }
			   
			   if(conn !=null) {
				   conn.close();
			   }
			   
		   }
	 
	
	
}
