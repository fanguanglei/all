package com.xw.mapper;

import com.xw.entiry.User;


public interface UserDao extends IBaseDao<User, Integer>{


    User findByName(String name);


}
