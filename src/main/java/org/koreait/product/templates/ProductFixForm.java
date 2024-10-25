package org.koreait.product.templates;

import org.koreait.global.Template;

public class ProductFixForm implements Template {
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("수정하시겠습니까? 1 - Yes, 2 - No.");
        System.out.println(sb);
    }
}