package org.koreait.main.controllers;

import org.koreait.global.Controller;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.main.templates.LoginMenu;
import org.koreait.member.controllers.AccessionController;
import org.koreait.member.controllers.LoginCheckController;

/**
 * 콘솔 프로그램 로그인 컨트롤러
 *
 */
public class LoginController extends Controller {
    public LoginController() {
        setInputProcess(input -> {
            // 메인 메뉴 사용자 입력 처리
            if (input == null || input.isBlank()) { // 입력이 없다면 함수 종료
                return;
            }

            // 메뉴 이동 처리 S
            if (input.equals("1")) { // 로그인 창 띄우기
                Utils.loadController(LoginCheckController.class); // LoginController 불러오기
            } else if (input.equals("2")) { // 회원가입 창 띄우기
                Utils.loadController(AccessionController.class); // AccessionController 불러오기
            } else { // 그외 메뉴라면 없는 메뉴이므로 메뉴 선택 안내
                throw new BadRequestException("메뉴는 1, 2 중 선택하세요.");
            }
        });
    }

    @Override
    public void view() {
        Utils.loadTpl(LoginMenu.class);  // Login Menu변경완료.
    }
}
