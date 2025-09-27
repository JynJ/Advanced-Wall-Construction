import Construction.*;
import Workers.*;

public class AppStarter {
    public static void main(String[] args) {
        // Initialisation du réservoir de pierre
        StonePool stonePool = new StonePool(50);

        // Initialisation du mur
        Wall wall = new Wall(100, 3); // Construction : 4 rangées, 12 pierres par rangée donc on fera 5 fois 90 pour savoir combien de stones doivent etre déposés

        // Initialisation des outils
        ToolManager toolManager = new ToolManager(2); // Limite de 2 outils

        // Initialisation du gestionnaire d'événements
        EventScheduler eventScheduler = new EventScheduler(stonePool, toolManager);

        // Lancement des travailleurs
        Thread[] threads = new Thread[7];

        // Création des chercheurs de pierres
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(new StoneFinder(stonePool, "Finder-" + i));
        }

        // Création des transporteurs
        for (int i = 3; i < 5; i++) {
            threads[i] = new Thread(new StoneTransporter(stonePool, toolManager, wall, "Transporter-" + (i - 3)));
        }


        // Création des maçons
        for (int i = 5; i < 7; i++) {
            threads[i] = new Thread(new StoneLayer(wall, "Mason-" + (i - 5)));
        }

        // Démarrage des threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Démarrage des événements aléatoires
        eventScheduler.startEvents();
    }
}