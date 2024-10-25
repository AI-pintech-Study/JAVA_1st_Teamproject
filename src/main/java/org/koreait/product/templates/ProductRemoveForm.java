package org.koreait.product.templates;

import org.koreait.global.Template;

public class ProductRemoveForm implements Template {
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("삭제하시겠습니까? 1 - Yes, 2 - No.");
        System.out.println(sb);
    }
}
