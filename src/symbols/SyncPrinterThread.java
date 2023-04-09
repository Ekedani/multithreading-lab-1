package symbols;

public class SyncPrinterThread extends Thread {
    private final PrinterSync sync;
    private final char symbol;
    private final boolean permission;

    public SyncPrinterThread(PrinterSync sync, char symbol, boolean permission) {
        this.sync = sync;
        this.symbol = symbol;
        this.permission = permission;
    }

    @Override
    public void run() {
        while (!this.sync.isStopped()) {
            sync.waitAndChange(this.permission, this.symbol);
        }
    }
}
