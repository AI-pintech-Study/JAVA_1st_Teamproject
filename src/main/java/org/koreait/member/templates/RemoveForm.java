package org.koreait.member.templates;

import org.koreait.global.Template;

public class RemoveForm implements Template {
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("탈퇴하시겠습니까? 1 - Yes, 2 - No.");
        System.out.println(sb);
    }
}
