package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.libs.Utils;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.services.ProductRemoveService;
import org.koreait.product.templates.ProductList;

import java.util.List;

public class ProductRemoveController extends Controller {
    public ProductRemoveController()
    {
        // 상품삭제 서비스 싱글톤 패턴으로 객체 생성
        ProductRemoveService removeService = BeanContainer.getBean(ProductRemoveService.class);
        setPromptProcess(() -> {
            long seq = Utils.getNumber("상품번호", "상품번호를 입력하세요.");
            String confirm = Utils.getString("정말 삭제하겠습니까?(Y/N)", "Y/N 둘중 입력 하세요.");

            // 삭제 동의(Y)시 삭제(remove) 메서드 실행 ksw
            if (confirm.toUpperCase().equals("Y")) {
                removeService.remove(seq);
            }

            // 삭제 완료 후 상품 메인메뉴 이동
            Utils.loadController(ProductBranchController.class);



        });
    }

    @Override
    protected String getPromptText() {
        return "삭제할 상품번호를 입력하세요(메인메뉴: M, 종료: Q)\n";
    }

    @Override
    public void view() {

        ProductInfoService infoService = BeanContainer.getBean(ProductInfoService.class);
        List<Product> items = infoService.getList();
        Utils.loadTpl(ProductList.class, new Model(items));
        
    }
}
