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

        LoginInfoService service = BeanContainer.getBean(LoginInfoService.class);
        List<Accession> items = service.getList(true);

        Utils.loadTpl(AccessionList.class, new Model(items));
    }
};