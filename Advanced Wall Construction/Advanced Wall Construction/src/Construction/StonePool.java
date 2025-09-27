package Construction;

import java.util.concurrent.atomic.AtomicInteger;

public class StonePool {
    private final AtomicInteger stoneCount;
    private int availableStones;



    public class Stonepool{
        private int availableStones;
    }
    public StonePool(int initialStones) {
        this.stoneCount = new AtomicInteger(initialStones);
        this.availableStones = initialStones;
    }

    public synchronized boolean acquireStone() {
        if (stoneCount.get() > 0) {
            stoneCount.decrementAndGet();
            return true;
        }



        return false;
    }

    public synchronized void addStone() {
        stoneCount.incrementAndGet(); // Utilise AtomicInteger pour incrémenter
        notifyAll(); // Notifie les threads en attente
        System.out.println("Stone added to the pool. Total stones: " + stoneCount.get());
    }

    public synchronized boolean takeStone() throws InterruptedException {
        while (stoneCount.get() <= 0) {
            wait(); // Attend qu'une pierre soit ajoutée
        }
        stoneCount.decrementAndGet(); // Décrémente le compteur
        System.out.println("Stone taken from the pool. Remaining stones: " + stoneCount.get());
        return true;
    }


    public int getAvailableStones() {
        return stoneCount.get();
    }
}
