package org.koreait.member.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.libs.Utils;
import org.koreait.global.validators.RequiredValidator;
import org.koreait.member.entities.Accession;
import org.koreait.member.service.LoginInfoService;
import org.koreait.member.templates.AccessionList;

import java.util.List;

public class BranchInquiryController extends Controller implements RequiredValidator  { // 여기서 정보 출력함,

    @Override
    public void view() {
        LoginInfoService service = BeanContainer.getBean(LoginInfoService.class); // InfoService 싱글톤패턴 객체 가져오기.
        List<Accession> items = service.getList(true); // getList를 통해 items에다가 List형식으로 다 넣어주기.

        Utils.loadTpl(AccessionList.class, new Model(items)); // List로 쭉 뿌리기.
    }

    protected String getPromptText() {
        return "(메인메뉴: M, 종료: Q, 회원메뉴: W).\n";
    }
};