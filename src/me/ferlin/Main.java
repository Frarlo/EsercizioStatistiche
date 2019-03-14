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
    public static void main(String[] args) {

        final Scanner sc = new Scanner(System.in);

        System.out.println("Inserire il numero di caratteri da generare: ");
        final int daGenerare = sc.nextInt();

        DatiCondivisi datiCondivisi = new DatiCondivisi(daGenerare);

        Thread genTh = new Thread(new GeneratoreRunnable(datiCondivisi));
        Thread puntiTh = new Thread(new LettoreRunnable(datiCondivisi, '.'));
        Thread spaziTh = new Thread(new LettoreRunnable(datiCondivisi, ' '));

        genTh.start();
        puntiTh.start();
        spaziTh.start();

        try {
            genTh.join();
            puntiTh.join();
            spaziTh.join();

            System.out.println("NumSpaziInseriti: " + datiCondivisi.getNumSpaziInseriti());
            System.out.println("NumPuntiInseriti: " + datiCondivisi.getNumPuntiInseriti());
            System.out.println("NumSpaziLetti: " + datiCondivisi.getNumSpaziLetti());
            System.out.println("NumPuntiLetti: " + datiCondivisi.getNumPuntiLetti());

            System.out.println("Done.");

        } catch (InterruptedException ex) {
            System.err.println("Main thread got interrupted");
            ex.printStackTrace();
        }

        System.out.println("Done.");
    }
    
}
