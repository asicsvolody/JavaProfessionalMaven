package MainTask;

import MainTask.Annotations.AfterSuite;
import MainTask.Annotations.BeforeSuite;
import MainTask.Annotations.Test;

public class ClassForTest1 {

    @BeforeSuite
    public void init(){
        System.out.println("BeforeSuite");
    }

    @Test(priority = 1)
    public void Test1(){
        System.out.println("Test priority- 1");

    }
    @Test(priority = 9)
    public void Test9(){
        System.out.println("Test priority- 9");

    }
    @Test(priority = 6)
    public void Test6(){
        System.out.println("Test priority- 6");

    }
    @Test(priority = 10)
    public void Test10(){
        System.out.println("Test priority- 10");
    }
    @Test
    public void Test5(){
        System.out.println("Test priority- 5");
    }
    @Test(priority = 7)
    public void Test7(){
        System.out.println("Test priority- 7");
    }
    @Test(priority = 3)
    public void Test3(){
        System.out.println("Test priority- 3");
    }
    @Test(priority = 2)
    public void Test2(){
        System.out.println("Test priority- 2");
    }
    @Test(priority = 4)
    public void Test4(){
        System.out.println("Test priority- 4");
    }
    @Test(priority = 8)
    public void Test8(){
        System.out.println("Test priority- 8");
    }

    @AfterSuite
    public void shutdown(){
        System.out.println("AfterSuite");
    }
}
