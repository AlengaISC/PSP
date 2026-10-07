import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingTest {
    public static void main(String[] args) throws Exception {
        // Clear console snippet
        System.out.print("\033[H\033[2J\033[3J");
        System.out.flush();

        ProcessBuilder pb = new ProcessBuilder("ping", "-c", "4", "google.com");
        Process proceso = pb.start();

        BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

        String linea;
        while ((linea = lector.readLine()) != null) {
            System.out.println(linea);
        }

        int codigo = proceso.waitFor();
        System.out.println("\nCódigo de salida: " + codigo);
    }
}
