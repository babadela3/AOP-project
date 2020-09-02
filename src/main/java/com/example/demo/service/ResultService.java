package com.example.demo.service;

import com.example.demo.aspect.Remember;
import com.example.demo.entity.Result;
import com.example.demo.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Remember
    public int sum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        Result result = Result.builder()
                .className(this.getClass().getCanonicalName())
                .methodName(Thread.currentThread().getStackTrace()[1].getMethodName())
                .createDate(LocalDateTime.now())
                .result(String.valueOf(sum))
                .build();
        resultRepository.save(result);

        return sum;
    }

}
