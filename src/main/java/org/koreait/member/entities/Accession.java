package org.koreait.member.entities;

import java.io.Serializable;

public class Accession implements Serializable {

    private final static long serialVersionUID = 1L;

    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private long userBirth;
    private boolean loginCheck;

    private boolean userAdmin;

    public boolean isLoginCheck() {
        return loginCheck;
    }

    public void setLoginCheck(boolean loginCheck) {
        this.loginCheck = loginCheck;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(boolean userAdmin) {
        this.userAdmin = userAdmin;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(long userBirth) {
        this.userBirth = userBirth;
    }

    @Override
    public String toString() {
        return "Accession{" +
                "userId = '" + userId + '\'' +
                ", userPassword = '" + userPassword + '\'' +
                ", userName = '" + userName + '\'' +
                ", userEmail = '" + userEmail + '\'' +
                ", userBirth = " + userBirth +
                ", userAdmin = " + userAdmin +
                '}';
    }

    public void copyFrom(Accession other) {
        this.userId = other.userId;
        this.userPassword = other.userPassword;
        this.userName = other.userName;
        this.userEmail = other.userEmail;
        this.userBirth = other.userBirth;
        this.loginCheck = other.loginCheck;
    }
}
