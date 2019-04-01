package com.xw.entiry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="city")
@Entity
public class City  extends BaseEntity{

    @Override
	public String toString() {
		return "City [id=" + id + ", cityName=" + cityName + ", initial=" + initial + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5764740943308147085L;

	@Id
    @GeneratedValue
    private Long id;

    private String cityName;

    private String initial;

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
