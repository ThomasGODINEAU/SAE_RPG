package LectureScenario;

import modele.Quete;
import modele.Scenario;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LectureFichierTexte {
    /** Méthode statique pour lire un fichier texte et retourner un objet Scenario
     *
     * @param fichier
     * @return (scenario) : objet de la classe scénario
     */
    public static Scenario lecture(File fichier) {
        Scenario scenario = new Scenario();
        try {
            Scanner scanner = new Scanner(fichier);
            // Parcourt chaque ligne du fichier texte
            while (scanner.hasNext()) {
                // Crée un nouvel objet Quete pour chaque ligne et ajoute la quête à l'objet Scenario
                scenario.ajout(new Quete(scanner.nextLine()));
            }
        }
        // Capture une exception s'il y a une erreur lors de l'ouverture du fichier
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return scenario;
    }
}
