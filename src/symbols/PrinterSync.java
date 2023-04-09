package symbols;

public class PrinterSync {
    private boolean permission = true;
    private int operations = 0;
    private boolean stopped = false;

    public boolean isStopped() {
        return stopped;
    }

    public synchronized void waitAndChange(boolean permission, char symbol) {
        while (this.permission != permission) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        if (this.stopped) {
            notifyAll();
            return;
        }
        System.out.print(symbol);
        this.permission = !this.permission;
        operations++;
        if (operations % 100 == 0) {
            System.out.println();
        }
        if (operations == 100000) {
            stopped = true;
        }
        notifyAll();
    }

}
