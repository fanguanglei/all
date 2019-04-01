package com.xw.mapper;
import com.xw.entiry.City;


public interface CityDao extends IBaseDao<City,Long>{

    City findByCityName(String name);

}
