package Workers;

import Construction.Wall;

public class StoneLayer extends AbstractWorker {
    private final Wall wall;

    public StoneLayer(Wall wall, String name) {
        super(name);
        this.wall = wall;
    }

    @Override
    protected void performTask() {
        while (!Thread.currentThread().isInterrupted()) {
            if (wall.isConstructionComplete()) {
                break; // Sort de la boucle si le mur est complet
            }

            if (!wall.placeStone(name)) {
                break; // Sort de la boucle si la construction est terminée
            }

            try {
                Thread.sleep(500); // Simule un délai
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Assure l'arrêt propre
            }
        }

        // Attente finale pour s'assurer que tous les threads sont notifiés en même temps
        wall.waitForCompletion();

        System.out.println(name + " is stopping as the wall is completed.");
    }
}
