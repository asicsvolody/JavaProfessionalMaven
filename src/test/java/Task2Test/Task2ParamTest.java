package Task2Test;

import Task2.Task2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Task2ParamTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {new int[]{5,6,8,3,2,8,9},new int[]{1,2,4,3,2,5,6,5,3,4,4,4,4,4,5,6,8,3,2,8,9}},
                {new int[]{5,6,8,3,8,9},new int[]{1,2,4,5,6,5,3,4,4,4,4,5,6,8,3,8,9}},
                {new int[]{5,6,3,2,8,9},new int[]{1,2,4,3,2,5,6,5,3,4,4,4,4,4,5,6,3,2,8,9}},
                {new int[]{5,6,8,3,2,9},new int[]{1,2,4,3,5,7,5,3,4,4,4,5,6,8,3,2,9}}
        });
    }

    private int[] outArr;
    private int[] inArr;

    public Task2ParamTest(int[] outArr, int[] inArr) {
        this.outArr = outArr;
        this.inArr = inArr;
    }

    Task2 task2;

    @Before
    public void init(){
        task2 = new Task2();
    }

    @Test
    public void massTestNumbersAfterFour(){
        Assert.assertArrayEquals(outArr,task2.numbersAfterFour(inArr));
    }



}
