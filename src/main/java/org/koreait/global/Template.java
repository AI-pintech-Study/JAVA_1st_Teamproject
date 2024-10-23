package org.koreait.global;

import java.util.Map;

/**
 * 출력 화면 Template 기본 구성
 *
 */

// ## 상속 받아 쓸 print() 추상 메서드
public interface Template {

    // ## 모델이 있을경우 print() 호출됨 ##
    void print();
    
    // ## 있어도 되고 없어도 되는 옵션
    // Template에 넘길 값(model)이 있을때 호출됨 ##
    default void print(Model model) {}
}