package org.koreait.global.libs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.koreait.main.controllers.ProductBranchController;
import org.koreait.main.templates.MainMenu;

public class UtilsTest {
    @Test
    @DisplayName("템플릿 출력 테스트")
    void loadTplTest() {
        Utils.loadTpl(MainMenu.class);
    }

    @Test
    @DisplayName("컨트롤러 실행 테스트")
    void loadControllerTest() {
        ProductBranchController controller = Utils.loadController(ProductBranchController.class);
        System.out.println(controller);
    }
}
