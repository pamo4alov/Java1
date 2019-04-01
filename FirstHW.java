public class FirstHW {

//    1. Создать пустой проект в IntelliJ IDEA и прописать метод main().
    public static void main(String[] args) {
        itemTwo();
        System.out.println("a * (b + (c / d)) = " + itemThree(5,7,10,2));
        System.out.println(itemFour(9, 7));
        itemFive(-3);
        System.out.println(itemSix(-2));
        itemSeven("Eric");
        itemEight(2024);
    }
//    2. Создать переменные всех пройденных типов данных и инициализировать их значения.
    public static void itemTwo() {
        System.out.println("Task 2.");
        byte a = 35;
        short b = 11359;
        int c = 3500000;
        long d = 395885499L;
        char e = 'm';
        float x = 50.76f;
        double y = 900.0987;
        boolean bo = true;
        System.out.println("Value for byte = " + a);
        System.out.println("Value for short = " + b);
        System.out.println("Value for int = " + c);
        System.out.println("Value for long = " + d);
        System.out.println("Value for char = " + e);
        System.out.println("Value for float = " + x);
        System.out.println("Value for double = " + y);
        System.out.println("Value for boolean = " + bo);
    }

//    3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
//    где a, b, c, d – входные параметры этого метода.

    public static int itemThree(int a, int b, int c, int d) {
        System.out.println("Task 3.");
        return a * (b + (c / d));
    }

//    4. Написать метод, принимающий на вход два числа и проверяющий, что их сумма лежит в
//    пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false.

    public static boolean itemFour(int x1, int x2) {
        System.out.println("Task 4.");
        int sum = x1 + x2;
        if (sum > 10 && sum < 20) {
            return true;
        } else {
            return false;
        }
    }

//    5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
//    положительное ли число передали или отрицательное. ​
//    Замечание: ноль считаем положительным числом.

    public static void itemFive(int x) {
        System.out.println("Task 5.");
        if (x >= 0) {
            System.out.println("Number is positive " + x);
        } else {
            System.out.println("Number is negative " + x);
        }
    }

//    6. Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true, если число отрицательное.

    public static boolean itemSix(int x) {
        System.out.println("Task 6.");
        if (x < 0) {
            return true;
        }
        return false;
    }

//    7. Написать метод, которому в качестве параметра передается строка, обозначающая имя.
//    Метод должен вывести в консоль сообщение «Привет, указанное_имя!».

    public static void itemSeven(String name) {
        System.out.println("Task 7.");
        System.out.println("Hello, " + name + "!");
    }

//    8. *Написать метод, который определяет, является ли год високосным, и выводит сообщение в консоль.
//    Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.

    public static void itemEight(int year) {
        System.out.println("Task 8.");
        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
            System.out.println("The year is a leap-year!");
        } else {
            System.out.println("The year is not a leap-year!");
        }
    }
}
