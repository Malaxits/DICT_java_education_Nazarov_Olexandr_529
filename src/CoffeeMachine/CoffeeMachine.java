package CoffeeMachine;

import java.util.Scanner;

class CoffeeMachine {
    int water = 400;
    int milk = 540;
    int coffee = 120;
    int money = 550;
    int cups = 9;
    String status = "wait";
    int counter = 0;

    void makeCoffee(int takeMoney, int needWater, int needCoffee, int needMilk) {
        if (water < needWater) {
            System.out.println("Sorry, not enough water!");
        } else if (coffee < needCoffee) {
            System.out.println("Sorry, not enough coffee!");
        } else if (milk < needMilk) {
            System.out.println("Sorry, not enough milk!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= needWater;
            coffee -= needCoffee;
            milk -= needMilk;
            cups--;
            money += takeMoney;
        }
    }

    void action(String command) {
        if (command.equals("buy")) {
            status = "make";
        } else if (command.equals("fill")) {
            status = "fill";
            counter = 0;
        } else if (command.equals("take")) {
            System.out.println("I gave you " + money);
            money = 0;
        } else if (command.equals("remaining")) {
            System.out.println("The coffee machine has:");
            System.out.println(water + " of water");
            System.out.println(milk + " of milk");
            System.out.println(coffee + " of coffee");
            System.out.println(cups + " of disposable cups");
            System.out.println(money + " of money");
        } else if (status.equals("make")) {
            int typeOfCoffee;
            try {
                typeOfCoffee = Integer.parseInt(command);
            } catch (NumberFormatException e) {
                typeOfCoffee = 4;
            }
            if (typeOfCoffee == 4) {
                status = "wait";
                return;
            } else if (typeOfCoffee == 1) {
                makeCoffee(4, 250, 16, 0);
            } else if (typeOfCoffee == 2) {
                makeCoffee(7, 350, 20, 75);
            } else if (typeOfCoffee == 3) {
                makeCoffee(6, 200, 10, 100);
            }
            status = "wait";
        } else if (status.equals("fill")) {
            int v = Integer.parseInt(command);
            switch (counter) {
                case 0:
                    water += v;
                    break;
                case 1:
                    coffee += v;
                    break;
                case 2:
                    milk += v;
                    break;
                case 3:
                    cups += v;
                    status = "wait";
                    counter = -1;
                    break;
            }
            counter++;
        } else {
            status = "wait";
        }
    }
}

class CoffeeMachineApp {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Write action (buy, fill, take, remaining, exit):\n>");
            String action = scanner.nextLine();
            coffeeMachine.action(action);

            if (action.equals("buy")) {
                System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, 4 - back to menu:\n>");
                String typeOfCoffee = scanner.nextLine();
                coffeeMachine.action(typeOfCoffee);
            } else if (action.equals("fill")) {
                System.out.print("Write how many ml of water you want to add:\n>");
                String water = scanner.nextLine();
                coffeeMachine.action(water);

                System.out.print("Write how many ml of milk you want to add:\n>");
                String milk = scanner.nextLine();
                coffeeMachine.action(milk);

                System.out.print("Write how many grams of coffee beans you want to add:\n>");
                String coffee = scanner.nextLine();
                coffeeMachine.action(coffee);

                System.out.print("Write how many disposable coffee cups you want to add:\n>");
                String cups = scanner.nextLine();
                coffeeMachine.action(cups);
            } else if (action.equals("exit")) {
                break;
            }
        }
    }
}
