package com.nm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.nm.model.User;
import com.nm.util.JdbcUtiles;

public class UsersDao {
	
	public User loginDao(String username,String password) throws SQLException{
		
		 User user= new User();
		 Connection	conn=null;
		 PreparedStatement  pre=null;
		 ResultSet	rs=null;
		 String  sql="select * from user where username=? and password=?";
		 try {
		    conn = JdbcUtiles.getConnection();
		    pre= conn.prepareStatement(sql);	
		    pre.setString(1,username);
		    pre.setString(2,password);
		    rs=	 pre.executeQuery();
		    while(rs.next()) {			
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
		    }
		 } catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		 }finally{
		    JdbcUtiles.getclose01(conn, pre, rs);
		 }
		
		 return user;
	}
	
	
	public void updatePasswordDao(String password,int id) throws SQLException{
		 Connection	conn=null;
		 PreparedStatement  pre=null;
		 String  sql="update user set password = ? where id = ?";
		    try {
		    	conn = JdbcUtiles.getConnection();
		    	pre= conn.prepareStatement(sql);	
		    	pre.setString(1,password);
		    	pre.setInt(2,id);
		    	pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtiles.getclose01(conn, pre,null);
		}
		
	}
	
	
}
