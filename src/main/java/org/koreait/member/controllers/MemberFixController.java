package org.koreait.member.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.global.validators.RequiredValidator;
import org.koreait.global.validators.TypeValidator;
import org.koreait.member.entities.Accession;
import org.koreait.member.service.LoginInfoService;
import org.koreait.member.templates.MemberFixForm;
import org.koreait.product.controllers.ProductViewController;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.templates.ProductList;

import java.util.List;
import java.util.Map;

public class MemberFixController extends Controller implements TypeValidator, RequiredValidator {

    public MemberFixController() {

        // ## 사용자 요청 input
        // 한개의 값 비교
        // 값 1개 들어옴(input) 나가는 값(output) 없음 ##
        setInputProcess(input -> {
            /* 유효성 검사 S */
            if (!check(input)) { // 필수 항목 체크
                return;
            }

            // ## 숫자 체크 ##
            if (!isNumber(input)) {
                System.out.println("상품 번호는 숫자만 입력하세요.");
                return;
            }

            if (input.equals("1")) { // 수정
                Utils.loadController(RealFixController.class);
            }
            else if (input.equals("2")) { // 노수정
                Utils.loadController(ManagementController.class);
            }
            else { // 그외 메뉴라면 없는 메뉴이므로 메뉴 선택 안내
                throw new BadRequestException("메뉴에 있는 메뉴 중 선택하세요.");
            }
        });
    }

    @Override
    protected String getPromptText() {
        return "회원님 정보를 수정하시겠습니까? Yes - 1, No - 2\n";
    }

    @Override

    // ## 요청받은 상품 목록 데이터 출력으로 오버라이딩
    // 단 기능이므로 여러개 있을 필요 없어 싱글톤(getBean) ##
    public void view() {
        LoginInfoService service = BeanContainer.getBean(LoginInfoService.class); // LoginInfo 객체 가져오기 -> 싱글톤
        Accession checkId = BeanContainer.getBean(Accession.class); // 회원정보도 불러오기

        Accession items = service.get(checkId.getUserId()); // 아이디가 있는지 없는지 체크하는 목록. 아이디가 없으면 null로 출력.
        Utils.loadTpl(MemberFixForm.class, new Model(items)); // MemberFixForm 불러오기. 데이터도 같이 넣어줌.
    }
}
