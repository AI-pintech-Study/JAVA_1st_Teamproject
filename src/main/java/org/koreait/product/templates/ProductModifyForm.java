package org.koreait.product.templates;

import org.koreait.global.Template;

public class ProductModifyForm implements Template {
    @Override
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("상품 등록 수정중...\n");
        System.out.println(sb);
    }
}
