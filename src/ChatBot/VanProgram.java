package ChatBot;

import java.util.Scanner;

public class VanProgram {
    public static void main(String[] args) {
        System.out.println("Hello! My name is Van \n I was created in 2023.");
        System.out.println("Please, remind me your names:");

        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("What a great name you have, " + name);

        System.out.println("Let me guess your age? \nEnter remainders of dividing your age by 3, 5 and 7");
        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();
        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.println("Your age is " + age + ", that's a good time to start programming!");

        System.out.println("Now I will prove to you that I can count to any number you want.");
        int a = scanner.nextInt();
        for (int i = 0; i <= a; i++) {
            System.out.println(i + "!");
        }

        System.out.println("Completed, have a nice day!");
        System.out.println("Let's test you.\nWhat is my name? ");
        System.out.println("1. Van! \n2. Billy!");

        while (true) {
            int k = scanner.nextInt();
            if (k == 1) {
                System.out.println("Cool, my name is Van!\nCongratulations, have a nice day!");
                break;
            } else {
                System.out.println("Please try again");
            }
        }
    }
}
