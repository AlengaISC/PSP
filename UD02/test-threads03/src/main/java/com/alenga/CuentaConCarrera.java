package com.alenga;

public class CuentaConCarrera {
    static class Cuenta {
        int saldo = 100;

        boolean retirar(int cantidad) {
            if (saldo >= cantidad) {
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return false;
                }
                saldo = saldo - cantidad;
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        clear();
        Cuenta cuenta = new Cuenta();
        Thread ana = new Thread(() -> {
            boolean resultado = cuenta.retirar(80);
            System.out.println(
                    "Ana retira 80: " + resultado);
        });
        Thread lea = new Thread(() -> {
            boolean resultado = cuenta.retirar(80);
            System.out.println(
                    "Lea retira 80: " + resultado);
        });
        ana.start();
        lea.start();
        ana.join();
        lea.join();
        System.out.println("Saldo final: " +
                cuenta.saldo);
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