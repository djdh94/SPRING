package com.ict.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

	@Select("select SYSDATE FROM dual")
	public String getTime();
}
