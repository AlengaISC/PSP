import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Parent {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        clear();
        System.out.print("Introduce the first secret: ");
        int secret1 = sc.nextInt();
        System.out.print("Introduce the second secret: ");
        int secret2 = sc.nextInt();

        boolean goesFirst = new Random().nextBoolean();

        // Process of the first child with the first secret sent as a parameter.
        ProcessBuilder pb1 = new ProcessBuilder("java", "Child1", String.valueOf(secret1), String.valueOf(goesFirst));
        Process child1 = pb1.start();
        BufferedReader readerChild1 = new BufferedReader(new InputStreamReader(child1.getInputStream()));
        PrintWriter writerChild1 = new PrintWriter(child1.getOutputStream(), true);

        // Process of the second child with the second secret sent as a parameter.
        ProcessBuilder pb2 = new ProcessBuilder("java", "Child2", String.valueOf(secret2), String.valueOf(!goesFirst));
        Process child2 = pb2.start();
        BufferedReader readerChild2 = new BufferedReader(new InputStreamReader(child2.getInputStream()));
        PrintWriter writerChild2 = new PrintWriter(child2.getOutputStream(), true);

        boolean found = false;
        String winner = null;
        int round = 1;

        System.out.println("\n[---GAME ON---]");
        while (winner == null) { // ROUND PASER

            System.out.println("ROUND " + round);

            int guess;

            guess = Integer.parseInt(read(readerChild1)); // RECIEVE GUESS FROM CHILD 1
            found = guess(readerChild2, writerChild2, guess); // MAKE A GUESS TO CHILD 2 AND READ THE RESULT
            if (found) {
                winner = "CHILD1";
            }

            guess = Integer.parseInt(read(readerChild2)); // RECIEVE GUESS FROM CHILD 2
            found = guess(readerChild1, writerChild1, guess); // MAKE GUESS TO CHILD 1 AND READ THE RESULT

            if (found && winner != null) {
                winner = "TIE";
            } else if (found) {
                winner = "CHILD2";
            }

            round++;
        }

        // clear();
        System.out.println("\n[---THE GAME HAS ENDED---]");
        switch (winner) {
            case "CHILD1":
                System.out.println("Child 1 found the secret number of child 2\nCHILD 1 WINS!");
                write(writerChild1, "END"); // Send END to Child 1
                break;

            case "CHILD2":
                System.out.println("Child 2 found the secret number of child 1\nCHILD 2 WINS!");
                write(writerChild2, "END"); // Send END to Child 2
                break;

            case "TIE":
                System.out.println("There was a tie! Both children found each other's secret on the same round!");
                // Both processes already set gameEnded = true and terminated natively.
                break;
        }

        sc.close();
    }

    /**
     * Sends a guess to the process and returns if it was correct. Game specific.
     * 
     * @param reader BufferedReader of the process to be read.
     * @param writer PrintWriter of the process to write to.
     * @param guess  The number to ask to the process.
     * 
     * @return If the guess equals the secret of the process.
     * 
     * @throws IOException If an I/O Exception occurs.
     */
    public static boolean guess(BufferedReader reader, PrintWriter writer, int guess) throws IOException {
        write(writer, String.valueOf(guess));
        return read(reader).equals("FOUND");
    }

    /**
     * Reads the stdout of a process.
     * 
     * @param reader BufferedReader of the process to be read.
     * 
     * @return Returns a String with the stdout of the process.
     * 
     * @throws IOException If an I/O Exception occurs.
     */
    public static String read(BufferedReader reader) throws IOException {
        return reader.readLine();
    }

    /**
     * Writes to the stdin of a process.
     * 
     * @param writer PrintWriter of the process to write to.
     * @param input  String of the input to write to the stdin of the process.
     */
    public static void write(PrintWriter writer, String input) {
        writer.println(input);
    }

    /**
     * Clears the console (vscode)
     */
    public static void clear() {
        // [----CLEAR CONSOLE----]
        System.out.print("\033[H\033[2J\033[3J");
        System.out.flush();
        // [----CLEAR CONSOLE----]
    }
}
