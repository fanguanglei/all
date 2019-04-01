package com.xw.service.impl;

import com.xw.entiry.City;
import com.xw.service.ICityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceImplTest {

    @Autowired
    private ICityService cityService;

    @Test
    public void getBaseDao() {

        List<City> all = cityService.findAll();
        System.out.println(all);

    }

    @Test
    public void find() {
    }

    @Test
    public void findByCityName() {
        City city = cityService.findByCityName("÷ÿ«Ï");
        System.out.println(city);


    }
}