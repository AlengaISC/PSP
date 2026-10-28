package com.alenga;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SystemMonitor {

    // 8. Sexta parte. Memoria compartida
    static Report report = new Report();

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
                    report.memory = readString;
                    // System.out.println(readString);

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

            // System.out.println("\n\n\n" + Thread.currentThread().getName() + " Output:");
            // System.out.println(readString);

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
                    report.disk = readString;
                    // System.out.println(readString);

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

            // System.out.println("\n\n\n" + Thread.currentThread().getName() + " Output:");
            // System.out.println(readString);

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
                    report.processes = readString;
                    // System.out.println(readString);

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

            // System.out.println("\n\n\n" + Thread.currentThread().getName() + " Output:");
            // System.out.println(readString);

        }, "Monitor-PROCESS");

        // ---------------------------------------------------------------------------------

        System.out.println("""
                =================================
                    MONITOR DEL SISTEMA
                =================================""");

        // Hilos sin iniciar
        System.out.println(ramMonitor.getName() + " status: " + ramMonitor.getState());
        System.out.println(diskMonitor.getName() + " status: " + diskMonitor.getState());
        System.out.println(processMonitor.getName() + " status: " + processMonitor.getState());

        // Iniciar hilos y consultar su estado
        System.out.println("\nIniciando monitorización...\n");

        // System.out.println("Started thread: " + ramMonitor.getName());
        ramMonitor.start();
        System.out.println(ramMonitor.getName() + " status: " + ramMonitor.getState());
        // System.out.println("Started thread: " + diskMonitor.getName());
        diskMonitor.start();
        System.out.println(diskMonitor.getName() + " status: " + diskMonitor.getState());
        // System.out.println("Started thread: " + processMonitor.getName());
        processMonitor.start();
        System.out.println(processMonitor.getName() + " status: " + processMonitor.getState());
        System.out.println();

        // Interrumpir los hilos tras 10s
        try {
            Thread.sleep(10000);
            System.out.println("Solicitando finalización...");
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
        System.out.println();
        System.out.println(ramMonitor.getName() + " status: " + ramMonitor.getState());
        System.out.println(diskMonitor.getName() + " status: " + diskMonitor.getState());
        System.out.println(processMonitor.getName() + " status: " + processMonitor.getState());

        // Mostrar resultados del informe
        System.out.println("""
                =================================
                    INFORME FINAL
                =================================""");

        System.out.println("\nMEMORIA:" + report.memory);
        System.out.println("\nDISCO:" + report.disk);
        System.out.println("\nPROCESOS:" + report.processes);

        System.out.println("\n\nMonitorización finalizada.");

        // PREGUNTAS
        System.out.println(
                """
                        ● ¿En qué estado se encuentra un hilo antes de ejecutar start()?
                            NEW

                        ● ¿Qué estados puedes observar durante la monitorización?
                            RUNNABLE o BLOCKED

                        ● ¿Qué estado tienen los hilos después de que join() haya terminado?
                            TERMINATED

                        9. Preguntas para entregar:
                            1. ¿Cuántos hilos crea explícitamente la aplicación?
                                3 explícitos + el hilo del main.

                            2. ¿Qué diferencia existe entre los hilos de Java y los procesos Linux que ejecutan?
                            3. ¿Por qué necesitamos waitFor() y join() en el mismo programa?
                            4. ¿Qué sucede cuando el hilo principal solicita la interrupción de los trabajadores?
                            5. ¿Por qué un hilo que ejecuta Thread.sleep() puede encontrarse en estado TIMED_WAITING?
                            6. ¿Qué ocurriría si eliminásemos los join() y mostrásemos el informe inmediatamente después de solicitar las interrupciones?
                            7. ¿Cómo pueden los tres hilos acceder al mismo objeto Informe?
                            8. ¿Por qué en esta práctica cada hilo modifica un atributo diferente del objeto compartido?
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