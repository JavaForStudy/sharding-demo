package com.lan.sharding.demo.test;


import com.lan.sharding.demo.util.ResourceUtils;

/**
 * @atuthor lanping
 * @email lanping@cvte.com
 * @Decription TODO
 * @date 2019-04-25 11:26
 */
public class testIn {


    public static void main(String[] args) throws Exception {

        ResourceUtils.queryAndOutput("sharding-tables.yaml",
                "select * from t_user where c_date in ('2019-03-01','2019-06-08')");

    }


}
