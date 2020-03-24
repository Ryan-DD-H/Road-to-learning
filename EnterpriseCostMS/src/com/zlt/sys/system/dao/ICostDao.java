package com.zlt.sys.system.dao;

import com.zlt.pojo.Cost;

import java.util.List;

public interface ICostDao {
    List<Cost> selectListCost(Cost cost);

    int costAdd(Cost cost);

    int costDelete(Cost cost);

    int costUpdate(Cost cost);
}
