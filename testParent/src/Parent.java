import java.io.IOException;

public class Parent {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("I'm the parent process");

        ProcessBuilder pb = new ProcessBuilder("java", "Child");

        Process child = pb.start();

        System.out.println("Child process was created");
        System.out.println("Child process PID: " + child.pid());

        child.waitFor();

        System.out.println("Child process ended");
    }
}
