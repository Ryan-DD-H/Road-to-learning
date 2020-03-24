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
import java.util.List;

@WebServlet("/system/CostListQueryServlet")
public class CostListQueryServlet extends HttpServlet {


    ICostService iCostService = new CostServiceImp();

    /*
    * 条件查询所有费用
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Cost queryCost = RequestBeanUtils.requestToBean(request,Cost.class);
        List<Cost> costList = iCostService.selectListCosts(queryCost);
        request.setAttribute("queryCost",queryCost);
        request.setAttribute("costList",costList);

        request.getRequestDispatcher("/view/system/cost/cost_list.jsp").forward(request,response);
    }

    /*
    * 跳转至查询所有费用界面（顺便查询所有费用）
    * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Cost> costList = iCostService.selectListCosts(new Cost());
        request.setAttribute("costList",costList);
        request.getRequestDispatcher("/view/system/cost/cost_list.jsp").forward(request,response);
    }
}
