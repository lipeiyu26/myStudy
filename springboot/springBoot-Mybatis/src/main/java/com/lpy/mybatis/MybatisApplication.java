package com.lpy.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.lpy.mybatis.config.DataSourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(DataSourceConfig.class)
public class MybatisApplication {
    @Autowired
    private DataSourceConfig dataSourceConfig;
	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}
    @Bean
	public DruidDataSource getDruidDataSource(){
	    DruidDataSource druid=new DruidDataSource();
	    druid.setUrl(dataSourceConfig.getUrl());
	    druid.setUsername(dataSourceConfig.getUsername());
	    druid.setPassword(dataSourceConfig.getPassword());
	    return druid;
    }
}

