package MainTask;

import MainTask.Annotations.AfterSuite;
import MainTask.Annotations.BeforeSuite;
import MainTask.Annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class PerFormingTest {

    public static void start(Class obj) {
        try {
            testing(obj);
        } catch (InvocationTargetException | IllegalAccessException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void start(String className) {
        Class obj = className.getClass();
        try {
            testing(obj);
        } catch (InvocationTargetException | IllegalAccessException | RuntimeException e) {
            e.printStackTrace();
        }

    }

    private static void testing(Class obj) throws InvocationTargetException, IllegalAccessException,RuntimeException {
        Method[] methods = obj.getDeclaredMethods();
        makeBeforeSuite(getBeforeSuiteMethod(methods),obj);
        makeTest(getTestMethods(methods),obj);
        makeAfterSuiteTest(getAfterSuiteMethod(methods),obj);
    }



    private static void makeBeforeSuite(Method method, Class obj) throws InvocationTargetException, IllegalAccessException {
        if(method!= null) {
            method.invoke(obj);
        }
    }

    private static void makeTest(Method[] methods, Class obj) throws InvocationTargetException, IllegalAccessException {
        for (Method m: methods) {
            m.invoke(obj);
        }
    }

    private static void makeAfterSuiteTest(Method method, Class obj) throws InvocationTargetException, IllegalAccessException {
        if(method!= null) {
            method.invoke(obj);
        }
    }

    private static Method getBeforeSuiteMethod(Method[] methods){
        Method resMethod = null;
        for (Method m: methods) {
            if(m.getAnnotation(BeforeSuite.class)!= null){
                if(resMethod!= null){
                    throw new RuntimeException();
                }
                resMethod = m;
            }
        }
        return resMethod;
    }

    private static Method[] getTestMethods(Method[] methods){
        TreeSet<Method>  sortedCollection = new TreeSet<>(Comparator.comparing(PerFormingTest ::getTestPriority));
        for (Method m: methods) {
            if(m.getAnnotation(Test.class)!= null){
                sortedCollection.add(m);
                printTreeSet(sortedCollection);

            }
        }
        Method[] resMethods = new Method[sortedCollection.size()];
        sortedCollection.toArray(resMethods);
        return resMethods;
    }

    private static int getTestPriority(Method method){
        return method.getAnnotation(Test.class).priority();
    }

    private static Method getAfterSuiteMethod(Method[] methods) throws RuntimeException{
        Method resMethod = null;
        for (Method m: methods) {
            if(m.getAnnotation(AfterSuite.class)!= null){
                if(resMethod!= null){
                    throw new RuntimeException();
                }
                resMethod = m;
            }
        }
        return resMethod;
    }

    private static void printTreeSet(TreeSet<Method> treeSet){
        Iterator <Method> iterator = treeSet.iterator();
        int i = 1;
        while (iterator.hasNext()){
            System.out.println(String.format("%s) %s",i++,iterator.next().getName()));

        }
    }


}
