package com.xw.service.impl;

import com.xw.entiry.User;
import com.xw.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setId(4);
        user.setPhone("15225874587");
        user.setCityName("÷ÿ«Ï");
        user.setName("zhangsan3");
        user.setPassword("e10adc3949ba59abbe56e057f20f883e");
        userService.save(user);
        System.out.println(user);
    }

    @Test
    public void findByName() {
       

    }


}