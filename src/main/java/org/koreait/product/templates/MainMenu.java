package org.koreait.product.templates;

import org.koreait.global.BeanContainer;
import org.koreait.global.Template;
import org.koreait.member.entities.Accession;

public class MainMenu implements Template {

    /**
     * 메인 화면 메뉴 출력
     *
     */
    @Override
    public void print() {

        Accession acc = BeanContainer.getBean(Accession.class);
        StringBuffer sb = new StringBuffer();
        sb.append("1. 상품목록\n")
                .append("2. 상품구매\n");


        if(acc.isUserAdmin())
        {
            sb.append(("3. 상품삭제\n"))
                    .append(("4. 상품수정\n"))
                    .append(("5. 상품등록"));
        }
        System.out.println(sb);
    }
}
