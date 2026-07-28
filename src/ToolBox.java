import java.util.Scanner;

class Box {
    int value;
    Box(int val) { this.value = val; }
}

public class ToolBox {

    static String greet(String name) {
        return "Hello, " + name + "! Welcome to my Java Toolbox.";
    }

    static double area(double side) {
        return side * side;
    }

    static double area(double length, double width) {
        return length * width;
    }

    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) total += n;
        return total;
    }

    static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println(" (inside swap) a = " + a + ", b = " + b);
    }

    static void addToBox(Box box, int amount) {
        box.value = box.value + amount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== JAVA TOOLBOX =====");
            System.out.println("1 - Greet me\n2 - Area (square or rectangle)\n3 - Sum of numbers");
            System.out.println("4 - Swap demo (pass-by-value)\n5 - Box demo (object mutation)\n0 - Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    System.out.println(greet(name));
                    break;

                case 2:
                    System.out.print("Sides (1 = square, 2 = rectangle): ");
                    int type = scanner.nextInt();
                    if (type == 1) {
                        System.out.print("Enter side length: ");
                        double side = scanner.nextDouble();
                        System.out.println("Area of square = " + area(side));
                    } else if (type == 2) {
                        System.out.print("Enter length: ");
                        double len = scanner.nextDouble();
                        System.out.print("Enter width: ");
                        double wid = scanner.nextDouble();
                        System.out.println("Area of rectangle = " + area(len, wid));
                    }
                    break;

                case 3:

                    System.out.println("Sum of 4, 8, 15 = " + sum(4, 8, 15));
                    System.out.println("Sum of 2, 4, 6, 8, 10 = " + sum(2, 4, 6, 8, 10));
                    break;

                case 4:

                    int x = 5;
                    int y = 9;
                    System.out.println("Before swap: x = " + x + ", y = " + y);
                    swap(x, y);
                    System.out.println("After swap: x = " + x + ", y = " + y + " (unchanged - Java is pass-by-value)");
                    break;

                case 5:

                    Box box = new Box(10);
                    System.out.println("Before: box.value = " + box.value);
                    addToBox(box, 25);
                    System.out.println("After: box.value = " + box.value + " (changed - the object is shared)");
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;
            }
        }
        scanner.close();
    }
}
