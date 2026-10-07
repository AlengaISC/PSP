import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Child1 {

    public static void main(String[] args) {
        int secret = Integer.parseInt(args[0]);

        Scanner sc = new Scanner(System.in);
        Set<Integer> previousGuesses = new HashSet<>();
        Random random = new Random();
        boolean gameEnded = false;

        // GAME LOOP. Ends when the secret is found or when stdin recieves "END".
        while (!gameEnded) {

            // MAKE A GUESS

            int guess = -1;
            do {
                guess = random.nextInt(101);
            } while (previousGuesses.contains(guess));
            previousGuesses.add(guess);
            System.out.println(guess);

            // RECIEVE A GUESS
            String read = sc.nextLine();
            if (!read.equals("END")) {
                if (Integer.parseInt(read) == secret) {
                    System.out.println("FOUND");
                    gameEnded = true;
                } else {
                    System.out.println("NOPE");
                }
            } else {
                gameEnded = true;
            }

        }

        sc.close();
    }
}
