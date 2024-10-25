package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.Router;
import org.koreait.global.libs.Utils;
import org.koreait.global.validators.RequiredValidator;
import org.koreait.global.validators.TypeValidator;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductBuyService;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.services.ProductRemoveService;
import org.koreait.product.services.ProductSaveService;
import org.koreait.product.templates.ProductList;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductBuyController extends Controller implements RequiredValidator, TypeValidator {

    public ProductBuyController() {
        ProductBuyService buyService = BeanContainer.getBean(ProductBuyService.class);
        setPromptProcess(() -> {

            long seq = Utils.getNumber("상품번호", "상품번호를 입력하세요.");

            int count = Integer.parseInt(Utils.getString("몇 개를 사시겠습니까?", "숫자를 입력하세요"));
            if (count >= 0) {

                buyService.buy(seq, count);
            }
            /* 유효성 검사 E */

            // 선택한 상품 번호와 함께 상품 상세로 이동
            // ## 유효성 검사 통과시 상품 데이터 넘겨줌
            // Model에 상품 번호를 Long값으로 넘겨줌 ##

//            item.setBuy(buy);

            // ## 작업 끝난후 다시 상품목록(List)으로 이동 시켜줌 ##
            Utils.loadController(ProductListController.class);
        });
    }

    @Override
    protected String getPromptText() {

        return "구매할 상품 번호를 입력하세요(메인메뉴: M, 종료: Q)\n";

    }

    @Override
    public void view() {

        ProductInfoService infoService = BeanContainer.getBean(ProductInfoService.class);
        List<Product> items = infoService.getList();
        Utils.loadTpl(ProductList.class, new Model(items));

    }
}