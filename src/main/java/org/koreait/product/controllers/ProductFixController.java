package org.koreait.product.controllers;


import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.libs.Utils;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.templates.ProductList;

import java.util.List;

public class ProductFixController extends Controller {
    public ProductFixController()
    {
        setPromptProcess(() -> {
            long seq = Utils.getNumber("상품번호", "상품번호를 입력하세요.");
            String confirm = Utils.getString("수정하시겠습니까?(Y/N)", "Y/N 둘중 입력 하세요.");

            ProductInfoService service = BeanContainer.getBean(ProductInfoService.class);
            Product item = service.get(seq);

            // 수정하겠다(Y) 선택시 item값 가지고 수정 컨트롤러로 이동 ksw
            if (confirm.toUpperCase().equals("Y")) {
                Utils.loadController(ProductModifyController.class, new Model(item));
            }
            else {
                // 다시 상품 메인메뉴로 이동
                Utils.loadController(ProductBranchController.class);
            }


        });
    }

    @Override
    protected String getPromptText() {
        return "수정할 상품번호를 입력하세요(메인메뉴: M, 종료: Q)\n";
    }

    @Override
    public void view() {

        ProductInfoService infoService = BeanContainer.getBean(ProductInfoService.class); // 싱글톤패턴으로 InfoService 객체 생성
        List<Product> items = infoService.getList(); // 객체 생성 후 넣어주기
        Utils.loadTpl(ProductList.class, new Model(items)); // 결과값 출력

    }
}
