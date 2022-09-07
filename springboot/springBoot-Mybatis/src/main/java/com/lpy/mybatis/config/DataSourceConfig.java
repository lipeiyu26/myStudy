package com.lpy.mybatis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: Victor Wu
 * @Date: 2018/12/7 10:22
 * @Description:
 */
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {
    private String username;
    private String password;
    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
