package me.ferlin;

import java.util.concurrent.Semaphore;

public class DatiCondivisi {

    private static final int BUFFER_SIZE = 10;

    private final char[] buffer;
    private int daGenerare;
    private int daLeggere;

    private final Semaphore bufferMutex;
    private final Semaphore pienoSemaphore;
    private final Semaphore vuotoSemaphore;

    private int numSpaziInseriti;
    private int numPuntiInseriti;
    private int numSpaziLetti;
    private int numPuntiLetti;

    public DatiCondivisi(int daGenerare) {
        buffer = new char[BUFFER_SIZE];
        this.daGenerare = daGenerare;

        bufferMutex = new Semaphore(1);
        pienoSemaphore = new Semaphore(2);
        vuotoSemaphore = new Semaphore(0);
    }

    public char[] getBuffer() {
        return buffer;
    }

    public synchronized int getDaGenerare() {
        return daGenerare;
    }

    public synchronized int getDaLeggere() {
        return daLeggere;
    }

    public synchronized void setDaLeggere(int daLeggere) {
        this.daLeggere = daLeggere;
    }

    public synchronized void decrementDaGenerare(int toRemove) {
        daGenerare -= toRemove;
    }

    public synchronized int getNumSpaziInseriti() {
        return numSpaziInseriti;
    }

    public synchronized void incNumSpaziInseriti() {
        numSpaziInseriti += 1;
    }

    public synchronized int getNumPuntiInseriti() {
        return numPuntiInseriti;
    }

    public synchronized void incNumPuntiInseriti() {
        numPuntiInseriti += 1;
    }

    public synchronized int getNumSpaziLetti() {
        return numSpaziLetti;
    }

    public synchronized void incNumSpaziLetti() {
        numSpaziLetti++;
    }

    public synchronized int getNumPuntiLetti() {
        return numPuntiLetti;
    }

    public synchronized void incNumPuntiLetti() {
        numPuntiLetti++;
    }

    public Semaphore getBufferMutex() {
        return bufferMutex;
    }

    public Semaphore getPieniSemaphore() {
        return pienoSemaphore;
    }

    public Semaphore getVuotoSemaphore() {
        return vuotoSemaphore;
    }
}
