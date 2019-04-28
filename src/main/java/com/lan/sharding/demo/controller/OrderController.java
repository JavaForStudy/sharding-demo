package com.lan.sharding.demo.controller;


import com.lan.sharding.demo.domain.Order;
import com.lan.sharding.demo.domain.Query;
import com.lan.sharding.demo.domain.User;
import com.lan.sharding.demo.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderMapper orderMapper;
    @RequestMapping("/save")
    public String save(Order order){
        orderMapper.insert(order);
        return "保存成功";
    }

    /**
     * 验证雪花id
     * @param order
     * @return
     */
    @RequestMapping("/saveForKeyGenerator")
    public String saveForKeyGenerator(Order order){
        orderMapper.insertForKeyGenerator(order);
        return "保存成功";
    }


    @RequestMapping("/selectForLimit")
    @GetMapping
    public Object selectForLimit() {
        Map<Integer, List<Order>> result = new HashMap<>();
        Integer total = orderMapper.selectCount();
        int pageSize = 2;
        int currentPage = 1;
        int totalPage = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1);
        while (currentPage <= totalPage) {
            List<Order> orderList = orderMapper.selectForLimit(
                    new Query()
                            .setPageSize(pageSize)
                            .setStart((currentPage - 1) * pageSize)
            );

            result.put(currentPage, orderList);

            currentPage++;

        }
        return result;
    }

}
