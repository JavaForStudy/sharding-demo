package com.lan.sharding.demo.mapper;

import com.lan.sharding.demo.domain.Order;
import com.lan.sharding.demo.domain.Query;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into t_order(order_name,user_id) value (#{orderName},#{userId})")
    int insert(Order order);

    @Select("select * from t_order")
    List<Order> selectAll();

    @Insert("insert into t_order(order_id,order_name,user_id) value (#{orderId},#{orderName},#{userId})")
    void insertForKeyGenerator(Order order);


    @Select("select user_id as userId," +
            "order_name As orderName," +
            "user_id As userId " +
            "from t_order limit #{start}, #{pageSize}")
    List<Order> selectForLimit(Query query);

    @Select("select count(1) from t_order")
    Integer selectCount();
}
