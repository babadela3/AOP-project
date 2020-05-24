package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;

@Slf4j
@Aspect
@Component
public class RecursiveTrackerAspect {

    // Key is name of method and value is number of calls
    private HashMap<String, Integer> trackingMethods = new HashMap<String, Integer>();

    @Around("@annotation(com.example.demo.aspect.MaxDepth)")
    //Apply this advice any method which is annotated with @MaxDepth
    public Object checkNumberOfRecursions(ProceedingJoinPoint pjp) throws Throwable {
        //Get annotation on the method
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        MaxDepth maxDepth = method.getAnnotation(MaxDepth.class);

        Object object = pjp.proceed();

        // Get the hashmap to check if number of calls exceeds the limit from MaxDepth
        if (trackingMethods.containsKey(pjp.getSignature().toString())) {
            int value = trackingMethods.get(pjp.getSignature().toString());
            // Throw RunTimeException if number of calls exceeds the limit
            if (value > maxDepth.times()) {
                throw new RuntimeException("The number of recursive calls is too big");
            }
            // Update the value from hashmp
            trackingMethods.put(pjp.getSignature().toString(), value + 1);
        }

        return object;
    }

    @Before("@annotation(com.example.demo.aspect.MaxDepth)")
    public void beforeMethod(JoinPoint joinPoint) {
        // Initialise with 0 in hashmap before the execution of method
        if (trackingMethods.containsKey(joinPoint.getSignature().toString())) {
            trackingMethods.remove(joinPoint.getSignature().toString());
        }
        trackingMethods.put(joinPoint.getSignature().toString(), 0);
    }
}
