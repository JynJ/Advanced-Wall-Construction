package Construction;

import java.util.concurrent.locks.ReentrantLock;

public class Wall {
    private final int rows, cols;
    private int currentRow = 0;
    private int stonesPlaced = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private volatile boolean constructionComplete = false; // Indique si la construction est terminée

    public Wall(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public boolean isConstructionComplete() {
        return constructionComplete;
    }

    public synchronized boolean placeStone(String workerName) {
        lock.lock();
        try {
            if (stonesPlaced >= rows * cols) {
                if (!constructionComplete) {
                    System.out.println("Wall construction is complete!");
                    constructionComplete = true;
                    synchronized (this) {
                        notifyAll(); // Notifie tous les threads que la construction est terminée
                    }
                }
                return false;
            }

            stonesPlaced++;
            int row = (stonesPlaced - 1) / cols + 1;
            int col = (stonesPlaced - 1) % cols + 1;
            System.out.println(workerName + " placed a stone at Row: " + row + ", Col: " + col + ". Total: " + stonesPlaced);

            double progress = (double) stonesPlaced / (rows * cols) * 100;
            System.out.printf("Wall progress: %.2f%% (%d/%d stones placed)%n", progress, stonesPlaced, rows * cols);

            if (stonesPlaced % cols == 0) {
                currentRow++;
                System.out.println("Row " + currentRow + " completed!");
            }

            if (stonesPlaced == rows * cols) {
                System.out.println("Wall construction is complete!");
                constructionComplete = true;
                synchronized (this) {
                    notifyAll(); // Notifie tous les threads que la construction est terminée
                }
            }

            return true;
        } finally {
            lock.unlock();
        }
    }

    public synchronized void waitForCompletion() {
        while (!constructionComplete) {
            try {
                wait(); // Attend que le mur soit complété
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Réagit à une interruption
            }
        }
    }
}
