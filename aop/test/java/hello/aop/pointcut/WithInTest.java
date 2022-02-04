package hello.aop.pointcut;


import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class WithInTest {

    /**
     * Within은 부모타입 사용 불가능함.
     * Within은 패키지 + 클래스만 맵핑함.
     */


    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;


    @BeforeEach
    void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }


    //WithIn은 타입만 살펴본다.

    @Test
    void withinExact() {
        pointcut.setExpression("within(hello.aop.member.MemberServiceImpl)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    }

    @Test
    void withinStar() {
        pointcut.setExpression("within(hello.aop.member.MemberService*)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }


    // 패키지는 생략하더라도, 클래스명은 반드시 놔둬야한다.
    @Test
    void withinSubPackage() {
        pointcut.setExpression("within(hello.aop..MemberServiceImpl)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    // within은 부모 클래스 지정이 안된다.
    @Test
    void withinSuperTypeFalse() {
        pointcut.setExpression("within(hello.aop.member.MemberService)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }








}
