package AdditionalTask;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
            for (File file: filesArr != null ? filesArr : new File[0]) {
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

    private Class ch;

    private HashMap <String, Method> methodsHashMap;


    private ByteArrayOutputStream outputStream;
    private PrintStream old;


    @Before
    public void init() throws MalformedURLException, ClassNotFoundException, NoSuchMethodException {
        methodsHashMap = new HashMap<>();
        ch = URLClassLoader.newInstance(new URL[]{classFile.getParentFile().toURL()}).loadClass(getNameWithOutFormat(classFile.getName()));

        methodsHashMap.put("calculateInt",ch.getDeclaredMethod("calculate",int.class,int.class, int.class,int.class));
        methodsHashMap.put("calculateFloat",ch.getDeclaredMethod("calculate",float.class,float.class,float.class,float.class));
        methodsHashMap.put("checkTwoNumbers",ch.getDeclaredMethod("checkTwoNumbers",int.class, int.class));
        methodsHashMap.put("printIsPositive",ch.getDeclaredMethod("printIsPositive", int.class));
        methodsHashMap.put("isNegative",ch.getDeclaredMethod("isNegative",int.class));
        methodsHashMap.put("printWelocome",ch.getDeclaredMethod("printWelocome",String.class));
        methodsHashMap.put("isLeapYear",ch.getDeclaredMethod("isLeapYear",int.class));
        methodsHashMap.forEach((k,v)->v.setAccessible(true));

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
        Assert.assertEquals(44, (int)methodsHashMap.get("calculateInt").invoke(ch,4,8,6,2));
    }

    @Test
    public void calculateFloatTest() throws InvocationTargetException, IllegalAccessException {
        Assert.assertEquals(17.25f,(float)methodsHashMap.get("calculateFloat").invoke(ch,1.5F,2.5f,4.5f,0.5f),2);
    }

    @Test
    public void checkTwoNumbersTest() throws InvocationTargetException, IllegalAccessException {
        Assert.assertTrue((boolean)methodsHashMap.get("checkTwoNumbers").invoke(ch,7,8));
    }

    @Test
    public void printIsPositiveTest() throws InvocationTargetException, IllegalAccessException {
        methodsHashMap.get("printIsPositive").invoke(ch,2);
        Assert.assertEquals("Your number is positive!\n",outputStream.toString());
    }

    @Test
    public void isNegativeTest() throws InvocationTargetException, IllegalAccessException {
        Assert.assertTrue((boolean)methodsHashMap.get("isNegative").invoke(ch,-2));
    }

    @Test
    public void printWelocomeTest() throws InvocationTargetException, IllegalAccessException {
        methodsHashMap.get("printWelocome").invoke(ch,"Владимир");
        Assert.assertEquals("Привет, Владимир!\n",outputStream.toString());
    }

    @Test
    public void isLeapYearTest() throws InvocationTargetException, IllegalAccessException {
        Assert.assertTrue((boolean)methodsHashMap.get("isLeapYear").invoke(ch,2012));
    }

    @After
    public void shutdown() {
        System.setOut(old);
    }

}
