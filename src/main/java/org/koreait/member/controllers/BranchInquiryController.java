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

public class BranchInquiryController extends Controller implements RequiredValidator  {// 유효성 검사를 통해 관리자 권한을 줘야함.
    public BranchInquiryController() {
        LoginInfoService service = BeanContainer.getBean(LoginInfoService.class);
        setInputProcess(input ->{
            if (!check(input)) {
                System.out.println(" 정확한 아이디를 입력하세요.");
                return;
            }

            Accession accession = service.get(input);
            Utils.loadController(BranchInquiryViewController.class, new Model(accession));
        });


    }

    @Override
    protected String getPromptText() {return "조회할 아이디를 입력하세요.";

    }

    @Override
    public void view() {

        LoginInfoService service = BeanContainer.getBean(LoginInfoService.class);
        List<Accession> items = service.getList(true);

        Utils.loadTpl(AccessionList.class, new Model(items));


    }
};

