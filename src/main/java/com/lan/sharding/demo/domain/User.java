package com.lan.sharding.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;


@Setter
@Getter
@Accessors(chain = true)
public class User {
    private String userName;
    private Date date;
    private Long userId;
    private Date createTime;
}