import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;


class ThirdHW {

    public static void main(String[] args) {
        System.out.println("Choose a game: 1 - guess the number, 2 - guess the word");
        Scanner enter = new Scanner(System.in);
        int p = enter.nextInt();
        if (p == 1) GuessTheNumber();
        if (p == 2) GuessTheWord();
    }


    /*  1. Написать программу, которая загадывает случайное число от 0 до 9,
        и пользователю дается 3 попытки угадать это число.
        При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
        После победы или проигрыша выводится запрос –
        «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).*/

    public static void GuessTheNumber(){
        Scanner in = new Scanner(System.in);

        int a, n = (int)(Math.random()*10);
        System.out.println("The computer made a number from 0 to 9, guess this number from 3 attempts.");
        for (int i = 0; i < 3; i++){
            System.out.println("Attempt " + (i+1) + ": ");
            a = in.nextInt();
            if (a < n) System.out.println("The number is bigger!");
            if (a > n) System.out.println("The number is lower!");
            if (a == n) {System.out.println("You are right!");
                System.out.println("The game repeat again? 1 - yes / 0 - no");
                a = in.nextInt();
                if (a == 1) GuessTheNumber();
                else {System.out.println("Thanks for playing!");
                    System.exit(0);
                }
            }
        }
        System.out.println("You are wrong :(");
        System.out.println("The game repeat again? 1 - yes / 0 - no");
        a = in.nextInt();
        if (a == 1) GuessTheNumber();
        else {System.out.println("Thanks for playing!");
        }
    }

    /* 2/Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado",
    "broccoli", "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
    "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
    сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
    Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
    apple – загаданное
    apricot - ответ игрока
    ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
    Для сравнения двух слов посимвольно, можно пользоваться:
    String str = "apple"; str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
    Играем до тех пор, пока игрок не отгадает слово
    Используем только маленькие буквы */

    public static void GuessTheWord(){
        String[] words = {"apple", "orange", "lemon", "banana",
                "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango",
                "mushroom", "nut", "olive", "pea", "peanut", "pear",
                "pepper", "pineapple", "pumpkin", "potato"};

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String word;
        int n = random.nextInt(words.length);
        System.out.println(words[n]); //для упрощения
        System.out.println("The computer made a word from the list " + Arrays.toString(words));
        System.out.println("try to guess it");
        char[] chs = words[n].toCharArray();
        char[] chCmp = new char[15];
        for (int i = 0; i < 15; i++) {
            chCmp[i] = '#';
        }

        while (true) {
            System.out.println("Enter the word: ");
            word = scanner.nextLine();
            if (word.equals(words[n])) {
                System.out.println("You are right!");
                break;
            } else {
                System.out.println("You are wrong :(");
                char[] chWord = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    if (i >= chWord.length) {
                        break;
                    }
                    if (chs[i] == chWord[i]) {
                        chCmp[i] = chs[i];
                    }
                } System.out.println(String.valueOf(chCmp));
            }
        } System.out.println("Thanks for playing!");

    }
}