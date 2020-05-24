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
public class NumberUtilsServiceTest {

    @Autowired
    NumberUtilsService numberUtilsService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testFactorialMethod() {
        // Basic positive tests and check the result value for factorial method
        int factorial1 = numberUtilsService.factorial(4);
        assertEquals(24, factorial1);

        int factorial2 = numberUtilsService.factorial(2);
        assertEquals(2, factorial2);
    }

    @Test
    public void testSunMethod() {
        // Basic positive tests and check the result value for sum method
        int sum1 = numberUtilsService.sum(10);
        assertEquals(55, sum1);

        int sum2 = numberUtilsService.sum(5);
        assertEquals(15, sum2);
    }

    @Test
    public void testThrowRuntimeExceptionSumMethod() {
        // Expect to throw RuntimeException because the input for sum method is bigger than the limit from MaxDepth
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("The number of recursive calls is too big");
        numberUtilsService.sum(15);
    }

    @Test
    public void testThrowRuntimeExceptionFactorial() {
        // Expect to throw RuntimeException because the input for factorial method is bigger than the limit from MaxDepth
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("The number of recursive calls is too big");
        numberUtilsService.factorial(9);
    }

}
