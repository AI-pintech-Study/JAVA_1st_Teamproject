package org.koreait.global.libs;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.Router;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.exceptions.CommonException;
import org.koreait.main.controllers.LoginController;
import org.koreait.product.controllers.ProductBranchController;
import org.koreait.member.entities.Accession;
import org.koreait.member.service.LoginInfoService;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class Utils {
    /**
     * 구분선 그리기
     *
     * @param ch : 출력할 문자
     * @param length : 출력할 갯수
     */
    public static void drawLine(char ch, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }

    // ## default 값은 10개로 오버로드
    public static void drawLine(char ch) {
        drawLine(ch, 10);
    }

    /**
     * 템플릿 출력
     *
     * Class 클래스는 클래스의 구성 요소의 정보도 조회하나 
     *  reflection 기능, 즉 동적 객체 생성, 동적 메서드 호출, 또는 동적으로 멤버 변수의 값 접근 및 변경 가능합니다.
     *  reflection 기능은 불특정 클래스로 부터 객체를 생성하거나 메서드를 호출할때 즉, 범용적인 접근을 할때 주로 사용할 수 있습니다.
     *
     * @param clazz
     * @param <T>
     * @param model 전송 데이터
     */

    // ## model이 있나 없나가 굉장히 중요 ##
    public static <T> T loadTpl(Class<T> clazz, Model model) {
        try {

            // 동적 객체 생성
            // ## 싱글톤 (getBean) ##
            Object obj = BeanContainer.getBean(clazz);
            if (model == null) {
                Method method = clazz.getDeclaredMethod("print");
                method.invoke(obj);
            } else { // 템플릿에 전달할 데이터가 있는 경우
                Method method = clazz.getDeclaredMethod("print", Model.class);
                method.invoke(obj, model);
            }

            return (T)obj;
        } catch (Exception e) {
            // ## 감싸진 예외 꺼내오기(get)
           if (e instanceof InvocationTargetException targetException) {
               Throwable throwable = targetException.getTargetException();
               if (throwable instanceof CommonException commonException) {
                   throw commonException;
               }
           }

           e.printStackTrace();
        }

        return null;
    }

    public static <T> T loadTpl(Class<T> clazz) {
        return loadTpl(clazz, null);
    }

    /**
     * 컨트롤러 실행
     *
     * 메뉴 컨트롤러는 여러개로 정의될 수 있으므로 Controller라는 클래스 공통 자료형을 통해 하나로 묶어 줄수 있음(다형성 활용)
     *
     * @param clazz
     * @return
     * @param <T>
     * @param model : 전송할 데이터
     */

    // ## loadController (무슨 컨트롤러가 올지 모르니까 지네릭 메서드) ##
    public static <T> T loadController(Class<T> clazz, Model model) {

        /**
         * 컨트롤러는 공통적으로 run 이라는 메서드가 정의되어 있고 run은 일련의 실행 절차가 정의되어 있다.
         */
        try {

            // ## 싱글톤 (getBean
            Object obj = BeanContainer.getBean(clazz);

            // Controller인 경우만 처리
            // ## Controller만 처리하도록 instanceof
            if (obj instanceof Controller controller) {


                if (model != null) { // 전송 데이터 처리
                    controller.setData(model.getData());
                }

                // ## 상위 클래스에 있는 run 메서드를 찾아서
                Method method = clazz.getSuperclass().getDeclaredMethod("run");
                // 실행(invoke) ##
                method.invoke(obj);

                // ## 반환 값으로 편의상 명시적 형변환
                // Object해도 되지만 이게 나음
                // 예를들어 Listcontroller면
                // return Listcontroller controller ##

                return (T) controller;
            }
        } catch (Exception e) {
            if (e instanceof InvocationTargetException targetException) {
                // ## 꺼낸 예외가 내가 정의한(Common)이면 throw
                Throwable throwable = targetException.getTargetException();
                if (throwable instanceof CommonException commonException) {
                    throw commonException;
                }
            }

            e.printStackTrace();
        }

        return null;
    }

    public static <T> T loadController(Class<T> clazz) {
        return loadController(clazz, null);
    }

    /**
     * 텍스트 입력 처리
     *
     * @param title : 안내 문구
     * @param message : 검증 실패시 안내 문구
     * @return
     */

    // ## 검증 실패시 다시 입력하라고 무한 반복 while(true) ##
    // (매개변수들) 그때그때 상황맞게 쓸 수 있게 String title, String message
    public static String getString(String title, String message, List<Predicate<String>> conditions) {
        Scanner sc = Router.sc;
        String input;
        while(true) {
            try {
                boolean isPass = true;
                System.out.print(title + ": ");
                input = sc.nextLine();
                if (commonInputProcess(input, message)) {
                    // ## 검증 성공시 break 후 return input##
                    isPass = false;
                }

               // 추가 조건 처리 S
                if (conditions != null) {
                    for (Predicate<String> predicate : conditions) {
                        if (!predicate.test(input)) {
                            isPass = false;
                        }
                    }
                }
                // 추가 조건 처리 E
                if (isPass) {
                    break;
                }

            } catch (CommonException e) {
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    public static String getString(String title, String message) {
        return getString(title, message, null);
    }

    /**
     * 숫자 입력 처리
     *
     * 1) 필수 여부 체크
     * 2) 숫자 형식 체크
     * 
     * @param title
     * @param message
     * @return
     */
    public static long getNumber(String title, String message, List<Predicate<String>> conditions) {

        Scanner sc = Router.sc;
        String checkBitrh = "0";
        while (true) {
            try {
                boolean isPass = true;
                System.out.print(title + ": ");
                String input = sc.nextLine();
                if (commonInputProcess(input, message)) { // 공통 입력 처리
                    isPass = false;
                }
                if (conditions != null) {
                    for (Predicate<String> predicate : conditions) {
                        if (!predicate.test(input)) {
                            isPass = false;
                        }
                    }
                }
                // 추가 조건 처리 E
                if (isPass) {
                    checkBitrh = input;
                    break;
                }
                else {
                    continue;
                }
            } catch (Exception e) {

                // ## 내가 정한 예외면 정해둔 메시지 출력 ##
                if (e instanceof CommonException) {
                    System.out.println(e.getMessage());
                    // ## 아닐 경우 숫자로 입력하라고 메시지 출력 ##
                } else {
                    System.out.println("숫자 형식으로 입력하세요.");
                }
            }

            return 0;
        }
        return Long.parseLong(checkBitrh);
    }

    public static long getNumber(String title, String message) {
        return getNumber(title, message, null);
    }

    /**
     * 공통 입력 처리
     *
     * @param input
     * @return boolean : true인 경우 반복 중단
     */
    private static boolean commonInputProcess(String input, String message) {

        // ## 필수성 체크
        // 값이 있나 없나 체크 후 없으면, null이면 다시 ##
        if (input == null || input.isBlank()) {
            throw new BadRequestException(message);
        }

        // 입력 문구가 대소문자 구분없이 M인 경우 메인 메뉴로 이동
        if (input.trim().toUpperCase().equals("M") || input.equals("ㅡ")) {
            Accession acc = BeanContainer.getBean(Accession.class);
            if (acc.isLoginCheck()) Utils.loadController(ProductBranchController.class);
            else Utils.loadController(LoginController.class);
            return true;
        }


        // 입력 문구가 대소문자 구분없이 Q인 경우 프로그램 종료
        if (input.trim().toUpperCase().equals("Q")|| input.equals("ㅂ")) {
            System.out.println("종료합니다.");
            System.exit(1);
        }

        if (input.toUpperCase().equals("O") || input.equals("ㅐ")) {
            Accession acc = BeanContainer.getBean(Accession.class);
            acc.setLoginCheck(false);
            Utils.loadController(LoginController.class);
            return true;
        }

        return false;
    }

    public static boolean getIdCheck(String id, String password)
    {
        LoginInfoService loginInfoService = BeanContainer.getBean(LoginInfoService.class);
        Accession checkItem = loginInfoService.get(id); // id가 있으면 그 객체 불러옴.
        Accession checkLogin = BeanContainer.getBean(Accession.class); // 객체 복사를 위해 싱글톤패턴으로 생성

        if (checkItem != null && checkItem.getUserPassword().equals(password)) {
        checkLogin.copyFrom(checkItem); // ID가 맞다면 이 안에 객체 데이터 복사.
        checkLogin.setLoginCheck(true); // Login 됬다고 알림
            return true;
        }

        return false;
    }

    /**
     * 상품 정보 목록 파일에서 로드
     *
     * @return
     */
    public static Map<String, Accession> load() {
        File file = new File("Accession.obj");
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream oos = new ObjectInputStream(fis)) {
                // ## 상품 목록을 Map형태로 가져옴
                Map<String, Accession> data = (Map<String, Accession>) oos.readObject();
                return data;
            } catch (Exception e) {}
        }

        return new HashMap<>();
    }
}
