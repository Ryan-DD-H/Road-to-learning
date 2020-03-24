package com.zlt.sys.system.service;

import com.zlt.pojo.Cost;

import java.util.List;

public interface ICostService {
    List<Cost> selectListCosts(Cost cost);

    void costAdd(Cost cost);

    void costDelete(Cost users);

    List<Cost> selectListUsers(Cost cost);

    void costUpdate(Cost cost);
}
