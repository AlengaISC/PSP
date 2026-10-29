import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Parent {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Invalid input. Enter an integer: ");
        }
        int number1 = sc.nextInt();

        System.out.print("Enter the second number: ");
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Invalid input. Enter an integer: ");
        }
        int number2 = sc.nextInt();

        // System.out.println("Numbers entered: " + number1 + ", " + number2);

        ProcessBuilder pb = new ProcessBuilder("java", "Child", String.valueOf(number1), String.valueOf(number2));
        Process child = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(child.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("CHILD: " + line);
        }

        int exitCode = child.waitFor();
        System.out.println("The child process finished");
        System.out.println("Exit code: " + exitCode);

        sc.close();
    }
}
