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

@WebServlet("/system/CostDeleteServlet")
public class CostDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ICostService iCostService = new CostServiceImp();
        try {

            Cost cost = RequestBeanUtils.requestToBean(request,Cost.class);
            iCostService.costDelete(cost);
            request.setAttribute("tips", "费用删除成功");
            request.getRequestDispatcher("/system/CostListQueryServlet").forward(request, response);
        }catch (RuntimeException e){
            request.setAttribute("tips", e.getMessage());
            request.getRequestDispatcher("/system/CostListQueryServlet").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
