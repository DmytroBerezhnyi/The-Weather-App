package com.example.android.test1;

import com.example.android.test1.Test.MathChecker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ConverterUtilTest {

    private int inputNumber;
    private Boolean expected;
    private MathChecker mathChecker;

    @Before
    public void setup(){
        mathChecker = new MathChecker();
    }

    public ConverterUtilTest(int inputNumber, Boolean expected) {
        this.inputNumber = inputNumber;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {1, true},
                {2, false},
                {3, true},
                {4, false},
                {5, true}
        });
    }

    @Test
    public void testisOdd() {
        System.out.println("Running test for:" + inputNumber);
        assertEquals(mathChecker.isOdd(inputNumber), expected);
    }
}
