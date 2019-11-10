package Task2Test;

import Task2.Task2;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Task2UnitTest {
    Task2 task2;

    @Before
    public void init(){
        task2 = new Task2();
    }

    @Test
    public void Test1(){
        Assert.assertArrayEquals(new int[]{1,2,3,2,1},task2.numbersAfterFour(new int[]{1,2,3,4,3,2,1,4,3,2,4,1,2,3,2,1}));
    }

    @Test
    public void Test2(){
        Assert.assertArrayEquals(new int[]{1,2,3,3,2,1,1},task2.numbersAfterFour(new int[]{1,2,34,3,2,3,4,5,4,3,2,1,4,3,4,1,2,3,3,2,1,1}));
    }

    @Test(expected = RuntimeException.class)
    public void Test3(){
        Assert.assertArrayEquals(new int[]{1,2,3,2,1},task2.numbersAfterFour(new int[]{1,2,3,3,2,1,3,2,1,2,3,2,1}));
    }
    @Test
    public void Test4(){
        Assert.assertArrayEquals(new int[0],task2.numbersAfterFour(new int[]{1,2,34,3,2,3,4,5,4,3,2,1,4,3,4,1,2,3,3,2,1,1,4}));
    }


    @After
    public void shutdown(){
        System.out.println("end");
    }
}

