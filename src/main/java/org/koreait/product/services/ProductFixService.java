package org.koreait.product.services;

import org.koreait.global.exceptions.BadRequestException;
import org.koreait.product.entities.Product;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ProductFixService {

    /**
     * 사용자가 입력한 요청 데이터로 회원 정보(Product) 등록 및 수정 처리
     * 요청 데이터 중에서 ID가 있다면 수정, 없다면 추가로 판단
     *
     * @param
     */

    // ## SAVE만 담당!!!! ##
    public void fix(Product item) {
        File file = new File("products.obj");
        Map<Long, Product> data = load(); // 회원 정보 가져오기 -> Map 형태. key = value


        // ## seq = 상품 등록 번호
        // ## seq가 없을경우 상품 생성
        // if (seq < 1L) seq = System.currentTimeMillis();

        if (data.get(seq) != null) // 있는지 없는지 유효성 체크
        {
            data.put(seq, );

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(data);

            } catch (IOException e) {}
        }
        else
        {
            throw new BadRequestException();
        }
    }

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
