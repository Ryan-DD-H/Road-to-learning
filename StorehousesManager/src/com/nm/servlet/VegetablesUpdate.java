package com.nm.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nm.model.Vegetables;
import com.nm.service.VegetablesService;

/**
 * Servlet implementation class VegetablesUpdate
 */
@WebServlet("/VegetablesUpdate")
public class VegetablesUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int vegetablesID = Integer.parseInt(request.getParameter("vegetablesID")) ;
		String name = request.getParameter("name");
		String indate = request.getParameter("indate");
		int QGPDay = Integer.parseInt(request.getParameter("QGPDay")) ;
		String category = request.getParameter("category");
		String address = request.getParameter("address");
		int stock = Integer.parseInt(request.getParameter("stock"));		
		Vegetables ve = new Vegetables(vegetablesID,name,indate,QGPDay,category,address,stock);
		try {
			new VegetablesService().vegetablesUpdate(ve);
			response.sendRedirect("DisplayVegetables");			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
