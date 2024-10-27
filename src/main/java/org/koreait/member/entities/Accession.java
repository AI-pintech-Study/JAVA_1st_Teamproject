package org.koreait.member.entities;

import java.io.Serializable;

public class Accession implements Serializable {

    private final static long serialVersionUID = 1L;

    private String userId; // ID
    private String userPassword; // Password
    private String userName; // Name
    private String userEmail; // 이메일
    private long userBirth; // 생년월일
    private boolean loginCheck; // 로그인 했는지 안했는지 확인용

    private boolean userAdmin; // 관리자인지 아닌지 확인용.

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

    public void copyFrom(Accession other) { // 객체 복사 붙여넣기.
        this.userId = other.userId;
        this.userPassword = other.userPassword;
        this.userName = other.userName;
        this.userEmail = other.userEmail;
        this.userBirth = other.userBirth;
        this.loginCheck = other.loginCheck;
        this.userAdmin = other.userAdmin;
    }
}
