package com.lan.sharding.demo.util;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @atuthor lanping
 * @email lanping@cvte.com
 * @Decription TODO
 * @date 2019-04-25 15:59
 */
public class ResourceUtils {


    public static String loadFromResource(String path) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(Class.class
                .getResourceAsStream("/" + path), "utf-8"));
        StringBuffer sb = new StringBuffer();
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        for (int count = reader.read(charBuffer); count > 0; count = reader.read(charBuffer)) {
            sb.append(charBuffer.flip());
        }
        return sb.toString();
    }


    private static void showData(ResultSet rs) throws SQLException {
        System.out.println("------------------------------------------------------------");
        int colCount = rs.getMetaData().getColumnCount();
        for (int i = 0; i < colCount; i++) {
            System.out.print(String.format("%-20s", rs.getMetaData().getColumnName(i + 1)));
        }
        System.out.println();
        int dataCount = 0;
        while (rs.next()) {
            dataCount++;
            for (int i = 0; i < colCount; i++) {
                System.out.print(String.format("%-20s", rs.getObject(i + 1) + " "));
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Get Data rows " + dataCount);
    }


    /**
     * 查询并且输出结果集
     * @param configResourcePath
     * @param querySql
     * @throws Exception
     */
    public static void queryAndOutput(String configResourcePath, String querySql) throws Exception {
        String shardingConfig = ResourceUtils.loadFromResource(configResourcePath);
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(shardingConfig.getBytes("utf-8"));
        Connection connection = dataSource.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(querySql);
        ResourceUtils.showData(rs);
        connection.close();
    }


}
