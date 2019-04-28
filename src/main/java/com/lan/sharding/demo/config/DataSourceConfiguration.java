package com.lan.sharding.demo.config;

import com.lan.sharding.demo.util.ResourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @atuthor lanping
 * @email lanping@cvte.com
 * @Decription TODO
 * @date 2019-04-25 16:10
 */
@Slf4j
@Configuration
public class DataSourceConfiguration {


    @Bean("dataSource")
    public DataSource dataSource() {
        DataSource dataSource;
        try {
            String shardingConfig = ResourceUtils.loadFromResource("sharding-tables.yaml");
            dataSource = YamlShardingDataSourceFactory.createDataSource(shardingConfig.getBytes("utf-8"));
        } catch (Exception e ) {
            throw new RuntimeException("加载数据源配置异常... ");
        }
        return dataSource;
    }


}
