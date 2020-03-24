package com.zlt.sys.finance.controller.salary;

import com.my.web.servlet.RequestBeanUtils;
import com.zlt.pojo.SalaryRecord;
import com.zlt.sys.finance.service.ISalaryService;
import com.zlt.sys.finance.service.imp.SalaryServiceImp;
import com.zlt.utiles.ExpenseDateFormat;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/finance/SalaryRecordQueryServlet")
public class SalaryRecordQueryServlet extends HttpServlet {


    /*
    * 跳转至薪资查询界面并查询所有的符合条件的用户的薪资
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ISalaryService iSalaryService = new SalaryServiceImp();
        //格式化时间
        ConvertUtils.register(new ExpenseDateFormat("yyyy-MM"), Date.class);
        //获取查询的数据
        SalaryRecord salaryRecord = RequestBeanUtils.requestToSimpleBean(request,SalaryRecord.class);
        List<SalaryRecord> salaryRecordList = iSalaryService.selectSalaryRecord(salaryRecord);
        request.setAttribute("salaryRecord",salaryRecord);
        request.setAttribute("salaryRecordList",salaryRecordList);
        request.getRequestDispatcher("/view/finance/salary/salarypayment_list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
