package com.yuansong.demo.boot.sqlite.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yuansong.demo.boot.sqlite.entity.User;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM user WHERE id = #{id}")
	User queryById(@Param("id") int id);

}
