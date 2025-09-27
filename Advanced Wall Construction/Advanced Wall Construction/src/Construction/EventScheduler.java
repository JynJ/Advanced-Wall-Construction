package Construction;

import java.util.Timer;
import java.util.TimerTask;

public class EventScheduler {
    private final StonePool stonePool;
    private final ToolManager toolManager;

    public EventScheduler(StonePool stonePool, ToolManager toolManager) {
        this.stonePool = stonePool;
        this.toolManager = toolManager;
    }

    public void startEvents() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Random Event: Adding stones...");
                stonePool.addStone();
            }
        }, 5000, 10000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Random Event: Tool malfunction simulated.");
            }
        }, 8000, 15000);
    }
}
