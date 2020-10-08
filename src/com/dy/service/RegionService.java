package com.dy.service;

import com.dy.bean.CnRegion;
import com.dy.dao.RegionDaoImp;
import com.dy.dao.BaseRegionDao;
import com.dy.util.DataUtil;

import java.util.List;

public class RegionService {
    private static BaseRegionDao dao = new RegionDaoImp();

    /**
     * 根据城市级别， 查询城市列表
     * @param level
     * @return
     */
    public static List<CnRegion> findByLevel(int level){
        if(level == 1){
            //查询所有省份
            List<CnRegion> citys = (List<CnRegion>) DataUtil.data.get("citys");
            if(citys == null){
                citys =  dao.findByLevel(level);
                DataUtil.data.put("citys",citys);
            }
            return citys;
        }
        return dao.findByLevel(level);
    }

    /**
     * 根据城市码 ， 查询单个城市
     * @param code 查询的城市码
     * @return
     */
    public static CnRegion findByCode(String code){
        return dao.findByCode(code);
    }


    /**
     * 根据城市码 ， 查询所有的子城市
     * @param code 城市码
     * @return
     */
    public static List<CnRegion> findByParentCode(String code){
        return dao.findByParentCode(code);
    }


}
