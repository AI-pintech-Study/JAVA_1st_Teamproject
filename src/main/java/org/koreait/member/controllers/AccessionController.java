package org.koreait.member.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.LoginController;
import org.koreait.member.entities.Accession;
import org.koreait.member.service.LoginSaveService;
import org.koreait.member.templates.AccessionForm;

import java.util.List;

public class AccessionController extends Controller {

    public AccessionController() {
        setPromptProcess(() -> {

            // ## 공통적으로 선 긋기 ##
            Utils.drawLine('-', 30);

            // ## 데이터 클래스 ##
            Accession item = new Accession();

            String id = Utils.getString("ID", "ID를 입력하세요.", List.of(
                    input -> {
                         if (input.length() >= 5 && input.length() <= 20) {
                             return true;
                         }

                        System.out.println("ID는 5자 이상 20자 이하 입력하세요.");
                        return false;
                    }
            ));
            item.setUserId(id);

            String password = Utils.getString("비밀번호", "비밀번호를 입력하세요.", List.of(
                    input -> {
                        if (input.length() >= 8 && input.length() <= 20) {
                            return true;
                        }
                        System.out.println("비밀번호는 8자 이상 20자 이하 입력하세요.");
                        return false;
                    }
            ));
            item.setUserPassword(password);

            String name = Utils.getString("이름", "이름을 입력하세요.");
            item.setUserName(name);

            String email = Utils.getString("Email", "Email을 입력하세요.", List.of(
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
            item.setUserEmail(email);

            long brith = Utils.getNumber("생년월일", "생년월일을 입력하세요.",List.of(
                    input -> {
                        if (input.length() == 6) {
                            return true;
                        }
                        System.out.println("생년월일을 6글자로 입력해주세요.");
                        return false;
                    }
            ));
            item.setUserBirth(brith);

            System.out.println("확인중입니다.");
            // 저장 이후에 상품 목록으로 페이지 이동

            LoginSaveService saveService = BeanContainer.getBean(LoginSaveService.class);
            saveService.save(item, false);

            // ## 작업 끝난후 다시 상품목록(List)으로 이동 시켜줌 ##
            Utils.loadController(LoginController.class);
        });
    }

    @Override
    public void view() {
        Utils.loadTpl(AccessionForm.class);
    }

    protected String getPromptText() {
        return "정보를 입력하세요.\n";
    }
}
