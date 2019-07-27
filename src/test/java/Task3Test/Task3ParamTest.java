package Task3Test;

import Task3.Task3;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Task3ParamTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {true,new int[]{2,3,4,5,5,2,3,4}},
                {true,new int[]{1,3,1,5,4,2,3,1}},
                {false,new int[]{2,3,2,5,3,2,3,7}},
                {false,new int[]{0,2,8,5,9,3,5,8}},
                {true,new int[]{2,3,4,5,4,2,3,1}}

        });
    }

    private boolean res;
    private int[] intArr;

    public Task3ParamTest(boolean res, int[] intArr) {
        this.res = res;
        this.intArr = intArr;
    }

    Task3 task3;

    @Before
    public void init() {
        task3 = new Task3();
    }

    @Test
    public void Test1() {
        Assert.assertEquals(res,task3.isNumberFourOrOne(intArr));
    }
}
