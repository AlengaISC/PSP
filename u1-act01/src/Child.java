public class Child {
    public static void main(String[] args) throws InterruptedException {

        if (args.length >= 2) {
            int number1 = Integer.parseInt(args[0]);
            int number2 = Integer.parseInt(args[1]);

            int sum = number1 + number2;

            System.out.println(number1 + " + " + number2 + " = " + sum);
        } else {
            System.out.println("Child expects 2 numbers as arguments");
        }
    }
}
