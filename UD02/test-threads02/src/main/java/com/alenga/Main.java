package com.alenga;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        clear();

        Thread hilo = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        System.out.println("Antes: " + hilo.getState());
        hilo.start();
        Thread.sleep(100);
        System.out.println("Durante: " + hilo.getState());
        hilo.join();
        System.out.println("Después: " + hilo.getState());
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