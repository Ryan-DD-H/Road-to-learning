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
 * Servlet implementation class VegetablesAdd
 */
@WebServlet("/VegetablesAdd")
public class VegetablesAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String indate = request.getParameter("indate");
		int QGPDay = Integer.parseInt(request.getParameter("QGPDay")) ;
		String category = request.getParameter("category");
		String address = request.getParameter("address");
		int stock = Integer.parseInt(request.getParameter("stock"));		
		Vegetables ve = new Vegetables(0,name,indate,QGPDay,category,address,stock);
		try {
			new VegetablesService().vegetablesAdd(ve);
			response.sendRedirect("main.jsp");
			
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
