import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Parent {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        ProcessBuilder pb = new ProcessBuilder("java", "Child");
        Process child = pb.start();

        PrintWriter writer = new PrintWriter(child.getOutputStream(), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(child.getInputStream()));

        String answer = "";
        int number;

        do {
            if (!answer.equals("")) {
                System.out.print(answer + ". Try again: ");
            } else {
                System.out.print("Guess your PSP grade: ");
            }
            number = sc.nextInt();
            writer.println(number);
            answer = reader.readLine();
        } while (!answer.equals("CORRECT"));

        System.out.print("Found it! Your grade is " + number);
        if (number < 5) {
            System.out.println(". Better luck next time!");
        } else if (number == 5) {
            System.out.print(" Phew, you barely passed!");
        } else if (number > 5 && number != 10) {
            System.out.println(" congratulations!");
        } else if (number == 10) {
            System.out.println(". Perfect!");
        }

        writer.close();
        reader.close();
        sc.close();
        child.waitFor();
    }
}
