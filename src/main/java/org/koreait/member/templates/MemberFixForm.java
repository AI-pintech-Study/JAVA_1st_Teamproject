package org.koreait.member.templates;

import org.koreait.global.Model;
import org.koreait.global.Template;
import org.koreait.member.entities.Accession;
import org.koreait.product.entities.Product;

public class MemberFixForm implements Template {
    private Accession item;

    @Override
    public void print() {
        System.out.println("회원 정보...");
        System.out.println(item);
    }

    @Override
    public void print(Model model) {

        Object data = model.getData();
        if (data != null) {
            item = (Accession)data;
        }

        print();
    }
}
