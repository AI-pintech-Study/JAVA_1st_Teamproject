package org.koreait.product.services;

import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.product.entities.Product;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ProductRemoveService {

    /**
     * 사용자가 입력한 요청 데이터로 상품 정보(Product) 삭제 처리
     * 요청 데이터 중에서 해당 seq가 있다면 삭제, 없다면 추가로 판단
     *
     * @param
     */

    // 삭제만 담당
    public void remove(long seq) {
        File file = new File("products.obj");
        Map<Long, Product> data = Utils.load("products.obj"); // 회원 정보 가져오기 -> Map 형태. key = value


        // seq = 상품 등록 번호
        if (data.get(seq) != null) // 해당 상품이 있는지 없는지 유효성 체크
        {
            data.remove(seq);
            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(data);
                return;

            } catch (IOException e) {}
        }
        throw new BadRequestException();
    }
}