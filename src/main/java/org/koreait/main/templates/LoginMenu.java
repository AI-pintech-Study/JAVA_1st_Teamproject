package org.koreait.main.templates;

import org.koreait.global.Template;

public class LoginMenu implements Template {

    @Override
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("1. 로그인\n")
                .append("2. 회원가입\n");

        System.out.println(sb);
    }
}
