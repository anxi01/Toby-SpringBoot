package spring.tobyspringboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.stereotype.Component;

@Retention(RetentionPolicy.RUNTIME) // 어노테이션 생명 주기
@Target(ElementType.TYPE) // 클래스나 인터페이스와 같은 타입에 적용
@Component
public @interface MyComponent {

}
