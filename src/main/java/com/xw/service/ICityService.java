package com.xw.service;

import com.xw.entiry.City;
import com.xw.entiry.User;

public interface ICityService extends IBaseService<City,Long> {

    City findByCityName(String name);


}
