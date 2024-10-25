package org.koreait.product.services;

import org.koreait.global.BeanContainer;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.member.entities.Accession;
import org.koreait.product.entities.Product;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ProductBuyService {

    /**
     * 사용자가 입력한 요청 데이터로 상품 정보(Product) 등록 및 수정 처리
     * 요청 데이터 중에서 등록번호가 있다면 수정, 없다면 추가로 판단
     *
     * @param item
     */

    // ## SAVE만 담당!!!! ##
    public void buy(long seq, int count) {
        File file = new File("products.obj");
        Map<Long, Product> data = load();
        // ## seq = 상품 등록 번호
//        long seq = item.getSeq();

        // ## seq가 없을경우 상품 생성
//        if (seq < 1L) seq = System.currentTimeMillis();

        // ## seq가 있을 경우 상품 수정


        if (data.get(seq) != null) { // 상품 구매

            ProductInfoService service = BeanContainer.getBean(ProductInfoService.class);
            Product product = service.get(seq);

//            재고 불러와서 구매한 개수 빼기
            int stock =  product.getStock() - count;
            product.setStock((int)stock);

            data.put(seq, product);



            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(data);

            } catch (IOException e) {}

        }
        else { // 상품 정보 등록
            throw new BadRequestException();
        }



    }

    /**
     * 상품 정보 목록 파일에서 로드
     *
     * @return
     */
    private Map<Long, Product> load() {
        File file = new File("products.obj");
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream oos = new ObjectInputStream(fis)) {
                // ## 상품 목록을 Map형태로 가져옴
                Map<Long, Product> data = (Map<Long, Product>) oos.readObject();
                return data;
            } catch (Exception e) {}
        }

        return new HashMap<>();
    }

}
