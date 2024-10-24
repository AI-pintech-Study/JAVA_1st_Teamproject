package org.koreait.member.controllers;
import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.BranchController;
import org.koreait.main.controllers.ProductBranchController;
import org.koreait.member.entities.Accession;
import org.koreait.member.service.LoginInfoService;
import org.koreait.member.templates.LoginForm;

public class LoginCheckController extends Controller {

    public LoginCheckController() {
        setPromptProcess(() -> {

            // ## 공통적으로 선 긋기 ##
            Utils.drawLine('-', 30);

            String message = "잘못된 정보입니다.";

            // ID
            String id = Utils.getString("ID", "ID를 입력하세요.");

            // 비밀번호
            String password = Utils.getString("비밀번호", "비밀번호를 입력하세요.");

            System.out.println("확인중입니다.");

            // 있는지 없는지 유효성 검사 ㄱㄱ
            if (Utils.getIdCheck(id,password)) {
                Utils.loadController(BranchController.class);
            }
            else {
                throw new BadRequestException(message);
            }
        });
    }

    @Override
    public void view() {
        Utils.loadTpl(LoginForm.class);
    }

    protected String getPromptText() {
        return "ID와 비밀번호를 입력하세요.\n";
    }
}

