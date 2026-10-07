import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Parent {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Primer número: ");
        int a = sc.nextInt();
        System.out.print("Segundo número: ");
        int b = sc.nextInt();

        // Crear el proceso hijo
        ProcessBuilder pb = new ProcessBuilder("java", "Child");
        Process hijo = pb.start();

        // Enviar los números al hijo
        PrintWriter escritor = new PrintWriter(hijo.getOutputStream(), true);
        escritor.println(a);
        escritor.println(b);

        // Leer el resultado del hijo
        BufferedReader lector = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
        String resultado = lector.readLine();

        // Esperar a que termine
        hijo.waitFor();
        System.out.println("Resultado recibido: " + resultado);
        sc.close();
    }
}
