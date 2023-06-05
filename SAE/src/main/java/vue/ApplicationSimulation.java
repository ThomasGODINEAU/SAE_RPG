package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class ApplicationSimulation extends Application {

    /**
     * Méthode de démarrage de l'application JavaFX.
     * Elle crée la scène principale avec la racine de l'interface utilisateur,
     * applique les paramètres de style à partir d'un fichier CSS externe,
     * configure la scène sur la fenêtre principale et affiche la fenêtre.
     *
     * @param stage La fenêtre principale de l'application.
     */
    public void start(Stage stage) {

        // Création de la racine de l'interface utilisateur
        HBoxRoot root = new HBoxRoot();

        // Création de la scène avec la racine
        Scene scene = new Scene(root);

        // Chargement du fichier CSS pour les paramètres de style
        File css = new File("css" + File.separator + "parametres.css");
        root.getStylesheets().add(css.toURI().toString());

        // Configuration de la scène sur la fenêtre principale
        stage.setScene(scene);

        // Configuration des paramètres de la fenêtre
        stage.setTitle("Paramètre de simulation");
        stage.setResizable(false);

        // Affichage de la fenêtre
        stage.show();
    }

    /**
     * Méthode principale permettant de lancer l'application JavaFX.
     *
     * @param args Les arguments de ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
