package me.ferlin;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GeneratoreRunnable extends BaseRunnable {

    private static final String DICTIONARY = "ABCDEFGHIJKLMNOPQRS. ";

    public GeneratoreRunnable(DatiCondivisi datiCondivisi) {
        super(datiCondivisi);
    }

    @Override
    public void run() {

        Random rn = ThreadLocalRandom.current();
        try {
            while(datiCondivisi().getDaGenerare() > 0) {
                datiCondivisi().getVuotoSemaphore().acquire(2);

                int i;
                for(i = 0; i < datiCondivisi().getBuffer().length; i++) {

                    if(datiCondivisi().getDaGenerare() <= 0)
                        break;

                    char generated = DICTIONARY.charAt(rn.nextInt(DICTIONARY.length()));

                    datiCondivisi().getBufferMutex().acquire();
                    datiCondivisi().getBuffer()[i] = generated;
                    datiCondivisi().getBufferMutex().release();

                    if(generated == ' ') {
                        datiCondivisi().getSchermo().println("Generata lettera '" + generated + "'");
                        datiCondivisi().incNumSpaziInseriti();
                    } else if(generated == '.') {
                        datiCondivisi().getSchermo().println("Generata lettera '" + generated + "'");
                        datiCondivisi().incNumPuntiInseriti();
                    }

                    datiCondivisi().decrementDaGenerare(1);
                }
                datiCondivisi().setDaLeggere(i);

                datiCondivisi().getPienoPuntiSemaphore().release();
                datiCondivisi().getPienoSpaziSemaphore().release();
            }

        } catch (InterruptedException ex) {
            // Il thread è stato interrotto dal main
        }

        datiCondivisi().getTerminationSemaphore().release();
    }
}
