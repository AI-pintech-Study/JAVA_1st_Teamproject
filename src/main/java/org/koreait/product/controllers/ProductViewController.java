package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.libs.Utils;
import org.koreait.product.entities.Product;
import org.koreait.product.exceptions.ProductNotFoundException;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.templates.ProductView;

/**
 * 상품 상세 보기
 *
 */
public class ProductViewController extends Controller {

    @Override
    public void view() {

        // ProductListContoller에서 받은 상품 번호
        Object data = getData();
        if (data == null) {
            throw new ProductNotFoundException();
        }
        
        long seq = (long)data; // 상품번호(seq) long타입으로 형변환해 가져옴

        // 상품 조회 객체 싱글톤 패턴(getBean)으로 생성
        ProductInfoService service = BeanContainer.getBean(ProductInfoService.class);

        Product item = service.get(seq);

        // 상품 정보와 함께 출력
        // 상품 정보를 출력해야 하므로 데이터가 있는 Model
        Utils.loadTpl(ProductView.class, new Model(item));
    }
}
