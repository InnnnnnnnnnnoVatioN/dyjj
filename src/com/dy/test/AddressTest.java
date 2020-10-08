package com.dy.test;

import com.dy.bean.Address;
import com.dy.dao.AddressDaoImp;
import com.dy.dao.BaseAddressDao;

import java.util.List;

public class AddressTest {
    public static void main(String[] args) {
        BaseAddressDao dao = new AddressDaoImp();
        //List<Address> data = dao.findByUserId(2);
        //Address data = dao.findById(3);
        //'2', '嘿嘿嘿', '18516955565', '110000', '110100', '110101', '110101001', '从前有座山, 山上有座庙, 庙里有个老和尚 , 正在对小和尚
        //Address address = new Address(-1,-1,"jason","18888888886",110000,110100,110101,110101001,"锄禾日当午, 清明上河图",0,null);
        //boolean flag = dao.insert(address);
        //boolean flag = dao.updateById(6, address);
        //boolean flag = dao.updateDefaultById(6,2);
        boolean flag = dao.deleteById(4);
        System.out.println(flag);
        //System.out.println(address);
    }
}
