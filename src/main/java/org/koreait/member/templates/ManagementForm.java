package org.koreait.member.templates;

import org.koreait.global.Template;

public class ManagementForm implements Template {
    @Override
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("1. 회원 정보 수정\n")
                .append("2. 회원 탈퇴\n")
                        .append("3. 회원 정보 출력\n");

        System.out.println(sb);
    }
}
