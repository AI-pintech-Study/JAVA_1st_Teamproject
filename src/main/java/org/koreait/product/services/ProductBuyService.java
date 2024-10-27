package org.koreait.product.services;

import org.koreait.global.BeanContainer;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.member.entities.Accession;
import org.koreait.product.entities.Product;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ProductBuyService {


    // Buy만 담당
    public void buy(long seq, int count) {
        File file = new File("products.obj");
        Map<Long, Product> data = Utils.load("products.obj");


        if (data.get(seq) != null) { // 상품 구매

            ProductInfoService service = BeanContainer.getBean(ProductInfoService.class);
            Product product = service.get(seq);

            // 재고 get해와서 구매 개수 빼기 연산 한후 바로 새 변수(stock) 선언과 동시에 값 초기화
            int stock =  product.getStock() - count;

            // 연산한 새 재고 값을 새로 set
            product.setStock(stock);

            // data에 최종적으로 값 put
            data.put(seq, product);


            // 재고가 0이 될경우 상품 삭제
            if (stock == 0) {
                data.remove(seq);
            }

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(data);

            } catch (IOException e) {}

        }
        else {
            throw new BadRequestException();
        }
    }
}
