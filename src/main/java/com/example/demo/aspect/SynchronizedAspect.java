//package com.example.demo.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.atomic.AtomicBoolean;
//
//@Slf4j
//@Aspect
//@Component
//public class SynchronizedAspect {
//
//    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
//
//    @Around("execution(* java.util..*+.*(..))")
//    public void synchronizeCollectionMethods() throws InterruptedException {
//        while (atomicBoolean.get()) {
//        }
//        atomicBoolean.set(true);
//        log.info("Lock");
//        Thread.sleep(100);
//        log.info("Unlock");
//        atomicBoolean.set(false);
//    }
//}
