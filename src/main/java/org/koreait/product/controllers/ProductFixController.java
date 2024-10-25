package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.LoginController;
import org.koreait.main.controllers.ProductBranchController;
import org.koreait.member.entities.Accession;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductFixService;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.services.ProductRemoveService;
import org.koreait.product.templates.ProductRemoveForm;
import org.koreait.product.templates.ProductList;

import java.util.List;

public class ProductFixController extends Controller {
    public ProductFixController()
    {
        ProductFixService fixService = BeanContainer.getBean(ProductFixService.class);
        setPromptProcess(() -> {
            long seq = Utils.getNumber("상품번호", "상품번호를 입력하세요.");
            String confirm = Utils.getString("정말 수정하겠습니까?(Y/N)", "Y/N 둘중 입력 하세요.");
            if (confirm.toUpperCase().equals("Y")) {
                fixService.fix(seq);
            }

            // 수정 완료 후 상품 메인메뉴 이동
            Utils.loadController(ProductBranchController.class);
        });

    }

    @Override
    protected String getPromptText() {
        return "수정할 상품번호를 입력하세요(메인메뉴: M, 종료: Q)\n";
    }

    @Override
    public void view() {

        ProductInfoService infoService = BeanContainer.getBean(ProductInfoService.class);
        List<Product> items = infoService.getList();
        Utils.loadTpl(ProductList.class, new Model(items));

    }
}
