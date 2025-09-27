package Workers;

import Construction.StonePool;

public class StoneFinder extends AbstractWorker {
    private final StonePool stonePool;

    public StoneFinder(StonePool stonePool, String name) {
        super(name);
        this.stonePool = stonePool;
    }

    @Override
    protected void performTask() {
        try {
            Thread.sleep(1000);
            stonePool.addStone();
            System.out.println(name + " found and added a stone.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
