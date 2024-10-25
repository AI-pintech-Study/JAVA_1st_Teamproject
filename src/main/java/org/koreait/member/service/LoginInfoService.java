package org.koreait.member.service;

import org.koreait.member.entities.Accession;
import org.koreait.product.exceptions.ProductNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LoginInfoService {
    /**
     * 아이디로 체크
     *  - 아이디가 없다면 null로. -> 유효성 검사
     *
     * @param id
     * @return
     */
    public Accession get(String id) { // 
        List<Accession> items = getList(false);

        // ## filter 참고로만 보기
        Accession item = items.stream().filter(i -> i.getUserId().equals(id)).findFirst().orElse(null);

        return item;
    }

    /**
     * 전체 목록 가져오기
     *
     * @param isDesc : false - 기본 상품번호 오름차순으로 기본 정렬, true - 상품번호 기준 내림차순으로 정렬
     * @return
     */
    public List<Accession> getList(boolean isDesc) {
        File file = new File("Accession.obj");
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream oos = new ObjectInputStream(fis)) {
                Map<Long, Accession> data = (Map<Long, Accession>)oos.readObject();

                // ## 오름차순 내림차순 참고로만 보기
                if (data != null && !data.isEmpty()) {
                    List<Accession> items = data.values().stream().sorted((i1, i2) -> isDesc ? i1.getUserId().compareTo(i2.getUserId()) : i2.getUserId().compareTo(i1.getUserId())).toList();
                    return items;
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return Collections.EMPTY_LIST;
    }

    //region 사용할 일이 없을거같음

    /**
     * 기본 상품 목록 조회 (오름차순)
     *
     * @return
     */
    public List<Accession> getList() {
        return getList(false);
    }

    //endregion
}