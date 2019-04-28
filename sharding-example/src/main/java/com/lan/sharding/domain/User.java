package com.lan.sharding.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;


@Setter
@Getter
@Accessors(chain = true)
public class User {
    private String userName;
    private Date pDate;
    private Long userId;
    private Date createTime;
}
