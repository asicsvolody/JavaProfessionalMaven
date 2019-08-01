public class SpiralWithRecourse {
    private int weight;
    private int height;
    private int horizontalMax;
    private int verticalMax;
    private int horizontalMin;
    private int verticalMin;
    private int number;

    private SpiralWithRecourse(int weight, int height) {
        this.weight = weight;
        this.height = height;
        horizontalMax = weight-1;
        verticalMax = height-1;
        horizontalMin = 0;
        verticalMin = 0;
        int[][] arr = new int[height][weight];
        writeSpiralToArr(arr);
        printArr(arr);

    }

    private void printArr(int[][] arr){

        for (int [] arrInside: arr) {
            for (int number :arrInside) {
                System.out.print(number+"\t");
            }
            System.out.println();
        }
    }


    private void  writeSpiralToArr(int[][] arr){
        for (int j = horizontalMin; j <= horizontalMax; j++) {
            arr[verticalMin][j] = number++;
        }
        verticalMin++;

        if(number >= weight*height) return;

        for (int i = verticalMin; i <= verticalMax; i++) {
            arr[i][horizontalMax] = number++;
        }
        horizontalMax--;
        if(number >= weight*height) return;


        for (int i = horizontalMax; i >= horizontalMin; i--) {
            arr[verticalMax][i] = number++;
        }
        verticalMax--;
        if(number >= weight*height) return;

        for (int i = verticalMax; i >= verticalMin; i--) {
            arr[i][horizontalMin] = number++;
        }
        horizontalMin++;
        if(number >= weight*height) return;

        writeSpiralToArr(arr);
    }




    public static void main(String[] args) {
        new SpiralWithRecourse(7,4);

    }
}
