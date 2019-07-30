package AdditionalTask;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


@RunWith(Parameterized.class)
public class HomeWorkCheckTest {
    @Parameterized.Parameters
    public static Collection<File> data() {
        return getClassFilesFromDir(new File("/java/projects/JavaProfessionalMaven/DZ"));

    }

    private static ArrayList<File> getClassFilesFromDir (File dir) {
        ArrayList<File> filesArrList =new ArrayList<>();
        if(dir.isDirectory()){
            File[] filesArr = dir.listFiles();
            for (File file: filesArr) {
                if(file.getName().endsWith(".class")){
                    filesArrList.add(file);
                }
            }
        }
        printAllFilesName(filesArrList);
        return filesArrList;
    }
    private static void printAllFilesName(ArrayList<File> fileArrayList) {
        Iterator<File> iterator = fileArrayList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getName());
        }
    }

    private File classFile;

    public HomeWorkCheckTest(File classFile) {
        this.classFile = classFile;
    }

    Method calculateInt;
    Method calculateFloat;
    Method checkTwoNumbers;
    Method printIsPositive;
    Method isNegative;
    Method printWelocome;
    Method isLeapYear;
    Class ch;


    ByteArrayOutputStream outputStream;
    PrintStream old;


    @Before
    public void init() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException {
        ch = URLClassLoader.newInstance(new URL[]{classFile.getParentFile().toURL()}).loadClass(getNameWithOutFormat(classFile.getName()));

        calculateInt = ch.getDeclaredMethod("calculate",int.class,int.class, int.class,int.class);
        calculateInt.setAccessible(true);
        calculateFloat = ch.getDeclaredMethod("calculate",float.class,float.class,float.class,float.class);
        calculateFloat.setAccessible(true);
        checkTwoNumbers = ch.getDeclaredMethod("checkTwoNumbers",int.class, int.class);
        checkTwoNumbers.setAccessible(true);
        printIsPositive = ch.getDeclaredMethod("printIsPositive", int.class);
        printIsPositive.setAccessible(true);
        isNegative = ch.getDeclaredMethod("isNegative",int.class);
        isNegative.setAccessible(true);
        printWelocome = ch.getDeclaredMethod("printWelocome",String.class);
        printWelocome.setAccessible(true);
        isLeapYear = ch.getDeclaredMethod("isLeapYear",int.class);
        isLeapYear.setAccessible(true);

        old = System.out;
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));



    }

    private String getNameWithOutFormat(String fileNameWithFormat){
        char[] charArr = fileNameWithFormat.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char symbol: charArr) {
            if(symbol=='.'){
                break;
            }else{
                sb.append(symbol);
            }
        }
        return sb.toString();
    }

    @Test
    public void calculateIntTest() throws InvocationTargetException, IllegalAccessException {
        Assert.assertEquals(44, (int)calculateInt.invoke(ch,4,8,6,2));
    }
    @Test
    public void calculateFloatTest() throws InvocationTargetException, IllegalAccessException {
        Assert.assertEquals(17.25f,(float)calculateFloat.invoke(ch,1.5F,2.5f,4.5f,0.5f),2);
    }
    @Test
    public void checkTwoNumbersTest() throws InvocationTargetException, IllegalAccessException {
        Assert.assertTrue((boolean)checkTwoNumbers.invoke(ch,7,8));
    }
    @Test
    public void printIsPositiveTest() throws InvocationTargetException, IllegalAccessException {
        printIsPositive.invoke(ch,2);
        Assert.assertEquals("Your number is positive!\n",outputStream.toString());
    }
    @Test
    public void isNegativeTest() throws InvocationTargetException, IllegalAccessException {
        Assert.assertTrue((boolean)isNegative.invoke(ch,-2));
    }

    @Test
    public void printWelocomeTest() throws InvocationTargetException, IllegalAccessException {
        printWelocome.invoke(ch,"Владимир");
        Assert.assertEquals("Привет, Владимир!\n",outputStream.toString());
    }
    @Test
    public void isLeapYearTest() throws InvocationTargetException, IllegalAccessException {
        Assert.assertTrue((boolean)isLeapYear.invoke(ch,2012));
    }

    @After
    public void shutdown() {
        System.setOut(old);
    }

}
