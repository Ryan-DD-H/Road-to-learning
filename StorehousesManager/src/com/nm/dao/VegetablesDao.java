package com.nm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nm.model.Vegetables;
import com.nm.util.JdbcUtiles;

public class VegetablesDao {

	public List<Vegetables> checkVegetablesDao() throws SQLException{
		ArrayList<Vegetables> arr = new ArrayList();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from vegetables";
		
		try {
			conn = JdbcUtiles.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Vegetables ve = new Vegetables(rs.getInt("vegetablesID"),rs.getString("name"),rs.getString("indate"),rs.getInt("QGPDay"),rs.getString("category"),rs.getString("address"),rs.getInt("stock"));
				arr.add(ve);		
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				JdbcUtiles.getclose(conn, st, rs);
		}		
		return arr;
	}
	
	
	
	public void vegetablesAddDao(Vegetables ve) throws SQLException{
		 Connection	conn=null;
		 PreparedStatement  pre=null;
		 String  sql="insert into vegetables values(?,?,?,?,?,?,?);";
		    try {
		    	conn = JdbcUtiles.getConnection();
		    	pre= conn.prepareStatement(sql);			    	
		    	pre.setInt(1,ve.getVegetablesID());
		    	pre.setString(2,ve.getName());
		    	pre.setString(3,ve.getIndate());
		    	pre.setInt(4,ve.getQGPDay());
		    	pre.setString(5,ve.getCategory());
		    	pre.setString(6,ve.getAddress());
		    	pre.setInt(7,ve.getStock());
		    	pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtiles.getclose01(conn, pre,null);
		}	
	}
	
	
	public void vegetablesDelDao(int veId) throws SQLException{
		 Connection	conn=null;
		 PreparedStatement  pre=null;
		 String  sql="delete from vegetables where vegetablesID =?;";
		    try {
		    	conn = JdbcUtiles.getConnection();
		    	pre= conn.prepareStatement(sql);	
		    	pre.setInt(1,veId);
		    	pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtiles.getclose01(conn, pre,null);
		}	
	}
	
	public Vegetables checkVegetablesByIdDao(int veId) throws SQLException{
		 Connection	conn=null;
		 PreparedStatement  pre=null;
		 ResultSet rs = null;
		 Vegetables ve = null;
		 String  sql="select * from vegetables where vegetablesID =?;";
		 
		 try {
		    conn = JdbcUtiles.getConnection();
		    pre= conn.prepareStatement(sql);	
		    pre.setInt(1,veId);
		    rs = pre.executeQuery();
		    while(rs.next()){
		    	ve = new Vegetables(rs.getInt("vegetablesID"),rs.getString("name"),rs.getString("indate"),rs.getInt("QGPDay"),rs.getString("category"),rs.getString("address"),rs.getInt("stock"));
		    }
		    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtiles.getclose01(conn, pre,rs);
		}
		 return ve;
	}
	
	
	public void vegetablesUpdateDao(Vegetables ve) throws SQLException{
		 Connection	conn=null;
		 PreparedStatement  pre=null;
		 String  sql="update vegetables set name =?,indate=?,QGPDay=?,category=?,address=?,stock=? where vegetablesID=?;";
		    try {
		    	conn = JdbcUtiles.getConnection();
		    	pre= conn.prepareStatement(sql);			    			    	
		    	pre.setString(1,ve.getName());
		    	pre.setString(2,ve.getIndate());
		    	pre.setInt(3,ve.getQGPDay());
		    	pre.setString(4,ve.getCategory());
		    	pre.setString(5,ve.getAddress());
		    	pre.setInt(6,ve.getStock());
		    	pre.setInt(7,ve.getVegetablesID());
		    	pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcUtiles.getclose01(conn, pre,null);
		}	
	}
	
	
}
