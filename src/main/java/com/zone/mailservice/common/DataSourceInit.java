package com.zone.mailservice.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

/**
 * @Classname DataSourceInit
 * @Description
 * @Date 2021/8/19 12:37 上午
 * @Created by zone
 */
@Configuration
@Slf4j
public class DataSourceInit {

    @Value("${spring.datasource.driver-class-name}")
    private String dirver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.name}")
    private String databaseName;

    @Value("${spring.sql.init.data-locations}")
    private String mailSql;

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SpringContextGetter springContextGetter;

    private static final String SCHEMA_NAME = "schema_name";

    @PostConstruct
    public void init() {
        URI uri = null;
        try {
            Class.forName(dirver);
            uri = new URI(url.replace("jdbc:", ""));
        } catch (ClassNotFoundException | URISyntaxException e) {
            log.error("JDBC URL解析错误", e);
        }
        String host = uri.getHost();
        int port = uri.getPort();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" +
                port + "?characterEncoding=utf8&useSSL=false&&allowMultiQueries=true&serverTimezone=Asia/Shanghai", username, password);
             Statement statement = connection.createStatement()) {

            String selectDatabase = "select schema_name from information_schema.schemata where schema_name = " + "'" + databaseName + "'";

            //1、查询返回的结果集
            ResultSet resultSet = statement.executeQuery(selectDatabase);
            if (!resultSet.next()) {
                //2、查不到数据库，执行数据库初始化脚本
                log.warn("查不到数据库({})", databaseName);
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("database/mail.sql");

                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                String createDb = "CREATE DATABASE IF NOT EXISTS " + databaseName;
                connection.setAutoCommit(false);
                statement.execute(createDb);
                connection.commit();
                log.info("创建数据库（{}）成功");
            } else {
                String databaseName = resultSet.getString(SCHEMA_NAME);
                log.warn("已经存在数据库({})", databaseName);
            }
            if (resultSet.isClosed()) {
                resultSet.close();
            }

            //3、执行sql脚本
            Resource resource = springContextGetter.getApplicationContext().getResource(mailSql);
            ScriptUtils.executeSqlScript(dataSource.getConnection(), resource);
        } catch (SQLException e) {
            log.error("启动项目检查数据库是否创建", e);
        }
    }
}