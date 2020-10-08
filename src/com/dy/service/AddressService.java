package com.dy.service;

import com.dy.bean.Address;
import com.dy.dao.AddressDaoImp;
import com.dy.dao.BaseAddressDao;

import java.util.List;

public class AddressService {
    private static BaseAddressDao dao = new AddressDaoImp();

    /**
     * 用于查询用户的所有收货地址
     * @param userId 用户的id
     * @return 查询的结果
     */
    public static List<Address> findByUserId(int userId){
        return dao.findByUserId(userId);
    }

    /**
     *  通过id 查询某个收货地址信息
     * @param id 地址id
     * @return 查询结果, 查询失败返回null
     */
    public static Address findById(int id){
        return dao.findById(id);
    }

    /**
     * 用于地址信息的插入
     * @param address 地址对象
     * @return 插入结果
     */
    public static boolean insert(Address address){
        return dao.insert(address);
    }

    /**
     * 插入默认收获地址
     * @param address 要插入的收获地址
     * @param userId 用户的id
     * @return
     */
    public static boolean insertDefault(Address address,int userId){
        insert(address);
        return updateDefaultById(address.getId(),userId);
    }

    /**
     * 用于地址信息的修改
     * @param id 要修改的id
     * @param newAddress 新的地址信息
     * @return
     */
    public static boolean updateById(int id,Address newAddress){
        return dao.updateById(id,newAddress);
    }

    /**
     * 根据id ,修改默认地址
     * @param id 要修改为默认地址的id
     *  @param userId 要修改的用户id
     * @return 修改的结果
     */
    public static boolean updateDefaultById(int id,int userId){
        return dao.updateDefaultById(id,userId);
    }

    /**
     * 用于用户地址的删除
     * @param id 要删除的id
     * @return
     */
    public static boolean deleteById(int id){
        return dao.deleteById(id);
    }

}
