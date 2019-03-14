package me.ferlin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Schermo {

    private final List<String> linee;

    private final Semaphore scrivereSemaphore;
    private final Semaphore scrittoSemaphore;

    public Schermo() {
        linee = Collections.synchronizedList(new ArrayList<>());

        scrivereSemaphore = new Semaphore(0);
        scrittoSemaphore = new Semaphore(0);
    }

    public void println(String linea) throws InterruptedException {
        linee.add("[" + Thread.currentThread().getName() + "] " + linea);
        scrivereSemaphore.release();
        scrittoSemaphore.acquire();
    }

    public void printMessages() throws InterruptedException {
        scrivereSemaphore.acquire();

        clearScreen();
        System.out.println("----------------------------------------");
        synchronized (linee) {
            for(String linea : linee)
                System.out.println(linea);
        }
        System.out.println("----------------------------------------");

        scrittoSemaphore.release();
    }

    private void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {
        }
    }
}
