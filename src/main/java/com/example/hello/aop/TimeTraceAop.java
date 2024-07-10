package com.example.hello.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 이걸 적어줘야 AOP로 사용할 수 있음
@Component // config 에 @Bean에 등록하거나 컴포넌트 스캔 쓰면됌.
public class TimeTraceAop {

//    throws 는 예외를 던지는것이다.
//    메서드의 내부소스코드에서 에러가 발생했을 때, 예외처리를 try-catch문으로 하지않고, throws를 사용하여 메서드를 호출한 쪽으로 책임을 전가하는 행위.
    @Around("execution(* com.example.hello..*(..))") // 적용시킬 패키지를 설정
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString() + " " +  timeMs + "ms");
        }

    }
}
