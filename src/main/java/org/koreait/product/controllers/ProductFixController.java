package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Router;
import org.koreait.global.libs.Utils;
import org.koreait.product.entities.Product;
import org.koreait.product.exceptions.ProductNotFoundException;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.services.ProductSaveService;
import org.koreait.product.templates.ProductForm;

import java.util.Scanner;

/**
 * 상품 등록/수정 컨트롤러
 *
 */
public class ProductFixController extends Controller {

    private final long productSeq; // 수정할 상품 등록 번호

    // 생성자에서 수정할 상품 등록 번호를 받음
    public ProductFixController(long productSeq) {
        this.productSeq = productSeq;

        setPromptProcess(() -> {
            // ## 공통적으로 선 긋기 ##
            Utils.drawLine('-', 30);

            // 상품 정보 로드
            ProductInfoService infoService = BeanContainer.getBean(ProductInfoService.class);
            Product item = infoService.get(productSeq);

            if (item == null) {
                throw new ProductNotFoundException();
            }

            // ## 데이터 클래스 ##
            Scanner sc = Router.sc;

            // 상품명 수정
            String name = Utils.getString("변경 상품명", "현재 상품명: " + item.getName() + ", 새 상품명을 입력하세요.");
            item.setName(name);

            // 판매가 수정
            int price = Utils.getNumber("변경 판매가", "현재 판매가: " + item.getPrice() + ", 새 판매가를 입력하세요.");
            item.setPrice(price);

            // 재고 수정
            int stock = Utils.getNumber("변경 재고", "현재 재고: " + item.getStock() + ", 새 재고를 입력하세요.");
            item.setStock(stock);

            // 상품 정보 저장 처리
            ProductSaveService saveService = BeanContainer.getBean(ProductSaveService.class);
            saveService.save(item, true); // 수정 처리

            System.out.println("상품이 수정되었습니다.");
            // 수정 후 상품 목록으로 이동
            Utils.loadController(ProductListController.class);
        });
    }

    @Override
    protected String getPromptText() {
        return "수정할 상품 정보를 입력하세요(메인 메뉴: M, 종료: Q).\n";
    }

    @Override
    public void view() {
        Utils.loadTpl(ProductForm.class);
    }
}
