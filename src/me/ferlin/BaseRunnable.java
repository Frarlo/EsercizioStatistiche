package me.ferlin;

public abstract class BaseRunnable implements Runnable {

    private final DatiCondivisi datiCondivisi;

    protected BaseRunnable(DatiCondivisi datiCondivisi) {
        this.datiCondivisi = datiCondivisi;
    }

    public DatiCondivisi datiCondivisi() {
        return datiCondivisi;
    }
}
