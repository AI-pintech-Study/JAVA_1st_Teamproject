package org.koreait.member.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.LoginController;
import org.koreait.member.entities.Accession;
import org.koreait.member.service.LoginSaveService;
import org.koreait.member.templates.AccessionForm;

public class AccessionController extends Controller {

    public AccessionController() {
        setPromptProcess(() -> {

            // ## 공통적으로 선 긋기 ##
            Utils.drawLine('-', 30);

            // ## 데이터 클래스 ##
            Accession item = new Accession();

            String id = Utils.getString("ID", "ID를 입력하세요.");
            item.setUserId(id);

            String password = Utils.getString("비밀번호", "비밀번호를 입력하세요.");
            item.setUserPassword(password);

            String name = Utils.getString("이름", "이름을 입력하세요.");
            item.setUserName(name);

            String email = Utils.getString("Email", "Email을 입력하세요.");
            item.setUserEmail(email);

            int brith = Utils.getNumber("생년월일", "생년월일을 입력하세요.");
            item.setUserBirth(brith);

            System.out.println("확인중입니다.");
            // 저장 이후에 상품 목록으로 페이지 이동

            LoginSaveService saveService = BeanContainer.getBean(LoginSaveService.class);
            saveService.save(item);

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
