package me.ferlin;

import java.util.Scanner;

/**
 *
 * @author Francesco Ferlin
 */
public class Main {

    /**
     * Entry point
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        final Scanner sc = new Scanner(System.in);

        System.out.println("Inserire il numero di caratteri da generare: ");
        final int daGenerare = sc.nextInt();

        DatiCondivisi datiCondivisi = new DatiCondivisi(daGenerare);

        Thread genTh = new Thread(new GeneratoreRunnable(datiCondivisi), "ThreadGeneratore");
        Thread puntiTh = new Thread(new LettoreRunnable(datiCondivisi, '.'), "ThreadContaPunti");
        Thread spaziTh = new Thread(new LettoreRunnable(datiCondivisi, ' '), "ThreadContaSpazi");
        Thread visualizzaTh = new Thread(new VisualizzaRunnable(datiCondivisi), "ThreadVisualizza");

        genTh.start();
        puntiTh.start();
        spaziTh.start();
        visualizzaTh.start();

        datiCondivisi.getTerminationSemaphore().acquireUninterruptibly(3);
        visualizzaTh.interrupt();
        visualizzaTh.join();

        System.out.println("NumSpaziInseriti: " + datiCondivisi.getNumSpaziInseriti());
        System.out.println("NumPuntiInseriti: " + datiCondivisi.getNumPuntiInseriti());
        System.out.println("NumSpaziLetti: " + datiCondivisi.getNumSpaziLetti());
        System.out.println("NumPuntiLetti: " + datiCondivisi.getNumPuntiLetti());

        if(datiCondivisi.getNumSpaziInseriti() == datiCondivisi.getNumSpaziLetti() &&
                datiCondivisi.getNumPuntiInseriti() == datiCondivisi.getNumPuntiLetti())
            System.out.println("I dati corrispondono");
        else
            System.err.println("I dati non corrispondono");

        System.out.println("Done.");
    }
    
}
