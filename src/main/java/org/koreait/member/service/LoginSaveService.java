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

        if(id.toUpperCase().contains("ADMIN")) { // 저장 할 당시에 admin 이라는 이름이 있으면 그 해당 회원 객체에 관리자 권한을 ture시킴.
            item.setUserAdmin(true);
        }
        else {
            item.setUserAdmin(false);
        }

        if(!fix) { // 수정 안할 때.
            if (service.get(id) == null) // 있는지 없는지 유효성 체크
            {
                // ID가 있으면 put으로 key == value 시켜줌.
                data.put(id, item);
            } else { // 없으면 바로 예외처리
                throw new BadRequestException();
            }
        }
        else { // 수정할 때
            data.put(id, item);
        }

        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data); //  이후 저장.

        } catch (IOException e) {
        }
    }
}
