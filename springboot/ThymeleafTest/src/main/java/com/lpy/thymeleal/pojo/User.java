package com.lpy.thymeleal.pojo;

/**
 * @Auther:LPY
 * @Data:${DATA}
 * @Description:com.lpy.thymeleal.pojo
 * @version:1.0
 */
public class User {
    private String name;
    private String age;

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }
}
