package com.xymiao.cms.pojo.auth;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@TableName("SYS_USER")
public class SysUser implements Serializable {
    @TableId
    private String userId;
    private String nickName;
    private String loginId;
    private String userPwd;
    private LocalDateTime lastTime;
    private LocalDateTime created;

    public SysUser(SysUser sysUser) {
        super();
    }

    public SysUser(String userId, String loginId, String userPwd) {
        this.userId = userId;
        this.loginId = loginId;
        this.userPwd = userPwd;
    }

    public SysUser(String userId, String nickName, String loginId, String userPwd, LocalDateTime lastTime, LocalDateTime created) {
        this.userId = userId;
        this.nickName = nickName;
        this.loginId = loginId;
        this.userPwd = userPwd;
        this.lastTime = lastTime;
        this.created = created;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId='" + userId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", loginId='" + loginId + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", lastTime=" + lastTime +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUser sysUser = (SysUser) o;
        return Objects.equals(userId, sysUser.userId) && Objects.equals(nickName, sysUser.nickName) && Objects.equals(loginId, sysUser.loginId) && Objects.equals(userPwd, sysUser.userPwd) && Objects.equals(lastTime, sysUser.lastTime) && Objects.equals(created, sysUser.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, nickName, loginId, userPwd, lastTime, created);
    }
}
