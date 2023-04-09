package symbols;

public class AsyncPrinterThread extends Thread {
    private final char symbol;

    public AsyncPrinterThread(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
