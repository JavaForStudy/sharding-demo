package com.lan.sharding.demo.mapper;


import com.lan.sharding.demo.annotation.ForceMaster;
import com.lan.sharding.demo.domain.Query;
import com.lan.sharding.demo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into t_user(user_id,user_name,c_date,c_create_time) value (#{userId},#{userName},#{createTime})")
    int insertForCreateTime(User user);


    @ForceMaster
    @Select("select * from t_user where c_create_time between #{startDay} and #{endDay}")
    List<User> selectAllForMaster(Query query);


    @Select("select user_id as userId," +
            "user_name As userName," +
            "c_date As date, " +
            "c_create_time as createTime " +
            "from t_user limit #{start}, #{pageSize}")
    List<User> selectForLimit(Query query);

    @Select("select count(1) from t_user")
    Integer selectCount();

    @Insert("insert into t_user(user_name,c_date) value (#{userName},#{date})")
    int insert(User user);


    @Insert("<script>" +
            "insert into t_user(user_name,c_date) values" +
            "<foreach collection='userList' item='item' index='index' separator=','>" +
            "(#{item.userName},#{item.date})" +
            "</foreach>" +
            "</script>")
    int insertBatch(@Param("userList") List<User> userList);

    @Select("select user_id as userId," +
            "user_name As userName," +
            "c_date As date, " +
            "c_create_time as createTime " +
            "from t_user " +
            "where user_name in ('小红','张三')")
    List<User> selectForIn();

    @Insert("insert into t_user (user_name, c_date) value (#{userName}, #{date}) on duplicate key update c_create_time = now()")
    int insertForOnDuplicate(User user);
}
