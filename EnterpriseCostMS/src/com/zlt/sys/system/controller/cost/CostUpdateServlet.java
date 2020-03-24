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

@WebServlet("/system/CostUpdateServlet")
public class CostUpdateServlet extends HttpServlet {

    ICostService iCostService = new CostServiceImp();

    /*
    * 更新费用
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Cost cost = RequestBeanUtils.requestToBean(request,Cost.class);
            request.setAttribute("cost",cost);
            iCostService.costUpdate(cost);
            request.setAttribute("tips","用户信息修改成功");
            request.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(request,response);
        }catch (RuntimeException e){
            request.setAttribute("tips",e.getMessage());
            request.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(request,response);
        }
    }


    /*
    * 跳转至更新费用界面
    * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cost cost = RequestBeanUtils.requestToBean(request,Cost.class);
        List<Cost> costList = iCostService.selectListUsers(cost);
        cost = costList.get(0);
        request.setAttribute("cost",cost);
        request.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(request,response);
    }
}
