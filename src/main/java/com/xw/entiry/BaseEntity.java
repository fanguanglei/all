package com.xw.entiry;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 基类-----属性类型需要为引用类型，判断条件为null,原生类型为0
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -250118731239275742L;

}