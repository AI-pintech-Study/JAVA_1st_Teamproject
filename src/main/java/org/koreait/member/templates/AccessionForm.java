package org.koreait.member.templates;

import org.koreait.global.Template;

public class AccessionForm implements Template{
    @Override
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("회원가입 시스템입니다...");
        System.out.println(sb);
    }
}
