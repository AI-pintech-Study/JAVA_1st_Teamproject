package org.koreait.member.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.member.entities.Accession;
import org.koreait.member.templates.ManagementForm;

public class ManagementController extends Controller {
    public ManagementController() {
        /**
         * 사용자가 입력한 입력 데이터 수신 및 처리
         * - 사용자가 입력한 데이터의 처리는 컨트롤러마다 다를 수 있음, 따라서 사용자 정의 기능 형태(열린 기능)으로 접근합니다.
         * - 이는 함수형 프로그래밍의 스타일이며, 매개변수로 함수가 쓰이는 형태입니다. 다만 자바는 함수를 값으로 사용 불가하므로 람다식(인터페이스가 객체가 되는 조건을 축약한 방법)과 이 함수형 인터페이스는 자바에서 이미 제공하고 있는 Consumer 인터페이스를 사용합니다.
         * Consumer 인터페이스는 void accept(T t) 으로 공급(사용자 입력)은 있지만 반환값은 없는, 즉 내부에서 처리하고 끝나는 유형을 정의 한것으로 이해하시면 됩니다.
         */
        setInputProcess(input -> {
            // 메인 메뉴 사용자 입력 처리
            if (input == null || input.isBlank()) { // 입력이 없다면 함수 종료
                return;
            }

            // 메뉴 이동 처리 S
            if (input.equals("1")) { // 회원 정보 수정
                Utils.loadController(MemberFixController.class);
            } else if (input.equals("2")) { // 회원 탈퇴
                Utils.loadController(RemoveController.class);
            }

            Accession acc = BeanContainer.getBean(Accession.class);


            if(acc.isUserAdmin()) {
                if (input.equals("3")) {
                    Utils.loadController(BranchInquiryController.class); // 회원 조회 -> 이건 관리자 권한으로 나눠야함.
                }
            }
            else { // 그외 메뉴라면 없는 메뉴이므로 메뉴 선택 안내
                throw new BadRequestException("메뉴에 있는 메뉴 중 선택하세요.");
            }
            // 메뉴 이동 처리 E
        });
    }

    @Override
    public void view() {
        // 템플릿 출력
        Utils.loadTpl(ManagementForm.class);
    }

}
