package com.example.demo.service;

import com.example.demo.aspect.MaxDepth;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@NoArgsConstructor
public class NumberUtilsService {

    @Resource
    private NumberUtilsService numberUtilsService;

    @MaxDepth(times = 5)
    public int factorial(int n){
        //Calculates prod = 1 * 2 * 3 * ... * n
        if (n == 1)
            return 1;
        else
            return(n * numberUtilsService.factorial(n-1));
    }

    @MaxDepth(times = 11)
    public int sum(int n) {
        // Calculates sum 1 + 2 + 3 + ... + n
        if (n >= 1) {
            return numberUtilsService.sum(n - 1) + n;
        }
        return n;
    }
}