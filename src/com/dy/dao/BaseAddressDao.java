package com.dy.dao;

import com.dy.bean.Address;

import java.util.List;

public interface BaseAddressDao {

    /**
     * 用于查询用户的所有收货地址
     * @param userId 用户的id
     * @return 查询的结果
     */
    List<Address> findByUserId(int userId);

    /**
     *  通过id 查询某个收货地址信息
     * @param id 地址id
     * @return 查询结果, 查询失败返回null
     */
    Address findById(int id);

    /**
     * 用于地址信息的插入
     * @param address 地址对象
     * @return 插入结果
     */
    boolean insert(Address address);

    /**
     * 用于地址信息的修改
     * @param id 要修改的id
     * @param newAddress 新的地址信息
     * @return
     */
    boolean updateById(int id,Address newAddress);

    /**
     * 根据id ,修改默认地址
     * @param id 要修改为默认地址的id
     *  @param userId 要修改的用户id
     * @return 修改的结果
     */
    boolean updateDefaultById(int id,int userId);

    /**
     * 用于用户地址的删除
     * @param id 要删除的id
     * @return
     */
    boolean deleteById(int id);


}
