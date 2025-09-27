package Workers;

public abstract class AbstractWorker implements Runnable {
    protected final String name;

    public AbstractWorker(String name) {
        this.name = name;
    }

    protected abstract void performTask();

    @Override
    public void run() {
        while (true) {
            performTask();
        }
    }
}
