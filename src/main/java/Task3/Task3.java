package Task3;


public class Task3 {
    public boolean isNumberFourOrOne(int[] intArr){
        for (int i: intArr) {
           if(i == 1 || i==4) return true;
        }
        return false;
    }
}
