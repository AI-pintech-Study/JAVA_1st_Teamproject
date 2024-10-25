package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.ProductBranchController;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductFixService;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.services.ProductSaveService;
import org.koreait.product.templates.ProductList;

import java.util.List;

public class ProductFixController extends Controller {
    public ProductFixController()
    {
        ProductFixService fixService = BeanContainer.getBean(ProductFixService.class);
        setPromptProcess(() -> {
            long seq = Utils.getNumber("상품번호", "상품번호를 입력하세요.");
            String confirm = Utils.getString("수정하시겠습니까?(Y/N)", "Y/N 둘중 입력 하세요.");

            ProductInfoService service = BeanContainer.getBean(ProductInfoService.class);
            Product item = service.get(seq);

            if (confirm.toUpperCase().equals("Y")) {
                Utils.loadController(ProductModifyController.class, new Model(item));
            }
            else {
                // 삭제 완료 후 상품 메인메뉴 이동
                Utils.loadController(ProductBranchController.class);
            }


        });
        /*
        setInputProcess(input -> {
            // 메인 메뉴 사용자 입력 처리
            if (input == null || input.isBlank()) { // 입력이 없다면 함수 종료
                return;
            }

            Utils.loadController(ProductListController.class);

            // 메뉴 이동 처리 S
            if (input.equals("1")) { // 삭제하시겠습니까? 문구.
                ProductFixServiceService remove = BeanContainer.getBean(ProductFixServiceService.class);
                Product acc = BeanContainer.getBean(Product.class);
                ProductFixService(acc);
                Utils.loadController(ProductController.class);
            }
            else if (input.equals("2")) {
                Utils.loadController(null);
            }
            else { // 그외 메뉴라면 없는 메뉴이므로 메뉴 선택 안내
                throw new BadRequestException("메뉴에 있는 메뉴 중 선택하세요.");
            }
            // 메뉴 이동 처리 E
        });

         */
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
