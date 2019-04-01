package com.xw.service.impl;

import com.xw.entiry.User;
import com.xw.mapper.IBaseDao;
import com.xw.mapper.UserDao;
import com.xw.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,Integer> implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public IBaseDao<User, Integer> getBaseDao() {
        return this.userDao;
    }

    @Override
    public User findByName(String name) {
          return userDao.findByName(name);
    }

    @Override
    public void saveUser(User user) {
        save(user);
    }


    @Override
    public User find(String id) {
        return null;
    }
}
