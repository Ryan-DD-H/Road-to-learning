package com.nm.service;

import java.sql.SQLException;
import java.util.List;

import com.nm.dao.VegetablesDao;
import com.nm.model.Vegetables;

public class VegetablesService {

	VegetablesDao vegetablesDao =new VegetablesDao();
	
	public List<Vegetables> checkVegetables() throws SQLException{
		return vegetablesDao.checkVegetablesDao();
	}
	
	public void vegetablesAdd(Vegetables ve) throws SQLException{
		vegetablesDao.vegetablesAddDao(ve);
	}
	
	public void vegetablesDel(int veId) throws SQLException{
		vegetablesDao.vegetablesDelDao(veId);
	}
	
	public Vegetables checkVegetablesById(int veId) throws SQLException{
		return vegetablesDao.checkVegetablesByIdDao(veId);
	}
	
	public void vegetablesUpdate(Vegetables ve) throws SQLException{
		vegetablesDao.vegetablesUpdateDao(ve);
	}
}
