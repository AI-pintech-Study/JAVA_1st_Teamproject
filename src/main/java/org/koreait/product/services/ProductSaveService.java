package org.koreait.product.services;

import org.koreait.global.libs.Utils;
import org.koreait.product.entities.Product;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 상품 추가 및 저장 처리
 *
 */
public class ProductSaveService {


    /**
     * 사용자가 입력한 요청 데이터로 상품 정보(Product) 등록 및 수정 처리
     * 요청 데이터 중에서 등록번호가 있다면 수정, 없다면 추가로 판단
     *
     * @param item
     */

    // 저장만 담당
    public void save(Product item) {
        File file = new File("products.obj");
        Map<Long, Product> data = Utils.load("products.obj");

        // seq = 상품 등록 번호
        long seq = item.getSeq();

        // seq가 없을경우 상품 생성
        if (seq < 1L) {
            System.currentTimeMillis();
        }

        if (data.containsKey(seq)) { // 상품 정보 수정
            item.setSeq(seq);
        }

        item.setModDt(LocalDateTime.now());

        // put = seq가 있으면 지금 넣는 값으로 수정될거고 없으면 새로 생성될것
        data.put(seq, item);

        try (FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);

        } catch (IOException e) {}
    }
}
