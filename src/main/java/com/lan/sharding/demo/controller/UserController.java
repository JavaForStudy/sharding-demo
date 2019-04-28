package com.lan.sharding.demo.controller;


import com.lan.sharding.demo.domain.Query;
import com.lan.sharding.demo.domain.User;
import com.lan.sharding.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/saveForCreateTime")
    public String saveForCreateTime(User user) {
        user.setCreateTime(new Date());
        userMapper.insertForCreateTime(user);
        return "保存成功";
    }

    @RequestMapping("/selectAllForMaster")
    public Object selectAllForMaster(User user) {
        Query query = new Query();
        query.setStartDay(new Date());
        query.setEndDay(new Date());
        List<User> users = userMapper.selectAllForMaster(query);
        return users;
    }

    /**
     * 测试分页查询 limit
     *
     * @return
     */
    @RequestMapping("/selectForLimit")
    public Object selectForLimit() {

        Map<Integer, List<User>> result = new HashMap<>();

        Integer total = userMapper.selectCount();
        int pageSize = 4;
        int currentPage = 1;
        int totalPage = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1);
        while (currentPage <= totalPage) {
            List<User> users = userMapper.selectForLimit(
                    new Query()
                            .setPageSize(pageSize)
                            .setStart((currentPage - 1) * pageSize)
            );

            result.put(currentPage, users);

            currentPage++;

        }
        return result;
    }


    /**
     * 测试分片插入
     */
    @RequestMapping("/insertUser")
    public int insertUser() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2019-04-02");
        User user = new User()
                .setCreateTime(new Date())
                .setUserName("张三")
                .setPDate(date);

        return userMapper.insert(user);

    }

    /**
     * 批量插入保存
     * @return
     * @throws ParseException
     */
    @RequestMapping("/insertBatch")
    public int insertBatch() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = sdf.parse("2019-04-12");
        Date date2 = sdf.parse("2019-05-22");
        Date date3 = sdf.parse("2019-06-28");

        User user1 = new User()
                .setCreateTime(new Date())
                .setUserName("小红")
                .setPDate(date1);

        User user2 = new User()
                .setCreateTime(new Date())
                .setUserName("李四")
                .setPDate(date2);
        User user3 = new User()
                .setCreateTime(new Date())
                .setUserName("王五")
                .setPDate(date3);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return userMapper.insertBatch(list);
    }


    /**
     * 测试 In
     * @return
     */
    @RequestMapping("/selectForIn")
    public Object selectForIn() {

        return userMapper.selectForIn();

    }

    /**
     * 测试on duplicate
     * @return
     */
    @RequestMapping("/insertForOnDuplicate")
    public Object insertForOnDuplicate() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = sdf.parse("2019-06-12");

        User user = new User().setUserName("李四").setPDate(date);

        return userMapper.insertForOnDuplicate(user);
    }


}
