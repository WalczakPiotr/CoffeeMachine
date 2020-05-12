package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public int amountWater = 400, amountMilk = 540, amountBeans = 120, amountCups = 9, amountMoney = 550;
    CoffeMachineCurrenState currentState = CoffeMachineCurrenState.CHOOSING_AN_ACTION;
    CoffeType coffeeType;

    public CoffeMachineCurrenState getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(CoffeMachineCurrenState coffeMachineCurrenState) {
        this.currentState = coffeMachineCurrenState;
    }

    public void whatToDo(String sample) {
        System.out.println("");
        if (this.getCurrentState() == CoffeMachineCurrenState.CHOOSING_AN_ACTION) {
            switch (sample) {
                case "buy":
                    this.currentState = CoffeMachineCurrenState.CHOOSING_A_VARIANT_OF_COFFEE;
                    break;
                case "fill":
                    this.currentState = CoffeMachineCurrenState.FILLING;
                    break;
                case "take":
                    this.currentState = CoffeMachineCurrenState.TAKING;
                    break;
                case "remaining":
                    this.currentState = CoffeMachineCurrenState.REMAINING;
                    break;
                case "exit":
                    this.currentState = CoffeMachineCurrenState.EXIT;
                    break;
            }
        } else if (this.getCurrentState() == CoffeMachineCurrenState.CHOOSING_A_VARIANT_OF_COFFEE) {
            switch (sample) {
                case "1":
                    this.currentState = CoffeMachineCurrenState.MAKEING_COFFEE;
                    this.coffeeType = CoffeType.ESPRESSO;
                    this.checkResources();
                    break;
                case "2":
                    this.currentState = CoffeMachineCurrenState.MAKEING_COFFEE;
                    this.coffeeType = CoffeType.LATTE;
                    this.checkResources();
                    break;
                case "3":
                    this.currentState = CoffeMachineCurrenState.MAKEING_COFFEE;
                    this.coffeeType = CoffeType.CAPPUCCINO;
                    this.checkResources();
                    break;
                case "back":
                    this.currentState = CoffeMachineCurrenState.CHOOSING_AN_ACTION;
                    break;
            }
        }
    }

    public void checkResources() {
        if (this.amountWater < this.coffeeType.amountWater) {
            System.out.println("Sorry, not enough water!\n");
            this.currentState = CoffeMachineCurrenState.CHOOSING_AN_ACTION;
        } else if (this.amountMilk < this.coffeeType.amountMilk) {
            System.out.println("Sorry, not enough milk!\n");
            this.currentState = CoffeMachineCurrenState.CHOOSING_AN_ACTION;
        } else if (this.amountBeans < this.coffeeType.amountBeans) {
            System.out.println("Sorry, not enough coffee beans!\n");
            this.currentState = CoffeMachineCurrenState.CHOOSING_AN_ACTION;
        } else if (this.amountCups < this.coffeeType.amountCups) {
            System.out.println("Sorry, not enough disposable cups of coffee!\n");
            this.currentState = CoffeMachineCurrenState.CHOOSING_AN_ACTION;
        } else {
            System.out.println("I have enough resources, making you a coffee!\n");
            this.makeCoffee();
        }
    }

    public void makeCoffee(){
        this.amountWater -= this.coffeeType.amountWater;
        this.amountMilk -=  this.coffeeType.amountMilk;
        this.amountBeans -= this.coffeeType.amountBeans;
        this.amountCups -= this.coffeeType.amountCups;
        this.amountMoney += this.coffeeType.amountMoney;
        this.currentState = CoffeMachineCurrenState.CHOOSING_AN_ACTION;
    }

    public void writeRemaining() {
        System.out.println("The coffee machine has:");
        System.out.println(this.amountWater + " of water");
        System.out.println(this.amountMilk + " of milk");
        System.out.println(this.amountBeans + " of coffee beans");
        System.out.println(this.amountCups + " of disposable cups");
        System.out.println("$" + this.amountMoney + " of money\n");
        this.setCurrentState(CoffeMachineCurrenState.CHOOSING_AN_ACTION);
    }

    public void takeMoney() {
        if (this.amountMoney == 0) {
            System.out.println("Coffee Machine is out of money!\n");
        } else {
            System.out.println("I gave you $" + this.amountMoney + "\n");
            this.amountMoney = 0;
        }
        this.setCurrentState(CoffeMachineCurrenState.CHOOSING_AN_ACTION);
    }

    public void fill(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        this.amountWater += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        this.amountMilk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        this.amountBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        this.amountCups += scanner.nextInt();
        System.out.println("");
        this.setCurrentState(CoffeMachineCurrenState.CHOOSING_AN_ACTION);
    }

    public enum CoffeMachineCurrenState {
        CHOOSING_AN_ACTION,
        CHOOSING_A_VARIANT_OF_COFFEE,
        MAKEING_COFFEE,
        FILLING,
        TAKING,
        REMAINING,
        EXIT;
    }

    public enum CoffeType {
        ESPRESSO(250, 0, 16, 1, 4),
        LATTE(350, 75, 20, 1, 7),
        CAPPUCCINO(200, 100, 12, 1, 6);

        int amountWater, amountMilk, amountBeans, amountCups, amountMoney;

        CoffeType(int amountWater, int amountMilk, int amountBeans, int amountCups, int amountMoney) {
            this.amountWater = amountWater;
            this.amountMilk = amountMilk;
            this.amountBeans = amountBeans;
            this.amountCups = amountCups;
            this.amountMoney = amountMoney;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while(coffeeMachine.getCurrentState() != CoffeMachineCurrenState.EXIT) {
            switch (coffeeMachine.getCurrentState()) {
                case CHOOSING_AN_ACTION:
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                    coffeeMachine.whatToDo(scanner.next());
                    break;
                case CHOOSING_A_VARIANT_OF_COFFEE:
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    coffeeMachine.whatToDo(scanner.next());
                    break;
                case FILLING:
                    coffeeMachine.fill();
                    break;
                case TAKING:
                    coffeeMachine.takeMoney();
                    break;
                case REMAINING:
                    coffeeMachine.writeRemaining();
                    break;
                case EXIT:
                    break;
            }
        };
    }
}
