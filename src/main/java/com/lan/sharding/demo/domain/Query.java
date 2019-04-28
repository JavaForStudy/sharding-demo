package com.lan.sharding.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;


@Setter
@Getter
@Accessors(chain = true)
public class Query {

    private Date startDay;
    private Date endDay;

    private Integer pageSize;
    private int start;
}
