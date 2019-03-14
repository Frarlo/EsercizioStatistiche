package me.ferlin;

public class LettoreRunnable extends BaseRunnable {

    private final char daContare;

    public LettoreRunnable(DatiCondivisi datiCondivisi,
                              char daContare) {
        super(datiCondivisi);
        this.daContare = daContare;
    }

    @Override
    public void run() {
        try {
            while(datiCondivisi().getDaGenerare() > 0) {

                switch (daContare) {
                    case '.':
                        datiCondivisi().getPienoPuntiSemaphore().acquire();
                        break;
                    case ' ':
                        datiCondivisi().getPienoSpaziSemaphore().acquire();
                }

                for(int i = 0; i < datiCondivisi().getDaLeggere(); i++) {

                    datiCondivisi().getBufferMutex().acquire();
                    char c = datiCondivisi().getBuffer()[i];
                    datiCondivisi().getBufferMutex().release();

                    if (c == daContare)
                        switch (daContare) {
                            case '.':
                                datiCondivisi().getSchermo().println("Letta lettera '" + c + "'");
                                datiCondivisi().incNumPuntiLetti();
                                break;
                            case ' ':
                                datiCondivisi().getSchermo().println("Letta lettera '" + c + "'");
                                datiCondivisi().incNumSpaziLetti();
                        }
                }

                datiCondivisi().getVuotoSemaphore().release();
            }
        } catch (InterruptedException ex) {
            // Il thread è stato interrotto dal main
        }

        datiCondivisi().getTerminationSemaphore().release();
    }
}
