package org.koreait.main.controllers;

import org.koreait.global.Controller;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.main.templates.BranchMenu;
import org.koreait.main.templates.LoginMenu;
import org.koreait.main.templates.MainMenu;

public class BranchController extends Controller {
    public BranchController() {
        setInputProcess(input -> {
            // 메인 메뉴 사용자 입력 처리
            if (input == null || input.isBlank()) { // 입력이 없다면 함수 종료
                return;
            }

            // 메뉴 이동 처리 S
            if (input.equals("1")) { // 상품관리
                Utils.loadController(ProductBranchController.class); // ProductBranchController 불러오기

            } else if (input.equals("2")) { // 회원관리
                //Utils.loadController(AccessionController.class); // 회원관리 불러오기
            } else { // 그외 메뉴라면 없는 메뉴이므로 메뉴 선택 안내
                throw new BadRequestException("메뉴는 1, 2 중 선택하세요.");
            }
        });
    }

    @Override
    public void view() {
        Utils.loadTpl(BranchMenu.class);  // 분기점 Menu변경완료.
    }
}
