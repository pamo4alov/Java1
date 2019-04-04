public class SecondHW {


    public static void main(String[] args){
        itemOne1();
        itemOne2();
        itemTwo1();
        itemTwo2();
        itemThree();
        itemFour();
        itemFive();
        System.out.println("\nTask 6.:");
        System.out.println(itemSix(new int[]{2, 2, 2, 1, 2, 2, 10, 1}));
        System.out.println(itemSix(new int[]{1, 1, 1, 2, 1}));
        System.out.println("\nTask 7.:");
        itemSeven(new int[] {1, 5, 10, 15, 20, 25, 30, 35, 40}, -2);


    }
//    1. Задать целочисленный массив, состоящий из элементов 0 и 1.
//    Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;

//        Method 1. Used ternary operation

    public static void itemOne1() {
        System.out.println("\nTask - 1.1");
        int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = arr1[i] == 1 ? 0 : 1;
            System.out.print(arr1[i] + " ");
        }
    }

//        Method 2. Used If ... else

    public static void itemOne2() {
        System.out.println("\n\nTask - 1.2");
        int[] arr2 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr2.length; i++) {
            if (arr2[i] == 1) {
                arr2[i] = 0;
            } else arr2[i] = 1;
        }
        for (int i : arr2) {
            System.out.print(i + " ");
        }

    }

//    2. Задать пустой целочисленный массив размером 8.
//    С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;

    public static void itemTwo1() {
        System.out.println("\n\nTask 2.1:");
        int[] arr3 = new int[8];
        for (int i = 0, j = 0; i < arr3.length; i++, j += 3) {
            arr3[i] = j;
            System.out.print(arr3[i] + " ");
        }
    }
    public static void itemTwo2() {
        System.out.println("\n\nTask 2.2:");
        int[] arr4 = new int[8];
        for (int i = 0; i < arr4.length; i++){
            arr4[i] = i*3;
            System.out.print(arr4[i] + " ");
        }
    }

//3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;

    public static void itemThree() {
        System.out.println("\n\nTask 3.:");
        int[] arr5 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr5.length; i++) {
            if (arr5[i] < 6) arr5[i] *= 2;
            System.out.print(arr5[i] + " ");
        }
    }

//    4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
//    и с помощью цикла(-ов) заполнить его диагональные элементы единицами;

    public static void itemFour() {
        System.out.println("\n\nTask 4.:");
        int[][] arr6 = new int[4][4];
        for (int i = 0; i < arr6.length; i++) {
            for (int j = 0; j < arr6.length; j++) {
                if ((i == j) || (i == arr6.length - 1 - j)) {
                    arr6[i][j] = 1;
                }
                System.out.print(arr6[i][j] + " ");
            }
            System.out.println();
        }
    }

//   5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);

    public static void itemFive() {
        System.out.println("\nTask 5.:");
        int[] arr7 = {1, 5, -3, 2, 11, 4, -5, 2, 4, -8, 9, -1};
        int max = arr7[0], min = arr7[0];
        int maxInd = 0, minInd = 0;
        for (int i = 0; i < arr7.length; i++) {
            if (arr7[i] > max) {
                max = arr7[i];
                maxInd = i;
            } else {
            if (arr7[i] < min)
                min = arr7[i];
                minInd = i;

            }
        }
        System.out.println("The maximum height is: " + max);
        System.out.println("The minimum height is: " + min);
    }


//    6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
//    метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
//    Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
//    граница показана символами ||, эти символы в массив не входят.

    public static boolean itemSix(int[] arr8) {
        int left = 0, right = 0;
        for (int i = 0; i < arr8.length; i++)
            right += arr8[i];
        for (int i = 0; i < arr8.length; i++)
        {
            left += arr8[i];
            right -= arr8[i];
            if (left == right) return true;
        }
        return false;
    }

//    7. **** Написать метод, которому на вход подается одномерный массив и
//    число n (может быть положительным, или отрицательным),
//    при этом метод должен сместить все элементы массива на n позиций.
//    Для усложнения задачи нельзя пользоваться вспомогательными массивами.

    static void itemSeven(int[] arr9, int n) {
        System.out.print("Before: ");
        for (int i : arr9) {
            System.out.print(i + " ");
        }

        System.out.print("(n = " + n + ")");

        if (n > 0) {
            for (int i = 0; i < n; i++) {

                int delta = arr9[arr9.length - 1];
                for (int j = arr9.length - 1; j > 0; j--) {
                    arr9[j] = arr9[j - 1];
                }
                arr9[0] = delta;
            }
        } else if (n < 0) {
            for (int i = 0; i < n * (-1); i++) {

                int delta = arr9[0];
                for (int j = 0; j < arr9.length - 1; j++) {
                    arr9[j] = arr9[j + 1];
                }
                arr9[arr9.length - 1] = delta;
            }
        }

        System.out.print("\nAfter:  ");
        for (int i : arr9) {
            System.out.print(i + " ");
        }
    }
}



