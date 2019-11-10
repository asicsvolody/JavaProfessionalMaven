package Task3Test;

import Task3.Task3;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Task3UnitTest {
    Task3 task3;

    @Before
    public void init(){
        task3 = new Task3();
    }

    @Test
    public void Test1(){
        Assert.assertTrue( task3.isNumberFourOrOne(new int[]{2,5,6,7,5,4,1,2,3}));
    }

    @Test
    public void Test2(){
        Assert.assertFalse( task3.isNumberFourOrOne(new int[]{2,5,6,7,5,2,3}));
    }

    @Test
    public void Test3(){
        Assert.assertTrue( task3.isNumberFourOrOne(new int[]{2,5,6,7,5,2,3,4}));
    }


    @After
    public void shutdown(){
        System.out.println("Test finish");
    }
}
