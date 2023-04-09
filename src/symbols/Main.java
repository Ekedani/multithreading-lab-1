package symbols;

public class Main {
    public static void main(String[] args) {
        /*AsyncPrinterThread asyncMinusThread = new AsyncPrinterThread('-');
        AsyncPrinterThread asyncPipeThread = new AsyncPrinterThread('|');
        asyncMinusThread.start();
        asyncPipeThread.start();*/

        PrinterSync printerSync = new PrinterSync();
        SyncPrinterThread syncMinusThread = new SyncPrinterThread(printerSync, '-', true);
        SyncPrinterThread syncPipeThread = new SyncPrinterThread(printerSync, '|', false);
        syncMinusThread.start();
        syncPipeThread.start();
    }
}
