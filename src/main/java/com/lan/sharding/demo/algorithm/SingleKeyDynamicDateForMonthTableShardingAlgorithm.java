package com.lan.sharding.demo.algorithm;


import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * @atuthor lanping
 * @email lanping@cvte.com
 * @Decription 自定义分片逻辑 - 按月来分片
 * @date 2019-03-06 19:52
 */
@RequiredArgsConstructor
public class SingleKeyDynamicDateForMonthTableShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
        Date date = shardingValue.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String logicTableName = shardingValue.getLogicTableName();
        return logicTableName + "_"+sdf.format(date);
    }

}
