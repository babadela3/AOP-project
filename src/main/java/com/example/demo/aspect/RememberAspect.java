package com.example.demo.aspect;

import com.example.demo.entity.Result;
import com.example.demo.repository.ResultRepository;
import com.example.demo.service.ResultService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RememberAspect {

    private final ResultRepository resultRepository;

    public RememberAspect(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Before("@annotation(com.example.demo.aspect.Remember)")
    public void beforeMethod(JoinPoint joinPoint) {
        if (resultRepository.findByClassNameAndMethodName(joinPoint.getSourceLocation().getWithinType().getName(), joinPoint.getSignature().getName()).isPresent()) {
            throw new RuntimeException("The result of the method is already saved");
        }
    }
}
