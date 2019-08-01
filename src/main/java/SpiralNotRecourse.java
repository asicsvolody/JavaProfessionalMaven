
class SpiralNotRecourse {
    private int weight;
    private int height;

    private SpiralNotRecourse(int weight, int height) {
        this.weight = weight;
        this.height = height;
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


    private void writeSpiralToArr(int[][] arr){
        int horizontalMax = weight-1;
        int verticalMax = height-1;
        int horizontalMin = 0;
        int verticalMin = 0;
        int number =1;

        while(true) {

            for (int j = horizontalMin; j <= horizontalMax; j++) {
                arr[verticalMin][j] = number++;
            }
            verticalMin++;

            if(number >= weight*height) break;

            for (int i = verticalMin; i <= verticalMax; i++) {
                arr[i][horizontalMax] = number++;
            }
            horizontalMax--;
            if(number >= weight*height) break;


            for (int i = horizontalMax; i >= horizontalMin; i--) {
                arr[verticalMax][i] = number++;
            }
            verticalMax--;
            if(number >= weight*height) break;

            for (int i = verticalMax; i >= verticalMin; i--) {
                arr[i][horizontalMin] = number++;
            }
            horizontalMin++;
            if(number >= weight*height) break;

        }
    }






    public static void main(String[] args) {
        new SpiralNotRecourse(4,7);
        new SpiralNotRecourse(10,15);
        new SpiralNotRecourse(25,30);
    }

}
