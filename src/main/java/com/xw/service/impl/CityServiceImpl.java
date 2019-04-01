package com.xw.service.impl;

import com.xw.entiry.City;
import com.xw.mapper.CityDao;
import com.xw.mapper.IBaseDao;
import com.xw.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  CityServiceImpl extends BaseServiceImpl<City,Long> implements ICityService {

   @Autowired
   private CityDao cityDao;

    @Override
    public IBaseDao<City, Long> getBaseDao() {
        return this.cityDao;
    }

    @Override
    public City find(String id) {
        return null;
    }

    @Override
    public City findByCityName(String name) {
        return cityDao.findByCityName(name);
    }
}
