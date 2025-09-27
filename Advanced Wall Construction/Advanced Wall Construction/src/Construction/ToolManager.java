package Construction;

import java.util.concurrent.Semaphore;

public class ToolManager {
    private final Semaphore tools;

    public ToolManager(int toolLimit) {
        this.tools = new Semaphore(toolLimit);
    }

    public void acquireTool() throws InterruptedException {
        tools.acquire();
    }

    public void releaseTool() {
        tools.release();
    }
}
