package org.koreait.member.templates;

import org.koreait.global.BeanContainer;
import org.koreait.global.Template;
import org.koreait.member.entities.Accession;

public class ManagementForm implements Template {
    @Override
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("1. 회원 정보 수정\n")
                .append("2. 회원 탈퇴\n");
        Accession acc = BeanContainer.getBean(Accession.class);
        if(acc.isUserAdmin()) {
            sb.append("3. 회원 정보 조회\n");
        }


        System.out.println(sb);
    }
}
