package MainTask;

import MainTask.Annotations.AfterSuite;
import MainTask.Annotations.BeforeSuite;
import MainTask.Annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
//import java.util.Iterator;
import java.util.TreeSet;

public class PerformingTest {

    public static void start(Class obj) {
        try {
            testing(obj);
        } catch (InvocationTargetException | IllegalAccessException |  NoSuchMethodException | InstantiationException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void start(String className) {
        try {
            Class obj = Class.forName(className);
            testing(obj);
        } catch (InvocationTargetException | IllegalAccessException |  NoSuchMethodException | InstantiationException | ClassNotFoundException |RuntimeException e) {
            e.printStackTrace();
        }

    }

    private static void testing(Class obj) throws InvocationTargetException, IllegalAccessException, RuntimeException, NoSuchMethodException, InstantiationException {
        Object classForTest = obj.getConstructor().newInstance();
        Method[] methods = obj.getDeclaredMethods();
        makeBeforeSuite(getBeforeSuiteMethod(methods), classForTest);
        makeTest(getTestMethods(methods), classForTest);
        makeAfterSuiteTest(getAfterSuiteMethod(methods),classForTest);
    }



    private static void makeBeforeSuite(Method method,  Object classForTest) throws InvocationTargetException, IllegalAccessException {
        if(method!= null) {
            method.invoke(classForTest);
        }
    }

    private static void makeTest(Method[] methods,  Object classForTest) throws InvocationTargetException, IllegalAccessException {
        for (Method m: methods) {
            m.invoke(classForTest);
        }
    }

    private static void makeAfterSuiteTest(Method method, Object classForTest) throws InvocationTargetException, IllegalAccessException {
        if(method!= null) {
            method.invoke(classForTest);
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
        TreeSet<Method>  sortedCollection = new TreeSet<>(Comparator.comparing(PerformingTest::getTestPriority));
        for (Method m: methods) {
            if(m.getAnnotation(Test.class)!= null){
                sortedCollection.add(m);
//                printTreeSet(sortedCollection);

            }
        }
        Method[] resMethods = new Method[sortedCollection.size()];
        sortedCollection.toArray(resMethods);
        return resMethods;
    }

    private static int getTestPriority(Method method){
        return -method.getAnnotation(Test.class).priority();
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

//    private static void printTreeSet(TreeSet<Method> treeSet){
//        Iterator <Method> iterator = treeSet.iterator();
//        int i = 1;
//        while (iterator.hasNext()){
//            System.out.println(String.format("%s) %s",i++,iterator.next().getName()));
//
//        }
//    }

    public static void main(String[] args) {

        System.err.println("Метод start(Class obj):");
        start(ClassForTest1.class);

        System.err.println("Метод start(String className):");
        start("MainTask.ClassForTest1");

    }


}
