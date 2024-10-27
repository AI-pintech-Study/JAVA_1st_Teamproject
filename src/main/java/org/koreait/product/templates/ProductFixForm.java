package org.koreait.product.templates;

import org.koreait.global.Template;

public class ProductFixForm implements Template {
    public void print() {
            StringBuffer sb = new StringBuffer();
            sb.append(" 수정하고 있습니다.\n ");
            System.out.println(sb);
    }
}

