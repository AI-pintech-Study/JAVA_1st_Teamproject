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

            // 메뉴 이동 처리 S
            if (input.equals("1")) { // 탈퇴하시겠습니까? 문구.
                LoginRemoveService remove = BeanContainer.getBean(LoginRemoveService.class);
                Accession acc = BeanContainer.getBean(Accession.class);
                remove.Remove(acc);
                Utils.loadController(LoginController.class);
            }
            else if (input.equals("2")) {
                Utils.loadController(null);
            }
            else { // 그외 메뉴라면 없는 메뉴이므로 메뉴 선택 안내
                throw new BadRequestException("메뉴에 있는 메뉴 중 선택하세요.");
            }
            // 메뉴 이동 처리 E
        });
    }

    @Override
    public void view() {
        Utils.loadTpl(RemoveForm.class);
    }
}
