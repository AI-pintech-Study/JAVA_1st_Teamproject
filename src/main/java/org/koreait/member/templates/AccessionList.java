package org.koreait.member.templates;

import org.koreait.global.Model;
import org.koreait.global.Template;
import org.koreait.global.libs.Utils;
import org.koreait.member.entities.Accession;

import java.util.List;

public class AccessionList implements Template { // 여기서 출력된 값 정확하게 나오도록 정리함.

    private List<Accession> infos;

    @Override
    public void print() {
        System.out.println("회원목록");
        Utils.drawLine('-', 30);
        if (infos != null && !infos.isEmpty()) {
            for (Accession info : infos) {
                System.out.printf("아이디: %s / 비밀번호: %s / 이름 : %s / 이메일: %s / 생일: %s%n", info.getUserId(), info.getUserPassword(), info.getUserName(), info.getUserEmail(), info.getUserBirth());
            }
            return;
        }

        System.out.println("등록된 정보가 없습니다.");
    }

    @Override
    public void print(Model model) {
        Object data = model.getData();
        if(data != null){
            infos = (List<Accession>)data;
        }

        print();
    }
}
