package com.quality.mapper;


import com.quality.model.UserModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserModel queryUser(String username);
}
