package com.centimeapp.database.annotations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Log4j2
@Component
public class LogMethodParameter {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Before("@annotation(LogMethodParam)")
    public void logMethodParam(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Class<?>[] argTypes = Arrays.stream(args)
                .map(Object::getClass)
                .toArray(Class<?>[]::new);
        try {
            Method method = joinPoint.getTarget().getClass()
                    .getMethod(methodName, argTypes);
            log.info("Method {} called with parameters: {}", methodName, Arrays.toString(args));
        } catch (Exception e) {
            log.error("Method {} not found", methodName);
        }
    }

    @AfterReturning(pointcut = "@annotation(LogMethodParam)", returning = "result")
    public void logMethodResult(Object result) {
        log.info("Method returned: {}", result);
    }

}
