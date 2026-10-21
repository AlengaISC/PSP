package com.alenga;

public class Downloads {
    public static void main(String[] args) {
        clear();
        Thread videoDownload = new Thread(() -> {
            int percentage = 0;
            do {
                System.out.println("video.mp4 [" + "█".repeat(percentage / 10) + "]" + percentage + "%");
                percentage = percentage + 10;
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.err.println("ERROR. Exception occurred when sleeping: " + e.getMessage());
                }
            } while (percentage <= 100);
            System.out.println("Downloaded video.mp4 successfully");
        });

        Thread musicDownload = new Thread(() -> {
            int percentage = 0;
            do {
                System.out.println("music.mp3 [" + "█".repeat(percentage / 10) + "]" + percentage + "%");
                percentage = percentage + 10;
                try {
                    Thread.sleep(300);
                } catch (Exception e) {
                    System.err.println("ERROR. Exception occurred when sleeping: " + e.getMessage());
                }
            } while (percentage <= 100);
            System.out.println("Downloaded music.mp3 successfully");
        });

        Thread imageDownload = new Thread(() -> {
            int percentage = 0;
            do {
                System.out.println("image.jpg [" + "█".repeat(percentage / 10) + "]" + percentage + "%");
                percentage = percentage + 10;
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    System.err.println("ERROR. Exception occurred when sleeping: " + e.getMessage());
                }
            } while (percentage <= 100);
            System.out.println("Downloaded image.jpg successfully");
        });

        Thread photoshopDownload = new Thread(() -> {
            int percentage = 0;
            do {
                System.out.println("photoshop2026.iso [" + "█".repeat(percentage / 10) + "]" + percentage + "%");
                percentage = percentage + 10;
                try {
                    Thread.sleep(2500);
                } catch (Exception e) {
                    System.err.println("ERROR. Exception occurred when sleeping: " + e.getMessage());
                }
            } while (percentage <= 100);
            System.out.println("Downloaded photoshop2026.iso successfully");
        });

        System.out.println("Downloads starting...");
        videoDownload.start();
        musicDownload.start();
        imageDownload.start();
        photoshopDownload.start();

        try {
            videoDownload.join();
            musicDownload.join();
            imageDownload.join();
            photoshopDownload.join();
        } catch (Exception e) {
            System.err.println("ERROR. Exception occurred when joining: " + e.getMessage());
        }

        System.out.println("All downloads have finished.");
        System.out.println("\n\n[----PREGUNTAS----]");
        System.out.println("1. ¿Qué archivo termina primero? ¿Por qué?");
        System.out.println("image.jpg termina primero porque es el que tiene el sleep más corto\n");
        System.out.println("2. ¿Cuánto debería tardar aproximadamente cada descarga?");
        System.out.println("El tiempo que duerme * 10\n");
        System.out.println("3. ¿Cuánto tardan las tres descargas ejecutándose concurrentemente?");
        System.out.println("El tiempo que tarde la descarga más larga\n");
        System.out.println("4. ¿Qué sucedería si utilizásemos run() en lugar de start()?");
        System.out.println("Cada archivo solo empiezará a descargarse al que termine el anterior. Secuencial\n");
        System.out.println(
                "5. ¿Qué ocurriría si eliminásemos los join()? ¿Aparecería el mensaje final antes de acabar las descargas?");
        System.out.println("Sí, el mensaje final saldría antes de que empiece la primera\n");
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