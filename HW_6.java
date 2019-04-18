import java.util.Scanner;

/*      1. Создать классы Собака и Кот с наследованием от класса Животное.
        2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие.
        В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания),
        или высоту (для прыжков).
        3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.;
        плавание: кот не умеет плавать, собака 10 м.).
        4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль.
        (Например, dog1.run(150); -> результат: run: true)
        5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
*/


public class HW_6 {

    public static void main(String[] args) {
        Pet[] pets = {new Cat("Tishka", 0), new Cat("Musik", 200),
                new Dog("Lors", 100), new Dog("Borz", -100)
        };
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter distance for run by Pets");
        int distance = scanner.nextInt();
        System.out.println("Enter distance for swim by Pets");
        int distanceWater = scanner.nextInt();
        System.out.println("Enter height for jump by Pets");
        int high = scanner.nextInt();

        for (Pet pet: pets) {
            System.out.println(pet.getName() + " can running by " + distance + " meters: " + pet.run(distance));
            System.out.println(pet.getName() + " can swimming by " + distanceWater
                    + " meters: " + pet.swim(distanceWater));
            System.out.println(pet.getName() + " can jumping by " + high + " meters: " + pet.jump(high));
        }
    }
}

abstract class Pet {
    private String name;

    Pet (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract boolean run(int distance);
    abstract boolean swim(int distanceWater);
    abstract boolean jump(int high);
}

class Cat extends Pet {
    private String name;
    private int forceRun;

    Cat (String name, int forceRun) {
        super(name);
        this.forceRun = forceRun;
    }

    public boolean run (int distance) {
        return (distance > 0 && distance <= 200 + forceRun);
    }

    public boolean swim (int distanceWater) {
        return false;
    }

    public boolean jump (int high) {
        return (high > 0 && high <= 2);
    }
}

class Dog extends Pet {
    private String name;
    private int forceRun;

    Dog (String name, int forceRun) {
        super(name);
        this.forceRun = forceRun;
    }

    public boolean run (int distance) {
        return (distance > 0 && distance <= 500 + forceRun);
    }

    public boolean swim (int distanceWater) {
        return (distanceWater > 0 && distanceWater <= 10);
    }

    public boolean jump (int high) {
        return (high > 0 && high <= 0.5);
    }
}


/*
    Дополнительное домашнее задание:
    Привести строку к нормальному виду
*/

class MainClassString {
    public static void main(String[] args) {

        String str1 = " Предложение один    Теперь предложение два     Предложение три";
        System.out.println(str1);

        String str2 = str1.replaceAll(" +", " ");
        System.out.println(str2);

        StringBuilder stringBuilder = new StringBuilder();
        boolean isHaveCapitalLetter = false;

        for (int i = 1; i < str2.length(); i++) {
            if (str2.charAt(i) >= 'A' && str2.charAt(i) <= 'Я' && i > 1) {
                stringBuilder.setCharAt(stringBuilder.length() - 1, '.');
                stringBuilder.append(' ');
                isHaveCapitalLetter = true;
            }
            stringBuilder.append(str2.charAt(i));
        }
        if (isHaveCapitalLetter) stringBuilder.append('.');


        System.out.println(stringBuilder.toString());

    }
}

