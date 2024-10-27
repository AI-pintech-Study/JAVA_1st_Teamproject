package org.koreait.member.service;

import org.koreait.global.BeanContainer;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.libs.Utils;
import org.koreait.member.entities.Accession;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LoginSaveService {


    /**
     * 사용자가 입력한 요청 데이터로 회원 정보(Product) 등록 및 수정 처리
     * 요청 데이터 중에서 ID가 있다면 수정, 없다면 추가로 판단
     *
     * @param item
     */

    // ## SAVE만 담당!!!! ##
    public void save(Accession item, boolean fix) {
        File file = new File("Accession.obj");
        Map<String, Accession> data = Utils.load("Accession.obj"); // 회원 정보 가져오기 -> Map 형태. key = value

        LoginInfoService service = BeanContainer.getBean(LoginInfoService.class); // LoginInfo 객체 가져오기 -> 싱글톤

        // ## ID 등록
        String id = item.getUserId();

        if(id.toUpperCase().contains("ADMIN")) {
            item.setUserAdmin(true);
        }
        else {
            item.setUserAdmin(false);
        }

        if(!fix) {
            if (service.get(id) == null) // 있는지 없는지 유효성 체크
            {
                // ## seq가 있으면 수정될거고 있으면 생성될것
                data.put(id, item);
            } else {
                throw new BadRequestException();
            }
        }
        else {
            data.put(id, item);
        }

        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);

        } catch (IOException e) {
        }
    }
}
