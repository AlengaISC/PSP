public class App {
    public static void main(String[] args) throws Exception {
        // Snippet to clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

        ProcessBuilder pb = new ProcessBuilder("sleep", "10");
        Process process = pb.start();

        System.out.println("Process created");
        System.out.println("PID: " + process.pid());

        // process.waitFor();
        System.out.println("Process terminated");
    }
}