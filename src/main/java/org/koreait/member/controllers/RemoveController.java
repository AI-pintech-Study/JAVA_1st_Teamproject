package org.koreait.member.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.LoginController;
import org.koreait.member.entities.Accession;
import org.koreait.member.service.LoginRemoveService;
import org.koreait.member.templates.RemoveForm;

public class RemoveController extends Controller {
    public RemoveController()
    {
        setInputProcess(input -> {
            // 메인 메뉴 사용자 입력 처리
            if (input == null || input.isBlank()) { // 입력이 없다면 함수 종료
                return;
            }

            // 회원 탈퇴 처리 S
            if (input.equals("1")) { // 탈퇴하시겠습니까? 문구.
                LoginRemoveService remove = BeanContainer.getBean(LoginRemoveService.class); // 탈퇴
                Accession acc = BeanContainer.getBean(Accession.class); // 싱글톤패턴으로 현재 객체 가져오기.
                remove.Remove(acc); // remove로 삭제.
                Utils.loadController(LoginController.class); // 삭제가 되었기에 Login Controller 로 다시 로그인 시작.
            }
            else if (input.equals("2")) { // 탈퇴 안하면 그냥 바로 메뉴 선택.
                Utils.loadController(ManagementController.class);
            }
            else { // 그외 메뉴라면 없는 메뉴이므로 메뉴 선택 안내
                throw new BadRequestException("메뉴에 있는 메뉴 중 선택하세요.");
            }
            // 회원 탈퇴 처리 E
        });
    }

    @Override
    public void view() {
        Utils.loadTpl(RemoveForm.class);
    }
}
