package com.zlt.sys.system.controller.cost;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.Cost;
import com.zlt.sys.system.service.ICostService;
import com.zlt.sys.system.service.imp.CostServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/system/CostAddServlet")
public class CostAddServlet extends HttpServlet {

    /*
    * 添加费用
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ICostService iCostService = new CostServiceImp();
            //获取前端传来的数据
            Cost cost = RequestBeanUtils.requestToBean(request,Cost.class);
            iCostService.costAdd(cost);
            request.setAttribute("tips", "费用添加成功！");
            request.getRequestDispatcher("/view/system/cost/cost_add.jsp").forward(request, response);
        }catch (RuntimeException e){
            request.setAttribute("tips", e.getMessage());
            request.getRequestDispatcher("/view/system/cost/cost_add.jsp").forward(request, response);
        }
    }

    /*
    * 跳转添加费用界面
    * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/system/cost/cost_add.jsp").forward(request,response);
    }
}
