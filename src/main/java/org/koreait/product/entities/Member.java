package org.koreait.product.entities;

import java.time.LocalDateTime;

public class Member {

    private long UserNumber; // 회원번호(가능하면?)

    private String UserId; // 유저 아이디
    private String UserPassword; // 유저 비밀번호
    private String UserEmail; // 유저 이메일
    private String UserName; // 유저 이름
    private String UserBirth; // 유저 생일

    private LocalDateTime JoinDate; // 가입날짜 (이것도 가능하면?)

    public long getUserNumber() {
        return UserNumber;
    }

    public void setUserNumber(long userNumber) {
        UserNumber = userNumber;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserBirth() {
        return UserBirth;
    }

    public void setUserBirth(String userBirth) {
        UserBirth = userBirth;
    }

    public LocalDateTime getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        JoinDate = joinDate;
    }
}
