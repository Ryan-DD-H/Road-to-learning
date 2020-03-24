package com.zlt.utiles;

import com.zlt.pojo.Users;
import sun.rmi.server.UnicastServerRef;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 黄国旺
 * 通用拦截器
 * */
@WebFilter("/*")
public class UniversalFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //设置编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        HttpSession session = request.getSession();
        Users users = (Users)session.getAttribute("users");
        //获取URI地址
        String uri = request.getRequestURI();

        if(users != null){
            //放行已登录的用户
            chain.doFilter(req, resp);
        }else if(uri.contains("system/UsersLoginServlet")){
            //放行登录的Servlet
            chain.doFilter(req, resp);
        }else if(uri.contains("resource")){
            //放行静态资源
            chain.doFilter(req, resp);
        }else if(uri.contains("login.jsp")){
            //放行登录的界面
            chain.doFilter(req, resp);
        }else{
            //其他情况则重定向至登录界面
            response.sendRedirect("/view/login.jsp");
        }



    }

    @Override
    public void destroy() {

    }

}
