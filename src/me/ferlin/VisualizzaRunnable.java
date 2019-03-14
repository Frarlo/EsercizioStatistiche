package me.ferlin;

public class VisualizzaRunnable extends BaseRunnable {



    public VisualizzaRunnable(DatiCondivisi datiCondivisi) {
        super(datiCondivisi);
    }

    @Override
    public void run() {
        try {
            while(true)
                datiCondivisi().getSchermo().printMessages();
        } catch (InterruptedException ex) {
            // Interrotto dal main
        }
    }
}
