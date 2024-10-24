package org.koreait.global;

import org.koreait.global.exceptions.CommonException;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.LoginController;
import org.koreait.main.controllers.MainController;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Router {

    // ## 매번 Scanner 만들 필요 없게 미리 정적으로 생성
    public static final Scanner sc;

    // ## 위 아래 static 모두 가능 ##

    static {
        // ## System.in = 사용자가 입력한 것 ##
        sc = new Scanner(System.in);
    }

    /**
     * 컨트롤러 라우터 실행
     *
     */

    // ## 최초 실행하는 메서드 ##
    public void execute() {

        // ## 무한 반복 while 문 ##
        while(true) {
            try {

                // ## C를 Load할때
                // 상품을 조회하는 기능은 1개면 충분해서 싱글톤##
                // 실행하면 가장 처음에 Main 화면이 처음에 나오도록 load ##
                Utils.loadController(LoginController.class);

            } catch (Exception e) {
                // 예외 공통 출력 처리 S
                int code = 500; // 우리가 정의한 예외가 아니라면 500으로 예외 코드 고정

                // 우리가 정의한 예외라면 그 예외 코드로 교체
                // reflection으로 유입된 경우

                // ## CommonException에 예외 정의
                // 동적 로딩시
                // 실제로 발생한 예외는 getTargetException()에 담긴 것
                // 예외가 발생하게되면 예외를 InvocationTargetException으로 감싸게 됨
                // 그것을 꺼내쓰려면 getTargetException() #
                if (e instanceof InvocationTargetException targetException) {

                    // ## getTargetException에 담긴 예외가 CommonException의 하위클래스인지
                    // 즉 내가 정의한 예외인지 instanceof 연산으로 판단 ##
                   Throwable throwable = targetException.getTargetException();
                   if (throwable instanceof CommonException exception) {
                       code = exception.getCode();
                   }
                }
                // 그외
                if (e instanceof CommonException commonException) {
                    code = commonException.getCode();
                }

                System.out.printf("[%d]%s%n", code, e.getMessage());
                // 예외 공통 출력 처리 E
            }
        }
    }
}
