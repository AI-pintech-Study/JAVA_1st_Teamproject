package org.koreait.member.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.LoginController;
import org.koreait.member.entities.Accession;
import org.koreait.member.service.LoginSaveService;
import org.koreait.member.templates.FixForm;

import java.util.List;

public class RealFixController extends Controller {

    public RealFixController() {
        setPromptProcess(() -> {

            // ## 공통적으로 선 긋기 ##
            Utils.drawLine('-', 30);

            // ## 데이터 클래스 ##
            Accession item = BeanContainer.getBean(Accession.class);
            Accession fixitem = new Accession(); // 수정되는 정보

            fixitem.copyFrom(item); // 수정되는 정보에 현재 객체를 데이터 복사.

            String password = Utils.getString("수정 할 비밀번호", "비밀번호를 입력하세요.", List.of( // 제약조건 확인.
                    input -> {
                        if (input.length() >= 8 && input.length() <= 20) {
                            return true;
                        }
                        System.out.println("비밀번호는 8자 이상 20자 이하 입력하세요.");
                        return false;
                    }
            ));
            fixitem.setUserPassword(password);

            String email = Utils.getString("수정 할 Email", "Email을 입력하세요.", List.of( // 제약조건 확인.
                    input -> {
                        String CheckEmail;
                        if(input.contains("@")) {
                            CheckEmail = input.substring(0, input.indexOf("@"));
                            System.out.println(CheckEmail);
                            if (CheckEmail.length() >= 3) {
                                return true;
                            }
                        }
                        System.out.println("이메일 형식을 재대로 입력하세요.");
                        return false;
                    }
            ));
            fixitem.setUserEmail(email);

            System.out.println("확인중입니다.");

            LoginSaveService saveService = BeanContainer.getBean(LoginSaveService.class); // 객체 싱글톤패턴 생성
            saveService.save(fixitem, true); // 현재 객체 저장.

            Utils.loadController(ManagementController.class);
        });
    }

    @Override
    protected String getPromptText() {
        return "수정할 수 있는 항목은 비밀번호와 이메일입니다.\n";
    }

    @Override
    public void view() {
        Utils.loadTpl(FixForm.class);
    }
}
