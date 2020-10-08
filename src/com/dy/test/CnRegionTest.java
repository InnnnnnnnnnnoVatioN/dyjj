package com.dy.test;

import com.dy.bean.CnRegion;
import com.dy.dao.RegionDaoImp;
import com.dy.dao.BaseRegionDao;

import java.util.List;

public class CnRegionTest {
    public static void main(String[] args) {
        BaseRegionDao dao = new RegionDaoImp();
        //List<CnRegion> data = dao.findByLevel(1);
        List<CnRegion> data = dao.findByParentCode("610104");
        System.out.println(data);
    }
}
