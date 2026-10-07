import java.util.Scanner;

public class Child {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        int suma = a + b;
        System.out.println(suma);
        sc.close();
    }
}