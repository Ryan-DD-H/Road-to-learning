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
 * Servlet implementation class VegetablesDelOrUpdate
 */
@WebServlet("/VegetablesDelOrUpdate")
public class VegetablesDelOrUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		int veId = Integer.parseInt(request.getParameter("veId"));
		System.out.println(op);
		if(op.equals("del")){
			try {
				
				new VegetablesService().vegetablesDel(veId);
				request.getRequestDispatcher("/DisplayVegetables").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(op.equals("update")){

			try {			
				Vegetables ve = new VegetablesService().checkVegetablesById(veId);
				request.setAttribute("ve", ve);
				request.getRequestDispatcher("/vegetablesUpdate.jsp").forward(request, response);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
