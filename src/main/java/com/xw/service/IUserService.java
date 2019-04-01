package com.xw.service;


import com.xw.entiry.User;

public interface IUserService extends IBaseService<User,Integer> {

    /**
     * 注册
     * @param user
     */
    void saveUser(User user);

    /**
     * 用户名查询
     * @param name
     */
    User findByName(String name);




}
