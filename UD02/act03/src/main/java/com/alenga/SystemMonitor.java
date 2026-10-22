package com.alenga;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SystemMonitor {
    public static void main(String[] args) {
        clear();

        Thread ramMonitor = new Thread(() -> {
            Process process = null;
            String readString = "";

            while (!Thread.currentThread().isInterrupted()) {
                // Limpiar lector
                readString = "";

                // Crear proceso Linux
                ProcessBuilder pb = new ProcessBuilder("free", "-m");

                // Ejecutar comando
                try {
                    process = pb.start();

                    // Recoger resultados
                    BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    try {
                        String line;
                        while ((line = lector.readLine()) != null) {
                            readString = readString + "\n" + line;
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR. An I/O error has occurred.");
                    }

                    // Consulta realizada
                    System.out.println("Consulta realizada " + Thread.currentThread().getName());

                    // Esperar a que termine el proceso y esperar 2 segundos
                    try {
                        process.waitFor();
                        // int id = process.waitFor();
                        // System.out.println("Exit code:" + id);
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }

                } catch (Exception e) {
                    System.err.println("ERROR. An error occurred while trying to run  \".start\"");
                }
            }

            System.out.println("\n\n\n" + Thread.currentThread().getName() + " Output:");
            System.out.println(readString);

        }, "Monitor-RAM");

        Thread diskMonitor = new Thread(() -> {
            Process process = null;
            String readString = "";

            while (!Thread.currentThread().isInterrupted()) {
                // Limpiar lector
                readString = "";

                // Crear proceso Linux
                ProcessBuilder pb = new ProcessBuilder("df", "-h", "/");

                // Ejecutar comando
                try {
                    process = pb.start();

                    // Recoger resultados
                    BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    try {
                        String line;
                        while ((line = lector.readLine()) != null) {
                            readString = readString + "\n" + line;
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR. An I/O error has occurred.");
                    }

                    // Consulta realizada
                    System.out.println("Consulta realizada " + Thread.currentThread().getName());

                    // Esperar a que termine el proceso y esperar 2 segundos
                    try {
                        process.waitFor();
                        // int id = process.waitFor();
                        // System.out.println("Exit code:" + id);
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }

                } catch (Exception e) {
                    System.err.println("ERROR. An error occurred while trying to run  \".start\"");
                }
            }

            System.out.println("\n\n\n" + Thread.currentThread().getName() + " Output:");
            System.out.println(readString);

        }, "Monitor-DISK");

        Thread processMonitor = new Thread(() -> {
            Process process = null;
            String readString = "";

            while (!Thread.currentThread().isInterrupted()) {
                // Limpiar lector
                readString = "";

                // Crear proceso Linux
                ProcessBuilder pb = new ProcessBuilder("ps", "-e", "--no-headers");

                // Ejecutar comando
                try {
                    process = pb.start();

                    // Recoger resultados
                    BufferedReader lector = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    try {
                        String line;
                        while ((line = lector.readLine()) != null) {
                            readString = readString + "\n" + line;
                        }
                    } catch (Exception e) {
                        System.err.println("ERROR. An I/O error has occurred.");
                    }

                    // Consulta realizada
                    System.out.println("Consulta realizada " + Thread.currentThread().getName());

                    // Esperar a que termine el proceso y esperar 2 segundos
                    try {
                        process.waitFor();
                        // int id = process.waitFor();
                        // System.out.println("Exit code:" + id);
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    }

                } catch (Exception e) {
                    System.err.println("ERROR. An error occurred while trying to run  \".start\"");
                }
            }

            System.out.println("\n\n\n" + Thread.currentThread().getName() + " Output:");
            System.out.println(readString);

        }, "Monitor-PROCESS");

        // ---------------------------------------------------------------------------------

        // Hilos sin iniciar
        System.out.println(ramMonitor.getName() + " status: " + ramMonitor.getState());
        System.out.println(diskMonitor.getName() + " status: " + diskMonitor.getState());
        System.out.println(processMonitor.getName() + " status: " + processMonitor.getState());

        // Iniciar hilos y consultar su estado
        // System.out.println("Started thread: " + ramMonitor.getName());
        ramMonitor.start();
        System.out.println(ramMonitor.getName() + " status: " + ramMonitor.getState());
        // System.out.println("Started thread: " + diskMonitor.getName());
        diskMonitor.start();
        System.out.println(diskMonitor.getName() + " status: " + diskMonitor.getState());
        // System.out.println("Started thread: " + processMonitor.getName());
        processMonitor.start();
        System.out.println(processMonitor.getName() + " status: " + processMonitor.getState());

        // Interrumpir los hilos tras 10s
        try {
            Thread.sleep(10000);
            ramMonitor.interrupt();
            ramMonitor.join();
            diskMonitor.interrupt();
            diskMonitor.join();
            processMonitor.interrupt();
            processMonitor.join();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        // Hilos tras ser interrumpidos
        System.out.println("\n\n\n");
        System.out.println(ramMonitor.getName() + " status: " + ramMonitor.getState());
        System.out.println(diskMonitor.getName() + " status: " + diskMonitor.getState());
        System.out.println(processMonitor.getName() + " status: " + processMonitor.getState());

        System.out.println("\nMonitorización finalizada.");

        // PREGUNTAS
        System.out.println("""
                \n
                ● ¿En qué estado se encuentra un hilo antes de ejecutar start()?
                    NEW

                ● ¿Qué estados puedes observar durante la monitorización?
                    RUNNABLE o BLOCKED

                ● ¿Qué estado tienen los hilos después de que join() haya terminado?
                    TERMINATED
                                """);

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