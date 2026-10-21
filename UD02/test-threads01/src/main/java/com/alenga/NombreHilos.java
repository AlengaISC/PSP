package com.alenga;

public class NombreHilos {
    public static void main(String[] args) {

        clear();
        System.out.println("Started");

        Runnable task = () -> {
            System.out.println("Soy " + Thread.currentThread().getName());

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("ERROR. Exception occurred: " + e.getMessage());
            }
        };

        Thread worker1 = new Thread(task, "Trabajador-A");
        Thread worker2 = new Thread(task, "Trabajador-B");
        Thread worker3 = new Thread(task, "Trabajador-3");

        try {
            worker1.start();
            worker1.join();
            worker2.start();
            worker2.join();
            worker3.start();
            worker3.join();

        } catch (Exception e) {
            System.out.println("ERROR. Exception occurred: " + e.getMessage());
        }

        System.out.println("Finished");
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