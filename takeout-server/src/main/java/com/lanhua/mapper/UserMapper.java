package com.lanhua.mapper;

import com.lanhua.annotation.AutoFill;
import com.lanhua.entity.User;
import com.lanhua.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 插入数据
     * @param user
     */
//    @AutoFill(value = OperationType.INSERT)
    void insert(User user);

    @Select("select * from user where id=#{userId}")
    User getById(Long userId);

    Integer queryAll();

    Integer queryNewCustomers(Map map);
}