package com.lan.sharding.demo.algorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Date;

/**
 * @atuthor lanping
 * @email lanping@cvte.com
 * @Decription 工具类
 * @date 2019-03-07 10:03
 */
public class TableShardingAlgorithmUtil {

    static String getTableString(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue, String today) {
        String tablePrefix = shardingValue.getLogicTableName();
        String result = tablePrefix + "_" + today;
        if (availableTargetNames.stream().anyMatch(it -> it.equals(tablePrefix))) {
            return result;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
