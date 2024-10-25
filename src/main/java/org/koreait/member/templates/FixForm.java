package org.koreait.member.templates;

import org.koreait.global.Template;

public class FixForm  implements Template {
    @Override
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("수정 시스템입니다...\n");
        System.out.println(sb);
    }
}
