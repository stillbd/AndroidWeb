package net.xinqusi.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String userName;

    private String password;

    private Date creatTime;

    private Date loginTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreattime() {
        return creatTime;
    }

    public void setCreattime(Date creattime) {
        this.creatTime = creattime;
    }

    public Date getUpdatetime() {
        return loginTime;
    }

    public void setUpdatetime(Date updatetime) {
        this.loginTime = updatetime;
    }
}