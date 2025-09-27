package Workers;

import Construction.StonePool;
import Construction.ToolManager;
import Construction.Wall;

public class StoneTransporter extends AbstractWorker {
    private final StonePool stonePool;
    private final ToolManager toolManager;

    private final Wall wall; // Ajoutez une référence au mur partagé

    public StoneTransporter(StonePool stonePool, ToolManager toolManager, Wall wall, String name) {
        super(name);
        this.stonePool = stonePool;
        this.toolManager = toolManager;
        this.wall = wall; // Initialisez le mur partagé
    }

    @Override
    protected void performTask() {
        try {
            toolManager.acquireTool(); // Acquérir un outil pour transporter la pierre
            if (stonePool.acquireStone()) { // Vérifie si une pierre est disponible
                System.out.println(name + " transported a stone.");
                if (!wall.placeStone(name)) { // Place la pierre sur le mur partagé
                    System.out.println("Wall completed! " + name + " is stopping.");
                    Thread.currentThread().interrupt(); // Arrêter proprement le thread
                }
            }
            toolManager.releaseTool(); // Libérer l'outil
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}