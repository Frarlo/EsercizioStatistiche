package me.ferlin;

public class LettoreRunnable extends BaseRunnable {

    private final char daContare;

    protected LettoreRunnable(DatiCondivisi datiCondivisi,
                              char daContare) {
        super(datiCondivisi);
        this.daContare = daContare;
    }

    @Override
    public void run() {
        try {

            while(datiCondivisi().getDaGenerare() > 0) {
                datiCondivisi().getPieniSemaphore().acquire();
                datiCondivisi().getBufferMutex().acquire();

                for(char c : datiCondivisi().getBuffer())
                    if(c == daContare) {
                        switch (daContare) {
                            case '.':
                                datiCondivisi().incNumPuntiLetti();
                                break;
                            case ' ':
                                datiCondivisi().incNumSpaziLetti();
                        }
                    }

                datiCondivisi().getBufferMutex().release();
                datiCondivisi().getVuotoSemaphore().release();
            }

        } catch (InterruptedException ex) {
            // Il thread è stato interrotto dal main
        }
    }
}
