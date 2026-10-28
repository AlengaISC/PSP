package com.alenga;

public class ContadorConCarrera {
    static class Contador {
        int valor = 0;
    }

    public static void main(String[] args)
            throws InterruptedException {
        for (int prueba = 1; prueba <= 3; prueba++) {
            Contador contador = new Contador();
            Runnable tarea = () -> {
                for (int i = 0; i < 1_000_000; i++) {
                    contador.valor++;
                    if (i % 1000 == 0) {
                        // sugiere al planificador ceder la CPU a otros hilos
                        Thread.yield();
                    }
                }
            };
            Thread hilo1 = new Thread(tarea);
            Thread hilo2 = new Thread(tarea);
            hilo1.start();
            hilo2.start();
            hilo1.join();
            hilo2.join();
            System.out.println(
                    "Prueba " + prueba
                            + " | Esperado: 2000000"
                            + " | Obtenido: " + contador.valor);
        }
    }
}