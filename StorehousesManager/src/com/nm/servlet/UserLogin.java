package com.nm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nm.model.User;
import com.nm.service.UsersService;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String imageValue = request.getParameter("imageValue");
		HttpSession session = request.getSession();
		String sRand =(String) session.getAttribute("sRand");
		if(imageValue.equals(sRand)){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UsersService usersService = new UsersService();
			try {
				User user = usersService.login(username,password);							
				if(user.getUsername()!=null &&  user.getUsername()!="") {
					  //将对象存入到Seesion中
					  request.getSession().setAttribute("user", user);
					  response.sendRedirect("main.jsp");					  
					  
				  }else {
					  //为空
					  //resp.sendRedirect("/view/logon.jsp");
					  request.setAttribute("message","账号或密码错误");
					  request.getRequestDispatcher("login.jsp").forward(request, response);
				  }							
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			  request.setAttribute("message","验证码错误");
			  request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
