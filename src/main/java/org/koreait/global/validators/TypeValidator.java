package org.koreait.global.validators;

/**
 * 자료형 체크
 *
 */
public interface TypeValidator {
    // ## 숫자형인지 아닌지 체크 ##
    default boolean isNumber(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}