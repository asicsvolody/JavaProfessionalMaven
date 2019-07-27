package Task2;

import java.util.ArrayList;

public class Task2 {


    public int[] numbersAfterFour(int[] intArr) throws RuntimeException{
        ArrayList<Integer> arrayList = new ArrayList<>();
        boolean isThereFour = false;
        for (int i : intArr) {
            arrayList.add(i);
            if(i == 4){
                arrayList.clear();
                isThereFour = true;
            }
        }
        if(!isThereFour){
            throw new RuntimeException("Not found 4");
        }
        return arrayListToInt(arrayList);
    }

    private static int[] arrayListToInt(ArrayList<Integer> integerArrList){
        int[] resInt = new int[integerArrList.size()];
        for (int i = 0; i <integerArrList.size(); i++) {
            resInt[i] = integerArrList.get(i);
        }
        return resInt;
    }
}
