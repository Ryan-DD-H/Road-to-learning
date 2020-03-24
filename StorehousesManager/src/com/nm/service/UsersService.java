package com.nm.service;

import java.sql.SQLException;

import com.nm.dao.UsersDao;
import com.nm.model.User;

public class UsersService {
	
	UsersDao userDao = new UsersDao();

	public User login(String username,String password) throws SQLException{	
		User user = userDao.loginDao(username,password);
		return user;
	}
	
	public void updatePassword(String password,int id) throws SQLException{
		userDao.updatePasswordDao(password, id);
	}
	
	
	
}
