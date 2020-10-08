package com.dy.dao;

import com.dy.bean.CnRegion;

import java.util.List;

public interface BaseRegionDao {

    /**
     * 根据城市级别， 查询城市列表
     * @param level
     * @return
     */
    List<CnRegion> findByLevel(int level);

    /**
     * 根据城市码 ， 查询单个城市
     * @param code 查询的城市码
     * @return
     */
    CnRegion findByCode(String code);


    /**
     * 根据城市码 ， 查询所有的子城市
     * @param code 城市码
     * @return
     */
    List<CnRegion> findByParentCode(String code);





}
