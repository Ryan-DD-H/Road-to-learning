package com.zlt.sys.system.service.imp;

import com.zlt.pojo.Cost;
import com.zlt.sys.system.dao.ICostDao;
import com.zlt.sys.system.dao.imp.CostDaoImp;
import com.zlt.sys.system.service.ICostService;

import java.util.List;

import static com.zlt.utiles.NullAssertion.isNotNull;

public class CostServiceImp implements ICostService {

    ICostDao iCostDao = new CostDaoImp();

    @Override
    public List<Cost> selectListCosts(Cost cost) {
        return iCostDao.selectListCost(cost);
    }

    @Override
    public void costAdd(Cost cost) {
        isNotNull("费用名不能为空",cost.getCostName());
        isNotNull("费用描述不能为空",cost.getCostDesc());
        int i = iCostDao.costAdd(cost);
        isNotNull("费用添加失败",i);
    }

    @Override
    public void costDelete(Cost cost) {
        int i = iCostDao.costDelete(cost);
        isNotNull("用户删除失败",i);
    }

    @Override
    public List<Cost> selectListUsers(Cost cost) {
        return iCostDao.selectListCost(cost);
    }

    @Override
    public void costUpdate(Cost cost) {
        isNotNull("无法获取费用编号",cost.getCostId());
        isNotNull("费用名称不能为空",cost.getCostName());
        isNotNull("费用描述不能为空",cost.getCostDesc());
        int i = iCostDao.costUpdate(cost);
        isNotNull("费用更新失败",i);
    }
}
