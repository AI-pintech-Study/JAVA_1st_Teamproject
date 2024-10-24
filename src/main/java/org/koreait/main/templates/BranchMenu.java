package org.koreait.main.templates;

import org.koreait.global.Template;

public class BranchMenu implements Template {
    @Override
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("1. 상품관리\n")
                .append("2. 회원관리\n");

        System.out.println(sb);
    }
}
