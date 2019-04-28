package com.lan.sharding.algorithm;


import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Date;

/**
 * @atuthor lanping
 * @email lanping@cvte.com
 * @Decription 自定义分片逻辑 - 按天来分片
 * @date 2019-03-06 19:52
 */
@RequiredArgsConstructor
public class RangeDynamicDateForDayTableShardingAlgorithm implements RangeShardingAlgorithm<Date> {



    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> shardingValue) {
        return null;
    }
}
