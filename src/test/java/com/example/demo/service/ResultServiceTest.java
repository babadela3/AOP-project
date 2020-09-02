package com.example.demo.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResultServiceTest {

    @Autowired
    ResultService resultService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testSumMethod() {
        int sum = resultService.sum(4);
        assertEquals(10, sum);

        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("The result of the method is already saved");
        resultService.sum(4);
    }
}
