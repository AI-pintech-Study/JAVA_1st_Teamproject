package org.koreait.global;

import java.util.HashMap;
import java.util.Map;

/**
 * 객체 컨테이너
 *
 * 컨트롤러, 서비스 객체는 싱글턴 패턴으로 컨테이너에서 관리
 * getBean으로 최초 조회시 객체 저장소에 저장하고 두번째 조회시에는 객체 저장소에서 해당 객체를 찾아서 반환한다.
 * 다만 필요에 따라 매번 생성하는 객체도 고려하여 구현
 */
// ## 컨테이너
// -> 컨트롤러 & 서비스처럼 여러개가 필요없는 기능들을 담는 곳 ## //
public class BeanContainer {

    // ## Map = Key & Value 형태 편의 기능 ##
    private final static Map<String, Object> beans; // 객체 컨테이너

    static {
        beans = new HashMap<>();
    }

    /**
     * 객체 조회
     *
     * @param clazz
     * @param isPrototype : true - 매번 새로운 객체 생성, false - 싱글톤 방식
     * @return 조회된 또는 생성된 객체
     * @param <T>
     */

    // ## 어떤 컨트롤러가 올지 모르기떄문에 지네릭 메서드 Class<T> clazz 사용
    // 어떤건지 모르니까 & invoke() 사용 위해 Class 클래스 객체 필요
    // isPrototype(default값 false)이 false면 싱글톤 true면 새로 생성##
    public static <T> T getBean(Class<T> clazz, boolean isPrototype) {

        // ## 중복 막음 ##
        String key = clazz.getName();
        /**
         * 매번 객체를 생성하는 경우(isPrototype - true)는 null로 obj의 초기값을 설정하여 객체를 동적 생성하게 처리하고 싱글톤(isPrototype - false)인 경우라면 객체 컨테이너에서 조회를 한다.
         */
        // ## 한번 생성한 obj는 컨테이너 안에 있으니 null(true)이면 새로 생성
        // false면 beans(컨테이너)안에서 호출 ##
        Object obj = isPrototype ? null : beans.get(key);

        // ## 싱글톤 생성 ##
        if (obj == null) {  // 없다면 동적으로 새로 생성
            try {
                obj = clazz.getDeclaredConstructor().newInstance();

                beans.put(key, obj); // 최초 생성한 객체는 컨테이너에 저장한다.

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        return (T)obj;
    }

    
    /**
     * 객체를 기본 생성자 싱글톤 방식으로 조회
     *
     * @param clazz
     * @return
     * @param <T>
     */
    // ## 메서드 오버로드
    // isPrototype = false니까 싱글톤 ##
    public static <T> T getBean(Class<T> clazz) {
        return getBean(clazz, false);
    }
}
