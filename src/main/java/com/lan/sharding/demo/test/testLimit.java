package com.lan.sharding.demo.test;


import com.lan.sharding.demo.util.ResourceUtils;

/**
 * @atuthor lanping
 * @email lanping@cvte.com
 * @Decription TODO
 * @date 2019-04-25 11:26
 */
public class testLimit {


    public static void main(String[] args) throws Exception {

        int pageSize = 5;
        Integer total = 15;
        int currentPage = 1,
                totalPage = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1);
        while(currentPage <= totalPage) {

            ResourceUtils.queryAndOutput("sharding-tables.yaml",
                    "select * from t_user limit " + (currentPage-1) * pageSize + "," + pageSize );
            currentPage ++;
        }

    }

}
