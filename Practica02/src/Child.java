import java.util.Random;
import java.util.Scanner;

public class Child {

    public static void main(String[] args) {
        // Generate the random grade (0-10)
        int grade = new Random().nextInt(11);
        // grade = 5;

        Scanner sc = new Scanner(System.in);

        boolean bingo = false;
        while (!bingo) {
            int number = sc.nextInt();
            if (number == grade) {
                System.out.println("CORRECT");
                bingo = true;
            } else if (number < grade) {
                System.out.println("BIGGER");
            } else if (number > grade) {
                System.out.println("SMALLER");
            }
        }

        sc.close();
    }
}
