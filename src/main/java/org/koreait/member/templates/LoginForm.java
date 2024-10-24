package org.koreait.member.templates;

import org.koreait.global.Template;

public class LoginForm implements Template{
    @Override
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("로그인 시스템입니다...");
        System.out.println(sb);
    }
}
